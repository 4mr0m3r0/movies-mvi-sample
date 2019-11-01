package com.tzion.domain

import com.tzion.domain.executor.ExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<TDomain, in Params> constructor(
    private val executionThread: ExecutionThread) {

    private val subscription = Disposables.empty()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Single<TDomain>

    open fun execute(params: Params? = null): Single<TDomain> =
        this.buildUseCaseObservable(params)
                .subscribeOn(executionThread.io())
                .observeOn(executionThread.mainThread())


}