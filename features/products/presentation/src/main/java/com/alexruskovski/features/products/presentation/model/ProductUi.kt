package com.alexruskovski.features.products.presentation.model

import android.text.Spanned
import androidx.annotation.StringRes

data class ProductUi(
    val id: Long,
    val title: String,
    val descriptionSpanned: Spanned,
    val type: String,
    @StringRes val isInStockTextRes: Int,
    val featuredMedia: FeaturedMedia,
    val productImages: List<FeaturedMedia>,
    val sizesInStock: List<SizesInStock>? = emptyList(),
    val price: String,
) {

    enum class SizesInStock {
        XS,
        S,
        M,
        L,
        XL,
        XXL;
    }

    data class FeaturedMedia(
        val url: String,
        val aspectRation: Float,
    )
}
