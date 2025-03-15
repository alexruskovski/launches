package com.alexruskovski.features.products.data.di

import com.alexruskovski.features.products.data.ProductsRepositoryImpl
import com.alexruskovski.features.products.data.remote.Api
import com.alexruskovski.features.products.domain.ProductsRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsDataModule {

    @Provides
    @Singleton
    fun providesApi(
        okHttpClient: OkHttpClient,
        json: Json,
    ): Api = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://cdn.develop.gymshark.com/training/mock-product-responses/") //TODO move this to the build config
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(Api::class.java)

    @Provides
    @Singleton
    fun providesJsonSerializer() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        //TODO re-enable this for debug version
//        if (BuildConfig.DEBUG) {
//            val interceptor = HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BASIC
//            }
//            client.addNetworkInterceptor(interceptor)
//        }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesProductsRepository(api: Api): ProductsRepository {
        return ProductsRepositoryImpl(api = api)
    }

}