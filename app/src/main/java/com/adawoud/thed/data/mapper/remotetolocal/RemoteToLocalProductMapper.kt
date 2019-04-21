package com.adawoud.thed.data.mapper.remotetolocal

import com.adawoud.thed.data.mapper.Mapper
import com.adawoud.thed.data.model.local.Product
import com.adawoud.thed.data.model.remote.Data
import dagger.Reusable
import javax.inject.Inject

/**
 * Maps the remote product response to its local database representation
 * */
@Reusable
class RemoteToLocalProductMapper @Inject constructor() :
    Mapper<Data, Product> {

    override fun map(from: Data) = Product(
        productId = from.id,
        title = from.name,
        price = from.price,
        thumbnailUrl = from.image.link
    )

}