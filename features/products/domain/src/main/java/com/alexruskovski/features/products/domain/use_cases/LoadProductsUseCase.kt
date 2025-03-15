package com.alexruskovski.features.products.domain.use_cases

import com.alexruskovski.core.data.NetworkResult
import com.alexruskovski.features.products.domain.ProductDomain
import com.alexruskovski.features.products.domain.ProductsRepository
import javax.inject.Inject

interface LoadProductsUseCase {
    suspend fun loadProducts(): NetworkResult<List<ProductDomain>>
}

class LoadProductsUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
) : LoadProductsUseCase {
    override suspend fun loadProducts(): NetworkResult<List<ProductDomain>> =
        productsRepository.loadProducts()
}