package com.adawoud.thed.util

import androidx.navigation.NavDirections

/**
 * A representation of Navigation Destinations
 * */
sealed class NavigationCommand {

    object Back : NavigationCommand()

    data class BackTo(val destinationId: Int) : NavigationCommand()

    data class To(val directions: NavDirections) : NavigationCommand()

}