package com.alexruskovski.features.products.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexruskovski.features.products.domain.use_cases.LoadProductDetailsUseCase
import com.alexruskovski.features.products.domain.use_cases.LoadProductDetailsUseCaseImpl
import com.alexruskovski.features.products.presentation.model.ProductUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val loadProductsUseCase: LoadProductDetailsUseCase,
) : ViewModel() {

    private val _product = MutableStateFlow<ProductUi?>(null)
    val product = _product
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun loadProduct(productId: Long) {
        viewModelScope.launch {
//            when (val response = loadProductsUseCase(productId)) {
//                is NetworkResult.Error -> TODO()
//                is NetworkResult.Loading -> TODO()
//                is NetworkResult.Success -> {
//                    _product.value = response.data.toProductUi()
//                }
//            }
        }
    }

}