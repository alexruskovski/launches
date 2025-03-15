package com.alexruskovski.features.products.domain

data class ProductDomain (
    val id: Long,
    val isInStock: Boolean,
    val sizeInStock: List<SizesInStock>?,
    val availableSizes: List<SizesAvailable>,
    val title: String,
    val description: String,
    val type: String,
    val gender: List<String>,
    val colour: String,
    val price: Double,
    val featuredMedia: FeaturedMedia,
    val media: List<FeaturedMedia>,
) {
    enum class SizesInStock {
        XS,
        S,
        M,
        L,
        XL,
        XXL;
    }

    data class SizesAvailable(
        val id: Long,
        val isInStock: Boolean,
        val quantity: Int,
        val price: Double,
        val size: SizesInStock,
    )

    data class FeaturedMedia(
        val createdAt: String,
        val height: Float,
        val id: Long,
        val position: Int,
        val productId: Long,
        val imgUrl: String,
        val updatedAt: String,
        val width: Float,
    )
}
