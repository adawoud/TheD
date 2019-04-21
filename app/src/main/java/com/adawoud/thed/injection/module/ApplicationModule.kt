package com.adawoud.thed.injection.module

import android.app.Application
import com.adawoud.thed.injection.scope.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule constructor(private val application: Application) {

    @Provides
    @Singleton
    @ApplicationContext
    fun application(): Application = application

}