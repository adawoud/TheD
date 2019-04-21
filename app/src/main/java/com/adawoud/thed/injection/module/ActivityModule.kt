package com.adawoud.thed.injection.module

import androidx.appcompat.app.AppCompatActivity
import com.adawoud.thed.injection.scope.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(private val appCompatActivity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun activity(): AppCompatActivity = appCompatActivity

}