package com.alexruskovski.features.products.presentation.mapper

import androidx.core.text.HtmlCompat
import com.alexruskovski.design.R
import com.alexruskovski.features.products.domain.ProductDomain
import com.alexruskovski.features.products.presentation.model.ProductUi

internal fun ProductDomain.toProductUi() =
    ProductUi(
        id = id,
        title = title,
        descriptionSpanned = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT),
        type = type,
        isInStockTextRes = if (isInStock) R.string.in_stock_label else R.string.out_of_stock_label,
        featuredMedia = ProductUi.FeaturedMedia(
            url = featuredMedia.imgUrl,
            aspectRation = featuredMedia.width / featuredMedia.height,
        ),
        productImages = media.map {
            ProductUi.FeaturedMedia(
                url = it.imgUrl,
                aspectRation = it.width / it.height,
            )
        },
        sizesInStock = sizeInStock?.map {
            when (it) {
                ProductDomain.SizesInStock.XS -> ProductUi.SizesInStock.XS
                ProductDomain.SizesInStock.S -> ProductUi.SizesInStock.S
                ProductDomain.SizesInStock.M -> ProductUi.SizesInStock.M
                ProductDomain.SizesInStock.L -> ProductUi.SizesInStock.L
                ProductDomain.SizesInStock.XL -> ProductUi.SizesInStock.XL
                ProductDomain.SizesInStock.XXL -> ProductUi.SizesInStock.XXL
            }
        },
        price = "£${price/100}",
    )
