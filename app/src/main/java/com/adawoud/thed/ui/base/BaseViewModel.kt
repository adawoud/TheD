package com.adawoud.thed.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.adawoud.thed.util.NavigationCommand
import com.adawoud.thed.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

/**
 * A base class for ViewModels in this project to extend to have easier access to a
 * {@link: CompositeDisposable} object with managed lifecycle
 * */
abstract class BaseViewModel : ViewModel() {
    private val internalNavigationCommand = SingleLiveEvent<NavigationCommand>()
    protected val disposables = CompositeDisposable()
    val navigationCommand: LiveData<NavigationCommand> = internalNavigationCommand

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    /**
     * Handles navigation commands from the ViewModel
     *
     * @param directions: the new destination*/
    protected fun navigate(directions: NavDirections) {
        internalNavigationCommand.postValue(NavigationCommand.To(directions))
    }

}