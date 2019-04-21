package com.adawoud.thed.data.model.remote

data class GetProductsResponse(
    val count: Int,
    val data: List<Data>
)