package com.adawoud.thed.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductDetail(
    @PrimaryKey val productId: Int,
    val thumbnailUrl: String,
    val title: String,
    val price: Int,
    val productDescription: String
)