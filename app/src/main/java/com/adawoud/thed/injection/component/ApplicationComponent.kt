package com.adawoud.thed.injection.component

import com.adawoud.thed.injection.module.ActivityModule
import com.adawoud.thed.injection.module.ApplicationModule
import com.adawoud.thed.injection.module.DataModule
import com.adawoud.thed.injection.module.FragmentModule
import com.adawoud.thed.ui.base.TheDApp
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class, DataModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(app: TheDApp)

    fun plus(activityModule: ActivityModule): ActivityComponent

    fun plus(fragmentModule: FragmentModule): FragmentComponent

}