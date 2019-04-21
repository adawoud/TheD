package com.adawoud.thed.data.local

import com.adawoud.thed.data.mapper.localtoui.LocalToUiProductDetailMapper
import com.adawoud.thed.data.mapper.localtoui.LocalToUiProductMapper
import com.adawoud.thed.data.model.ui.Product
import com.adawoud.thed.data.model.ui.ProductDetail
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStore @Inject constructor(
    private val localToUiProductMapper: LocalToUiProductMapper,
    private val localToUiProductDetailMapper: LocalToUiProductDetailMapper,
    private val productDao: ProductDao,
    private val productDetailDao: ProductDetailDao
) {

    /**
     * Get a list of products stored locally and map them to their UI representation
     *
     * @return a list of products
     * */
    fun products(): Single<List<Product>> =
        productDao.products()
            .map { data ->
                return@map data.map { product -> localToUiProductMapper.map(product) }
            }

    /**
     * Insert a list of products into the database
     *
     * */
    fun insertProducts(products: List<com.adawoud.thed.data.model.local.Product>) =
        productDao.insertProducts(products)

    /**
     * Deletes all records from the products table
     * */
    fun deleteProducts() = productDao.nukeProducts()

    /**
     * Get the details of a product stored locally and map them to their UI representation
     *
     * @param productId: id of the product in question
     * @return product details
     * */
    fun productDetail(productId: Int): Single<ProductDetail> =
        productDetailDao.productDetail(productId)
            .map { product -> localToUiProductDetailMapper.map(product) }

    /**
     * Insert a product details object, or any array of them into the database
     *
     * */
    fun insertProductDetails(vararg productDetails: com.adawoud.thed.data.model.local.ProductDetail) =
        productDetailDao.insertProductDetails(*productDetails)

    /**
     * Deletes all records from the product details table
     * */
    fun deleteProductDetails() = productDetailDao.nukeProductDetails()

}