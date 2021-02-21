package com.tzion.presentation.movie.processor

import com.tzion.domain.executor.ExecutionThread
import com.tzion.domain.movie.FindMoviesUseCase
import com.tzion.presentation.movie.action.MoviesAction
import com.tzion.presentation.movie.action.MoviesAction.FindMoviesByTextAction
import com.tzion.presentation.movie.result.MoviesResult
import com.tzion.presentation.movie.result.MoviesResult.FindMoviesByTextResult
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class MoviesActionProcessor @Inject constructor(
    private val findMoviesUseCase: FindMoviesUseCase,
    private val executionThread: ExecutionThread
) {

    private val findMoviesByTextProcessor =
        ObservableTransformer<FindMoviesByTextAction, FindMoviesByTextResult> { actions ->
            actions.switchMap { action ->
                findMoviesUseCase
                    .execute(FindMoviesUseCase.Params(action.queryText))
                    .toObservable()
                    .map { movies ->
                        FindMoviesByTextResult.Success(movies)
                    }
                    .cast(FindMoviesByTextResult::class.java)
                    .onErrorReturn(FindMoviesByTextResult::Error)
                    .startWith(FindMoviesByTextResult.InProcess)
                    .subscribeOn(executionThread.io())
                    .observeOn(executionThread.mainThread())
            }
        }

    var actionProcessor: ObservableTransformer<MoviesAction, MoviesResult>

    init {
        actionProcessor = ObservableTransformer { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared
                        .ofType(FindMoviesByTextAction::class.java)
                        .compose(findMoviesByTextProcessor),
                    shared
                        .filter { action -> action !is FindMoviesByTextAction }
                        .flatMap {
                            Observable.error<MoviesResult>(
                                IllegalArgumentException("Unknown Action type: $it")
                            )
                        }
                )
            }
        }
    }

}