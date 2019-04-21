package com.adawoud.thed.ui.products

import com.adawoud.thed.data.model.ui.Product

sealed class ProductsViewState {

    /**
     * This should probably be wrapped in a {@link SingleLiveEvent }, but since
     * we're subscribed to the connection state using, an Observable, this
     * should not last if the connectivity state changes
     */
    object Offline : ProductsViewState()

    object Loading : ProductsViewState()

    object Error : ProductsViewState()

    data class Success(val products: List<Product>) : ProductsViewState()

}