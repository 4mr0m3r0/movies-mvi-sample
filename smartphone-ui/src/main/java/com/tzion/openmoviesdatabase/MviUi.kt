package com.tzion.openmoviesdatabase

import io.reactivex.Observable

interface MviUi<TIntent, in TUiState> {

    fun intents(): Observable<TIntent>

    fun render(state: TUiState)

}