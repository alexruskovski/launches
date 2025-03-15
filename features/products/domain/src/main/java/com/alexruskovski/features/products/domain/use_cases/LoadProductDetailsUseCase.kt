package com.alexruskovski.features.products.domain.use_cases

import com.alexruskovski.core.data.NetworkResult
import com.alexruskovski.features.products.domain.ProductDomain
import com.alexruskovski.features.products.domain.ProductsRepository
import javax.inject.Inject

interface LoadProductDetailsUseCase {
    suspend fun loadProductDetails(productId: Long): NetworkResult<ProductDomain>
}

class LoadProductDetailsUseCaseImpl(
    private val productsRepository: ProductsRepository,
) : LoadProductDetailsUseCase {
    override suspend fun loadProductDetails(productId: Long): NetworkResult<ProductDomain> =
        productsRepository.loadProductDetails(productId)
}
