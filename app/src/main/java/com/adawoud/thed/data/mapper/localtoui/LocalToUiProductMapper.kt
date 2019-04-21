package com.adawoud.thed.data.mapper.localtoui

import com.adawoud.thed.data.mapper.Mapper
import com.adawoud.thed.data.model.ui.Product
import dagger.Reusable
import javax.inject.Inject

/**
 * Maps the local product response to its UI representation
 * */
@Reusable
class LocalToUiProductMapper @Inject constructor() :
    Mapper<com.adawoud.thed.data.model.local.Product, Product> {

    override fun map(from: com.adawoud.thed.data.model.local.Product) = Product(
        id = from.productId,
        title = from.title,
        price = from.price,
        thumbnailUrl = from.thumbnailUrl
    )

}