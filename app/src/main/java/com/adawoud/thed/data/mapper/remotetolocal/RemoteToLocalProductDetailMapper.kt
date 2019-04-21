package com.adawoud.thed.data.mapper.remotetolocal

import com.adawoud.thed.data.mapper.Mapper
import com.adawoud.thed.data.model.local.ProductDetail
import com.adawoud.thed.data.model.remote.Data
import dagger.Reusable
import javax.inject.Inject

/**
 * Maps the remote product details response to its local database representation
 * */
@Reusable
class RemoteToLocalProductDetailMapper @Inject constructor() : Mapper<Data, ProductDetail> {

    override fun map(from: Data) = ProductDetail(
        productId = from.id,
        title = from.name,
        price = from.price,
        productDescription = from.productDescription,
        thumbnailUrl = from.image.link
    )

}