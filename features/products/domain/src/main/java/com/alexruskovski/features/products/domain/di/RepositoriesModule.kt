package com.alexruskovski.features.products.domain.di

import com.alexruskovski.features.products.domain.ProductsRepository
import com.alexruskovski.features.products.domain.use_cases.LoadProductDetailsUseCase
import com.alexruskovski.features.products.domain.use_cases.LoadProductDetailsUseCaseImpl
import com.alexruskovski.features.products.domain.use_cases.LoadProductsUseCase
import com.alexruskovski.features.products.domain.use_cases.LoadProductsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    @Singleton
    fun providesLoadProductDetailsUseCaseImpl(
        prodcuctRepository: ProductsRepository
    ): LoadProductDetailsUseCase =
        LoadProductDetailsUseCaseImpl(productsRepository = prodcuctRepository)

    @Provides
    @Singleton
    fun providesLoadProductsUseCaseImpl(
        prodcuctRepository: ProductsRepository
    ): LoadProductsUseCase =
        LoadProductsUseCaseImpl(prodcuctRepository)

}
