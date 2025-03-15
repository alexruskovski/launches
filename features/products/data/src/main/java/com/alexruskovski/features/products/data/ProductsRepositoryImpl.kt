package com.alexruskovski.features.products.data

import com.alexruskovski.core.data.NetworkResult
import com.alexruskovski.features.products.data.mapper.toProductDomain
import com.alexruskovski.features.products.data.remote.Api
import com.alexruskovski.features.products.domain.ProductDomain
import com.alexruskovski.features.products.domain.ProductsRepository
import kotlinx.coroutines.ensureActive
import java.net.UnknownHostException
import kotlin.coroutines.coroutineContext

class ProductsRepositoryImpl(
    private val api: Api,
) : ProductsRepository {

    private val inMemoryCache = mutableListOf<ProductDomain>()

    override suspend fun loadProducts(): NetworkResult<List<ProductDomain>> {
        return try {
            NetworkResult.Success(
                api.getProducts()
                    .hits
                    .map { it.toProductDomain() }
                    .also {
                        inMemoryCache.clear()
                        inMemoryCache.addAll(it)
                    }
            )
        } catch (e: UnknownHostException) {
            NetworkResult.Error(e)
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            NetworkResult.Error(e)
        }
    }

    override suspend fun loadProductDetails(id: Long): NetworkResult<ProductDomain> {
        val product = inMemoryCache.find { it.id == id }
        return if (product != null) {
            NetworkResult.Success(product)
        } else {
            NetworkResult.Error(Exception("Product not found"))
        }
    }
}