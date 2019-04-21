package com.adawoud.thed.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteStore @Inject constructor(
    private val theDService: TheDService
) {

    fun products() = theDService.products()
        .map { response -> response.data }


    /**
     * We could use the same endpoint above for getting the product details, and pass
     * them to the Detail screen using arguments since they're only a few text fields,
     * but this solution is more general, and also future-proof.
     * */
    fun productDetails() = theDService.products()
        .map { response -> response.data }

}