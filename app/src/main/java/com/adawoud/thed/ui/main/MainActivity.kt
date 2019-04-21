package com.adawoud.thed.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.adawoud.thed.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var onNavigatedListener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.navHostFragment)
        toolbar.setupWithNavController(navController)
        /**
         * Changes the Toolbar title to the name of the Product in the
         * {@link ProductDetailFragment}
         * */
        onNavigatedListener =
            NavController.OnDestinationChangedListener { _, destination, arguments ->
                when (destination.id) {
                    R.id.productDetailFragment -> {
                        toolbar.title = arguments?.getString("productName")
                    }
                }
            }
    }

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(onNavigatedListener)
    }

    override fun onStop() {
        super.onStop()
        navController.removeOnDestinationChangedListener(onNavigatedListener)
    }

}