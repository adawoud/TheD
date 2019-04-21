package com.adawoud.thed.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteStore @Inject constructor(
    private val theDService: TheDService
) {

    fun products() = theDService.products()
        .map { response -> response.data }

    fun productDetails() = theDService.products()
        .map { response -> response.data }

}