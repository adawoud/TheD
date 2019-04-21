package com.adawoud.thed.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.adawoud.thed.R
import com.adawoud.thed.data.model.ui.ProductDetail
import com.adawoud.thed.ui.base.BaseFragment
import com.adawoud.thed.util.exhaustive
import com.adawoud.thed.util.hide
import com.adawoud.thed.util.show
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_detail_fragment.*
import javax.inject.Inject

class ProductDetailFragment : BaseFragment() {
    @Inject lateinit var factory: ProductDetailViewModelFactory
    @Inject lateinit var picasso: Picasso
    private lateinit var viewModel: ProductDetailViewModel
    private val args: ProductDetailFragmentArgs by navArgs()
    private val keyProductId = "keyProductId"
    private var offlineSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.product_detail_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ProductDetailViewState.Loading -> renderLoadingState()
                is ProductDetailViewState.Error -> renderErrorState()
                is ProductDetailViewState.Success -> renderSuccessState(state.product)
                is ProductDetailViewState.Offline -> renderOfflineState()
            }.exhaustive
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
            .get(ProductDetailViewModel::class.java)
        when {
            savedInstanceState != null -> {
                viewModel.productDetail(savedInstanceState.getInt(keyProductId))
            }
            else -> {
                viewModel.productDetail(args.productId)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(keyProductId, args.productId)
    }

    private fun renderLoadingState() {
        startLoading()
    }

    private fun renderErrorState() {

    }

    private fun renderSuccessState(product: ProductDetail) {
        stopLoading()
        offlineSnackbar?.let { snackbar ->
            if (snackbar.isShown) snackbar.dismiss()
        }
        picasso.load(product.thumbnailUrl).into(ivThumbnail)
        tvTitle.text = product.name
        tvPrice.text = resources.getString(R.string.price, product.price)
        tvDescription.text = product.productDescription
    }

    private fun renderOfflineState() {
        stopLoading()
        offlineSnackbar = Snackbar
            .make(
                tvDescription,
                getString(R.string.error_no_connection),
                Snackbar.LENGTH_INDEFINITE
            )
            .setAction(getString(R.string.action_retry)) {
                viewModel.onRetryButtonClicked(args.productId)
            }
        offlineSnackbar?.show()
    }

    private fun startLoading() {
        progressBar.show()
    }

    private fun stopLoading() {
        progressBar.hide()
    }

}