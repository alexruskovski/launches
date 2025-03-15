package com.alexruskovski.features.products.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ProductsResponse(
    val hits: List<ProductDTO>,
)

@Serializable
data class ProductDTO(
    val id: Long,
    @SerialName("inStock") val isInStock: Boolean,
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

    enum class SizesInStock(size: String) {
        @SerialName("xs")
        XS("xs"),
        @SerialName("s")
        S("s"),
        @SerialName("m")
        M("m"),
        @SerialName("l")
        L("l"),
        @SerialName("xl")
        XL("xl"),
        @SerialName("xxl")
        XXL("xxl");
    }

    @Serializable
    data class SizesAvailable(
        val id: Long,
        @SerialName("inStock") val isInStock: Boolean,
        @SerialName("inventoryQuantity") val quantity: Int,
        val price: Double,
        val size: SizesInStock,
    )

    @Serializable
    data class FeaturedMedia(
        @SerialName("created_at") val createdAt: String,
        val height: Float,
        val id: Long,
        val position: Int,
        @SerialName("product_id") val productId: Long,
        @SerialName("src") val imgUrl: String,
        @SerialName("updated_at") val updatedAt: String,
        val width: Float,
    )
}
