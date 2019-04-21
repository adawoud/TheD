package com.adawoud.thed.data.mapper.localtoui

import com.adawoud.thed.data.mapper.Mapper
import com.adawoud.thed.data.model.ui.ProductDetail
import dagger.Reusable
import javax.inject.Inject

/**
 * Maps the local product details response to its UI representation
 * */
@Reusable
class LocalToUiProductDetailMapper @Inject constructor() :
    Mapper<com.adawoud.thed.data.model.local.ProductDetail, ProductDetail> {

    override fun map(from: com.adawoud.thed.data.model.local.ProductDetail) = ProductDetail(
        id = from.productId,
        name = from.title,
        price = from.price,
        productDescription = from.productDescription,
        thumbnailUrl = from.thumbnailUrl

    )
}