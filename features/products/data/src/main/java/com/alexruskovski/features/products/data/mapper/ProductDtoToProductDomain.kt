package com.alexruskovski.features.products.data.mapper

import com.alexruskovski.features.products.data.model.ProductDTO
import com.alexruskovski.features.products.domain.ProductDomain

fun ProductDTO.toProductDomain(): ProductDomain = ProductDomain(
    id = id,
    isInStock = isInStock,
    sizeInStock = sizeInStock?.map { it.toDomainSizesInStock() },
    availableSizes = availableSizes.map { it.toDomainSizesAvailable() },
    title = title,
    description = description,
    type = type,
    gender = gender,
    colour = colour,
    price = price,
    featuredMedia = featuredMedia.toDomainFeaturedMedia(),
    media = media.map { it.toDomainFeaturedMedia() },
)

fun ProductDTO.SizesInStock.toDomainSizesInStock() = ProductDomain.SizesInStock.valueOf(name)

fun ProductDTO.SizesAvailable.toDomainSizesAvailable() = ProductDomain.SizesAvailable(
    id = id,
    isInStock = isInStock,
    quantity = quantity,
    price = price,
    size = size.toDomainSizesInStock(),
)

fun ProductDTO.FeaturedMedia.toDomainFeaturedMedia() = ProductDomain.FeaturedMedia(
    createdAt = createdAt,
    height = height,
    id = id,
    position = position,
    productId = productId,
    imgUrl = imgUrl,
    updatedAt = updatedAt,
    width = width,
)
