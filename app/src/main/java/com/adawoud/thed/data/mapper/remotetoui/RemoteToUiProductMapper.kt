package com.adawoud.thed.data.mapper.remotetoui

import com.adawoud.thed.data.mapper.Mapper
import com.adawoud.thed.data.model.remote.Data
import com.adawoud.thed.data.model.ui.Product
import dagger.Reusable
import javax.inject.Inject

/**
 * Maps the remote product response to its UI representation
 * */
@Reusable
class RemoteToUiProductMapper @Inject constructor() :
    Mapper<Data, Product> {

    override fun map(from: Data) = Product(
        id = from.id,
        title = from.name,
        price = from.price,
        thumbnailUrl = from.image.link
    )

}