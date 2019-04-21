package com.adawoud.thed.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.EmptyResultSetException
import com.adawoud.thed.data.model.ui.Product
import com.adawoud.thed.data.repository.Repository
import com.adawoud.thed.ui.base.BaseViewModel
import com.adawoud.thed.ui.products.ProductsViewState.*
import com.adawoud.thed.util.RxSchedulers
import timber.log.Timber
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    schedulers: RxSchedulers,
    repository: Repository
) : BaseViewModel() {
    private val internalState = MutableLiveData<ProductsViewState>()
    val state: LiveData<ProductsViewState> = internalState

    init {
        repository.products()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .doOnSubscribe {
                internalState.value = Loading
            }
            .subscribe({ products ->
                /*if the response is null, then we probably haven't fetched it from the network
                and stored it offline yet. This most likely means you're offline'*/
                when (products) {
                    null -> internalState.value = Offline
                    else -> internalState.value = Success(products)
                }
            }, {
                when (it) {
                    /**
                     * {@link: EmptyResultSetException} is the error that Room returns when
                     * a query that returns a Single<T> doesn't find the record in the database
                     *
                     * <p>
                     * Since our implementation is that we always hit the network if the user
                     * is online and cache the result to the database, having this error
                     * means that the app was never able to cache this value, so the device
                     * is offline
                     * <p]>
                     *
                     * @see: https://medium.com/androiddevelopers/room-rxjava-acb0cd4f3757
                     * */
                    is EmptyResultSetException -> internalState.value = Offline
                    else -> internalState.value = Error
                }
                Timber.d(it)
            })
            .let(disposables::add)
    }

    fun onProductClicked(product: Product) {
        navigate(
            ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(
                product.id,
                product.title
            )
        )
    }

}