package com.alexruskovski.gymsharktrialapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alexruskovski.features.products.presentation.details.ProductDetails
import com.alexruskovski.features.products.presentation.list.ProductsList

@Composable
fun GymSharkTrialApp(
    innerPadding: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestinations.ProductList,
    ) {
        composable<NavigationDestinations.ProductList> {
            ProductsList(
                modifier = Modifier.padding(innerPadding),
                onProductClick = {
                    navController.navigate(
                        route = NavigationDestinations.ProductDetails(
                            productId = it.id
                        )
                    )
                },
            )
        }
        composable<NavigationDestinations.ProductDetails> {
            val product = it.toRoute<NavigationDestinations.ProductDetails>()
            ProductDetails(
                modifier = Modifier.padding(innerPadding),
                productId = product.productId,
                onNavigateBack = {
                    navController.popBackStack(NavigationDestinations.ProductList, false)
                },
                onBuyNowClick = {

                }
            )
        }
    }
}