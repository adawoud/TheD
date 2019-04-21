package com.adawoud.thed.ui.products

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adawoud.thed.R
import com.adawoud.thed.data.model.ui.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductViewHolder constructor(itemView: View, private val picasso: Picasso) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(product: Product, itemClickListener: (Product) -> Unit) {
        itemView.setOnClickListener { itemClickListener(product) }
        picasso
            .load(product.thumbnailUrl)
            .error(R.drawable.ic_launcher_background)
            .into(itemView.ivThumbnail)
        //Glide.with(itemView).load(productDetails.thumbnailUrl).into(itemView.ivThumbnail)
        itemView.tvTitle.text = product.title
        itemView.tvPrice.text = itemView.resources.getString(R.string.price, product.price)
    }

}