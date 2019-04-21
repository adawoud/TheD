package com.adawoud.thed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adawoud.thed.data.model.local.Product
import io.reactivex.Single

@Dao
interface ProductDao {

    @Query("SELECT * FROM PRODUCT")
    fun products(): Single<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<Product>)

    @Query("DELETE FROM Product")
    fun nukeProducts()

}