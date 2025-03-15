package com.alexruskovski.features.products.data.remote

import com.alexruskovski.features.products.data.model.ProductsResponse
import retrofit2.http.GET

interface Api {

    @GET("algolia-example-payload.json")
    suspend fun getProducts(): ProductsResponse

}