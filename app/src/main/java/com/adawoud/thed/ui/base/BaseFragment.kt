package com.adawoud.thed.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.adawoud.thed.injection.component.FragmentComponent
import com.adawoud.thed.injection.module.FragmentModule
import com.adawoud.thed.util.NavigationCommand

/**
 * A base class for Fragments in this project to extend to have easier access to the
 * {@link: FragmentComponent} object
 * */
abstract class BaseFragment : Fragment() {
    protected val fragmentComponent: FragmentComponent by lazy {
        (requireActivity().application as TheDApp)
            .applicationComponent
            .plus(FragmentModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent
        super.onCreate(savedInstanceState)
    }

    /**
     * Handles navigation commands coming from the ViewModel
     *
     * @param viewModel: the associated ViewModel of a Fragment
     * @param owner: a lifecycle owner, most likely the fragment itself*/
    protected fun observeNavigationCommands(viewModel: BaseViewModel, owner: LifecycleOwner) {
        viewModel.navigationCommand.observe(owner, Observer<NavigationCommand?> { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().navigateUp()
                is NavigationCommand.BackTo -> findNavController().navigate(command.destinationId)
            }
        })
    }

}