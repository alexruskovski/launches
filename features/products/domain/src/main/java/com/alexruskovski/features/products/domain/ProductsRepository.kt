package com.alexruskovski.features.products.domain

import com.alexruskovski.core.data.NetworkResult

interface ProductsRepository {

    suspend fun loadProducts(): NetworkResult<List<ProductDomain>>

    suspend fun loadProductDetails(id: Long): NetworkResult<ProductDomain>

}