package com.tzion.domain.executor

import io.reactivex.Scheduler

interface ExecutionThread {

    fun mainThread(): Scheduler

    fun io(): Scheduler

}