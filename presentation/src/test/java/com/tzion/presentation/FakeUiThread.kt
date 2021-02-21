package com.tzion.presentation

import com.tzion.domain.executor.ExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FakeUiThread @Inject constructor(): ExecutionThread {

    override fun mainThread(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()
}