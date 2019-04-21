package com.adawoud.thed.data.repository

import com.adawoud.thed.data.local.LocalStore
import com.adawoud.thed.data.mapper.remotetolocal.RemoteToLocalProductDetailMapper
import com.adawoud.thed.data.mapper.remotetolocal.RemoteToLocalProductMapper
import com.adawoud.thed.data.mapper.remotetoui.RemoteToUiProductDetailMapper
import com.adawoud.thed.data.mapper.remotetoui.RemoteToUiProductMapper
import com.adawoud.thed.data.model.ui.Product
import com.adawoud.thed.data.model.ui.ProductDetail
import com.adawoud.thed.data.remote.RemoteStore
import com.adawoud.thed.util.ConnectivityChecker
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val connectivityChecker: ConnectivityChecker,
    private val remoteToLocalProductMapper: RemoteToLocalProductMapper,
    private val remoteToLocalProductDetailMapper: RemoteToLocalProductDetailMapper,
    private val remoteToUiProductMapper: RemoteToUiProductMapper,
    private val remoteToUiProductDetailMapper: RemoteToUiProductDetailMapper,
    private val remoteStore: RemoteStore,
    private val localStore: LocalStore
) {

    /**
     * Get a list of products, either from the server or from local cache.
     *
     * The current implementation checks if the user is connected to the Internet, and if so,
     * it always fetches data from the server, otherwise, it serves locally stored ones.
     *
     * @return a list of products
     * */
    fun products(): Observable<List<Product>> {
        return connectivityChecker.isConnected()
            .flatMapSingle { connectivity ->
                when {
                    connectivity.available() -> remoteProducts()
                    else -> cachedProducts()
                }
            }
    }

    /**
     * Get the details of a product, either from the server or from local cache.
     *
     * The current implementation checks if the user is connected to the Internet, and if so,
     * it always fetches data from the server, otherwise, it serves from the local storage.
     *
     * @param productId: the id of the product we'd like to fetch the details of
     * @return product details
     * */
    fun productDetail(productId: Int): Observable<ProductDetail> {
        return connectivityChecker.isConnected()
            .flatMapSingle { connectivity ->
                when {
                    connectivity.available() -> remoteProductDetails(productId)
                    else -> cachedProductDetail(productId)
                }
            }
    }


    /**
     * Get a list of products from the server, store them locally if the call is successful,
     * then map the response to UI elements
     *
     * We do this here instead of in the {@link: RemoteStore} class to avoid
     * creating a direct dependency on the {@link: LocalStore} there.
     *
     * This is probably a mess and should be refactored into something better
     *
     * @return a list of products
     * */
    private fun remoteProducts(): Single<List<Product>> = remoteStore.products()
        .doOnSuccess { data ->
            val localProducts = data.map { remoteToLocalProductMapper.map(it) }
            localStore.insertProducts(localProducts)
        }
        .map { data ->
            return@map data.map { remoteItem ->
                remoteToUiProductMapper.map(remoteItem)
            }
        }

    /**
     * Get the details of a product from the server, store them locally if the call is successful,
     * then map the response to UI elements
     *
     * We do this here instead of in the {@link: RemoteStore} class to avoid
     * creating a direct dependency on the {@link: LocalStore} there.
     *
     * @param productId: the id of the product we'd like to fetch the details of
     * @return product details
     * */
    private fun remoteProductDetails(productId: Int): Single<ProductDetail> =
        remoteStore.productDetails()
            .map { data -> data.find { it.id == productId } }
            .doOnSuccess { productDetails ->
                productDetails?.let {
                    localStore.insertProductDetails(remoteToLocalProductDetailMapper.map(it))
                }
            }
            .map { productDetails -> remoteToUiProductDetailMapper.map(productDetails) }

    /**
     * Get a list of locally stored products, with the response already mapped to UI elements
     *
     * @return a list of products
     * */
    private fun cachedProducts() = localStore.products()

    /**
     * Get the details of a locally stored product, with the response already mapped to UI elements
     *
     * @param productId: the id of the product we'd like to fetch the details of
     * @return product details
     * */
    private fun cachedProductDetail(productId: Int) = localStore.productDetail(productId)

}