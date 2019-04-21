package com.adawoud.thed.ui.base

import android.app.Application
import com.adawoud.thed.BuildConfig
import com.adawoud.thed.injection.component.ApplicationComponent
import com.adawoud.thed.injection.component.DaggerApplicationComponent
import com.adawoud.thed.injection.module.ApplicationModule
import timber.log.Timber
import timber.log.Timber.plant

class TheDApp : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        applicationComponent
        super.onCreate()
        plantTimber()
    }

    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }

}