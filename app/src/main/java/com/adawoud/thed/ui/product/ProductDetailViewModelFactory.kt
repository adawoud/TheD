package com.adawoud.thed.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adawoud.thed.data.repository.Repository
import com.adawoud.thed.util.RxSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDetailViewModelFactory @Inject constructor(
    private val schedulers: RxSchedulers,
    private val repository: Repository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ProductDetailViewModel::class.java) ->
                ProductDetailViewModel(schedulers, repository) as T
            else -> throw IllegalArgumentException("ViewModel not found")
        }

}