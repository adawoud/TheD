package com.adawoud.thed.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adawoud.thed.R
import com.adawoud.thed.data.model.ui.Product
import com.adawoud.thed.ui.base.BaseFragment
import com.adawoud.thed.util.exhaustive
import com.adawoud.thed.util.hide
import com.adawoud.thed.util.show
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.feed_fragment.*
import javax.inject.Inject

class ProductsFragment : BaseFragment() {
    @Inject lateinit var factory: ProductsViewModelFactory
    @Inject lateinit var picasso: Picasso
    private lateinit var viewModel: ProductsViewModel
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.feed_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductsRv()
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ProductsViewState.Loading -> renderLoadingState()
                is ProductsViewState.Error -> renderErrorState()
                is ProductsViewState.Success -> renderSuccessState(state.products)
                is ProductsViewState.Offline -> renderOfflineState()
            }.exhaustive
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ProductsViewModel::class.java)
        productsAdapter = ProductsAdapter(picasso) { product ->
            viewModel.onProductClicked(product)
        }
        observeNavigationCommands(viewModel, this)
    }

    private fun initProductsRv() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        rvProducts.apply {
            adapter = productsAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun renderLoadingState() {
        startLoading()
    }

    private fun renderErrorState() {

    }

    private fun renderSuccessState(products: List<Product>) {
        stopLoading()
        productsAdapter.submitList(products)
    }

    private fun renderOfflineState() {
        stopLoading()
        Snackbar.make(
            rvProducts,
            getString(R.string.error_no_connection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun startLoading() {
        rvProducts.hide()
        progressBar.show()
    }

    private fun stopLoading() {
        progressBar.hide()
        rvProducts.show()
    }

}