package com.adawoud.thed.data.mapper.remotetoui

import com.adawoud.thed.data.mapper.Mapper
import com.adawoud.thed.data.model.remote.Data
import com.adawoud.thed.data.model.ui.ProductDetail
import dagger.Reusable
import javax.inject.Inject

/**
 * Maps the remote product details response to its UI representation
 * */
@Reusable
class RemoteToUiProductDetailMapper @Inject constructor() :
    Mapper<Data, ProductDetail> {

    override fun map(from: Data) = ProductDetail(
        id = from.id,
        name = from.name,
        price = from.price,
        productDescription = from.productDescription,
        thumbnailUrl = from.image.link
    )

}