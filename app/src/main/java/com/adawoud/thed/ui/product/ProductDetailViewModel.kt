package com.adawoud.thed.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.EmptyResultSetException
import com.adawoud.thed.data.repository.Repository
import com.adawoud.thed.ui.base.BaseViewModel
import com.adawoud.thed.ui.product.ProductDetailViewState.*
import com.adawoud.thed.util.RxSchedulers
import timber.log.Timber
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val schedulers: RxSchedulers,
    private val repository: Repository
) : BaseViewModel() {
    private val internalState = MutableLiveData<ProductDetailViewState>()
    val state: LiveData<ProductDetailViewState> = internalState

    fun productDetail(productId: Int) {
        repository.productDetail(productId)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .doOnSubscribe {
                internalState.value = Loading
            }
            .subscribe({ product ->
                internalState.value = Success(product)
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

    fun onRetryButtonClicked(productId: Int) {
        productDetail(productId)
    }

}