package com.alexruskovski.features.products.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexruskovski.core.data.NetworkResult
import com.alexruskovski.features.products.domain.use_cases.LoadProductsUseCase
import com.alexruskovski.features.products.presentation.mapper.toProductUi
import com.alexruskovski.features.products.presentation.model.ProductUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val loadProductsUseCase: LoadProductsUseCase,
) : ViewModel() {

    private val _products = MutableStateFlow(emptyList<ProductUi>())
    val products = _products
        .onStart { loadProducts() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    private fun loadProducts() {
        _isLoading.value = true
        viewModelScope.launch {

            //TODO: load products we can't access the NetworkResult in the presentation, as this is declared in the DATA module in the core

            when (val result = loadProductsUseCase.loadProducts()) {
                is NetworkResult.Error -> {
                    //TODO: handle error
                    _isLoading.value = false
                }

                is NetworkResult.Loading -> {
                    //TODO: handle loading
                    _isLoading.value = false
                }

                is NetworkResult.Success -> {
                    _isLoading.value = false
                    _products.value = result.data.map { it.toProductUi() }
                }
            }
        }
    }

}