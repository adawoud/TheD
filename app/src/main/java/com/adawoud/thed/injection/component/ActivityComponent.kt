package com.adawoud.thed.injection.component

import com.adawoud.thed.injection.module.ActivityModule
import com.adawoud.thed.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}