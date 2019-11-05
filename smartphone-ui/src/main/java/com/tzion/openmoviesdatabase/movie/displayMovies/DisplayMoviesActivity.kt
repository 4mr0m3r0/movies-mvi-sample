package com.tzion.openmoviesdatabase.movie.displayMovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tzion.openmoviesdatabase.MviUi
import com.tzion.openmoviesdatabase.R
import com.tzion.openmoviesdatabase.databinding.ActivityDisplayMoviesBinding
import com.tzion.openmoviesdatabase.movie.mapper.UiMovieMapper
import com.tzion.presentation.movie.FindMoviesViewModel
import com.tzion.presentation.movie.intent.MoviesIntent
import com.tzion.presentation.movie.intent.MoviesIntent.*
import com.tzion.presentation.movie.model.PresentationMovie
import com.tzion.presentation.movie.state.MoviesUiState
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.*

class DisplayMoviesActivity : AppCompatActivity(), MviUi<MoviesIntent, MoviesUiState> {

    private val searchFilterIntentPublisher = PublishSubject.create<SearchFilterIntent>()
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val findMoviesViewModel: FindMoviesViewModel? by lazy(NONE) {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(FindMoviesViewModel::class.java)
    }
    private val disposable = CompositeDisposable()
    @Inject lateinit var displayMoviesAdapter: DisplayMoviesAdapter
    @Inject lateinit var mapper: UiMovieMapper
    private lateinit var binding: ActivityDisplayMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupInjection()
        setUpUi()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_movies)
    }

    private fun setupInjection() {
        AndroidInjection.inject(this)
    }

    private fun setUpUi() {
        attachViewModel()
        setUpRecyclerView()
    }

    private fun attachViewModel() {
        findMoviesViewModel?.apply {
            disposable.add(states().subscribe { render(it) })
            processIntent(intents())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.find_movies_menu, menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(text: String?): Boolean {
                searchFilterIntentPublisher.onNext(SearchFilterIntent(text ?: ""))
                return false
            }
        })

        return true
    }

    override fun render(state: MoviesUiState) {
        setScreenForLoading(state.isLoading)
        setScreenForInstructions(state.withSearchInstructions)
        setScreenForError(state.withError, state.errorMessage)
        setScreenForEmptyListOfMovies(state.thereAreNotMoviesMatches)
        setScreenForDisplayMovies(state.movies)
    }

    private fun setScreenForLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                pbDisplayMovies.visibility = View.VISIBLE
            } else {
                pbDisplayMovies.visibility = View.GONE
            }
        }
    }

    private fun setScreenForInstructions(withSearchInstructions: Boolean) {
        binding.apply {
            if (withSearchInstructions) {
                acivSearchDisplayMovies.visibility = View.VISIBLE
                tvInstructions.visibility = View.VISIBLE
            } else {
                acivSearchDisplayMovies.visibility = View.GONE
                tvInstructions.visibility = View.GONE
            }
        }
    }

    private fun setScreenForError(thereIsAnError: Boolean, errorMessage: String) {
        binding.apply {
            if (thereIsAnError) {
                ivError.visibility = View.VISIBLE
                tvError.text = getString(R.string.something_went_wrong, errorMessage)
                tvError.visibility = View.VISIBLE
            } else {
                ivError.visibility = View.GONE
                tvError.visibility = View.GONE
            }
        }
    }

    private fun setScreenForEmptyListOfMovies(thereAreNotMoviesMatches: Boolean) {
        binding.apply {
            if (thereAreNotMoviesMatches) {
                ivEmptyList.visibility = View.VISIBLE
                tvEmptyList.visibility = View.VISIBLE
            } else {
                ivEmptyList.visibility = View.GONE
                tvEmptyList.visibility = View.GONE
            }
        }
    }

    private fun setScreenForDisplayMovies(movies: List<PresentationMovie>) {
        binding.apply {
            if (movies.isEmpty()) {
                rvDisplayMovies.visibility = View.GONE
            } else {
                displayMoviesAdapter.setData(movies.map { presentationMovie ->
                    with(mapper) { presentationMovie.fromPresentationToUi() }
                })
                rvDisplayMovies.visibility = View.VISIBLE
            }
        }
    }

    override fun intents(): Observable<MoviesIntent> = searchFilterIntent()
        .cast(MoviesIntent::class.java)

    private fun searchFilterIntent(): Observable<SearchFilterIntent> = searchFilterIntentPublisher

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpRecyclerView() {
        try {
            binding.rvDisplayMovies.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
            binding.rvDisplayMovies.itemAnimator = DefaultItemAnimator()
            binding.rvDisplayMovies.adapter = displayMoviesAdapter
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}
