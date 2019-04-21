package com.adawoud.thed.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.adawoud.thed.R
import com.adawoud.thed.data.model.ui.Product
import com.squareup.picasso.Picasso

class ProductsAdapter constructor(
    private val picasso: Picasso,
    private val itemClickListener: (Product) -> Unit
) :
    ListAdapter<Product, ProductViewHolder>(object : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView, picasso)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, itemClickListener)
    }

}