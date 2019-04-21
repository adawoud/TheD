package com.adawoud.thed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adawoud.thed.data.model.local.ProductDetail
import io.reactivex.Single

@Dao
interface ProductDetailDao {

    @Query("SELECT * FROM ProductDetail WHERE productId = :id")
    fun productDetail(id: Int): Single<ProductDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductDetails(vararg productDetail: ProductDetail)

    @Query("DELETE FROM ProductDetail")
    fun nukeProductDetails()

}