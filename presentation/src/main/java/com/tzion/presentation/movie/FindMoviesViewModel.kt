package com.tzion.presentation.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tzion.domain.exception.NoMoviesResultsException
import com.tzion.presentation.MviViewModel
import com.tzion.presentation.movie.action.MoviesAction
import com.tzion.presentation.movie.action.MoviesAction.FindMoviesByTextAction
import com.tzion.presentation.movie.intent.MoviesIntent
import com.tzion.presentation.movie.intent.MoviesIntent.SearchFilterIntent
import com.tzion.presentation.movie.mapper.PresentationMovieMapper
import com.tzion.presentation.movie.processor.MoviesActionProcessor
import com.tzion.presentation.movie.result.MoviesResult
import com.tzion.presentation.movie.result.MoviesResult.FindMoviesByTextResult
import com.tzion.presentation.movie.state.MoviesUiState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

open class FindMoviesViewModel @Inject constructor(
    private val moviesActionProcessor: MoviesActionProcessor,
    private val mapper: PresentationMovieMapper)
    : ViewModel(), MviViewModel<MoviesIntent, MoviesUiState> {

    private val intentsSubject: PublishSubject<MoviesIntent> = PublishSubject.create()
    private val uiStatesObservable: Observable<MoviesUiState> = compose()

    //TODO: another solution variant, using ViewModel LiveData and subscribe
//    private val disposables = CompositeDisposable()
//    private val liveDataUiState = MutableLiveData<MoviesUiState>()

//    override fun onCleared() {
//        disposables.dispose()
//        super.onCleared()
//    }

//    fun liveData(): LiveData<MoviesUiState> = MediatorLiveData<MoviesUiState>().apply {
//        addSource(liveDataUiState) { uiState ->
//            value = uiState
//        }
//    }

//    init {
//        bindUiStates()
//    }

//    private fun bindUiStates() {
//        disposables += intentsSubject
//            .map { intent -> actionFromIntent(intent) }
//            .compose(moviesActionProcessor.actionProcessor)
//            .scan(MoviesUiState.Default, reducer)
//            .distinctUntilChanged()
//            .replay(1)
//            .autoConnect(0)
//            .subscribe(liveDataUiState::setValue)
//    }

    private fun compose(): Observable<MoviesUiState> = intentsSubject
        .map { intent -> actionFromIntent(intent) }
        .compose(moviesActionProcessor.actionProcessor)
        .scan(MoviesUiState.Default, reducer)
        .distinctUntilChanged()
        .replay(1)
        .autoConnect(0)

    private val reducer: BiFunction<MoviesUiState, MoviesResult, MoviesUiState>
        get() = BiFunction { previousUiState: MoviesUiState, result: MoviesResult ->
            when(result) {
                is FindMoviesByTextResult -> when(result) {
                    is FindMoviesByTextResult.Success -> {
                        if (result.movies.isEmpty()) {
                            MoviesUiState.EmptyList
                        } else {
                            MoviesUiState.Success(result.movies.map { movie ->
                                with(mapper) { movie.fromDomainToPresentation() }
                            })
                        }
                    }
                    is FindMoviesByTextResult.Error -> {
                        if (result.error is NoMoviesResultsException) {
                            MoviesUiState.EmptyList
                        } else {
                            MoviesUiState.Error(result.error.localizedMessage)
                        }
                    }
                    FindMoviesByTextResult.InProcess -> MoviesUiState.Loading
                }
            }
        }

    override fun processIntent(intents: Observable<MoviesIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<MoviesUiState> = uiStatesObservable

    private fun actionFromIntent(intent: MoviesIntent): MoviesAction =
        when(intent) {
            is SearchFilterIntent   -> FindMoviesByTextAction(intent.queryText)
        }


    /**
     * take only the first ever InitialIntent and all intents of other types
     * to avoid reloading data on config changes
     */
//    private val intentFilter: ObservableTransformer<MoviesIntent, MoviesIntent> =
//        ObservableTransformer { intents ->
//            intents.publish { shared ->
//                shared.ofType(InitialIntent::class.java).take(1)
//                shared.filter { intent -> intent !is InitialIntent }
//            }
//        }


}