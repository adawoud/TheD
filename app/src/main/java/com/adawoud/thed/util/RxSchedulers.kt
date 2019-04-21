package com.adawoud.thed.util

import dagger.Reusable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * A wrapper around the RxJava {@link Schedulers} to make it easier to just use
 * the trampoline scheduler in tests.
 * */
@Reusable
class RxSchedulers @Inject constructor() {

    fun single() = Schedulers.single()

    fun computation() = Schedulers.computation()

    fun io() = Schedulers.io()

    fun trampoline() = Schedulers.trampoline()

    fun newThread() = Schedulers.newThread()

    fun main(): Scheduler = AndroidSchedulers.mainThread()

}