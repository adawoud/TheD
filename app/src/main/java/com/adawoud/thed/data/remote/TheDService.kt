package com.adawoud.thed.data.remote

import com.adawoud.thed.data.model.remote.GetProductsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface TheDService {

    @GET(".")
    fun products(): Single<GetProductsResponse>

}