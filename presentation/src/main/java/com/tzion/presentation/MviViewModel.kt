package com.tzion.presentation

import io.reactivex.Observable

interface MviViewModel<TIntent, TUiState> {

    fun processIntent(intents: Observable<TIntent>)

    fun states(): Observable<TUiState>

}