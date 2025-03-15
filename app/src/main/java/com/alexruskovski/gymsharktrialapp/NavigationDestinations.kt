package com.alexruskovski.gymsharktrialapp

import kotlinx.serialization.Serializable

sealed class NavigationDestinations {
    @Serializable
    data object ProductList : NavigationDestinations()

    @Serializable
    data class ProductDetails(
        val productId: Long,
    ) : NavigationDestinations()
}
