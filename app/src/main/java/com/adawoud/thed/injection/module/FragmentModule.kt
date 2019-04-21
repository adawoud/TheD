package com.adawoud.thed.injection.module

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule constructor(private val fragment: Fragment) {

    @Provides
    fun fragment() = fragment

}