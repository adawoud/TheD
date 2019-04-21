package com.adawoud.thed.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adawoud.thed.data.repository.Repository
import com.adawoud.thed.util.RxSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsViewModelFactory @Inject constructor(
    private val schedulers: RxSchedulers,
    private val repository: Repository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ProductsViewModel::class.java) ->
                ProductsViewModel(schedulers, repository) as T
            else -> throw IllegalArgumentException("ViewModel not found")
        }

}