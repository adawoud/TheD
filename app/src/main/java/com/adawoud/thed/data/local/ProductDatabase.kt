package com.adawoud.thed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adawoud.thed.data.model.local.Product
import com.adawoud.thed.data.model.local.ProductDetail

@Database(entities = [Product::class, ProductDetail::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun productDetailDao(): ProductDetailDao

}