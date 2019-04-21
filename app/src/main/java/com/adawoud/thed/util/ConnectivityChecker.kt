package com.adawoud.thed.util

import android.app.Application
import com.adawoud.thed.injection.scope.ApplicationContext
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.Reusable
import io.reactivex.Observable
import javax.inject.Inject

@Reusable
class ConnectivityChecker @Inject constructor(
    @ApplicationContext private val context: Application
) {

    /**
     * An observable that emits connectivity changes
     * */
    fun isConnected(): Observable<Connectivity> =
        ReactiveNetwork.observeNetworkConnectivity(context)

}