package com.adawoud.thed.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

val <T> T.exhaustive: T
    get() = this

fun View.show() {
    visibility = VISIBLE
}

fun View.hide() {
    visibility = GONE
}