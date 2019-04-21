package com.adawoud.thed.ui.product

import com.adawoud.thed.data.model.ui.ProductDetail

sealed class ProductDetailViewState {

    /**
     * This should probably be wrapped in a {@link SingleLiveEvent }, but since
     * we're subscribed to the connection state using, an Observable, this
     * should not last if the connectivity state changes
     */
    object Offline : ProductDetailViewState()

    object Loading : ProductDetailViewState()

    object Error : ProductDetailViewState()

    data class Success(val product: ProductDetail) : ProductDetailViewState()

}