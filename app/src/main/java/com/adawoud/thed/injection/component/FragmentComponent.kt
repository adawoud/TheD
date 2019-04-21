package com.adawoud.thed.injection.component

import com.adawoud.thed.injection.module.FragmentModule
import com.adawoud.thed.ui.product.ProductDetailFragment
import com.adawoud.thed.ui.products.ProductsFragment
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(productsFragment: ProductsFragment)

    fun inject(productsFragment: ProductDetailFragment)

}