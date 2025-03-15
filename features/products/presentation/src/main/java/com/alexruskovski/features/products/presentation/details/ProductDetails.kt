package com.alexruskovski.features.products.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexruskovski.design.components.Text
import com.alexruskovski.design.R
import com.alexruskovski.design.components.CtaButton
import com.alexruskovski.design.components.RemoteImage
import com.alexruskovski.design.components.SpannableText
import com.alexruskovski.features.products.presentation.model.ProductUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(
    productId: Long,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onBuyNowClick: (ProductUi) -> Unit,
    modifier: Modifier = Modifier,
) {
    val product by viewModel.product.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.loadProduct(productId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = product?.title ?: "") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.navigataion_back)
                    )
                }
            }
        )
        product?.let {
            Details(
                modifier = modifier,
                product = it,
                onBuyNowClick = onBuyNowClick,
            ) }

    }
}

@Composable
internal fun ColumnScope.PriceSection(price: String?) {
    if (price.isNullOrBlank())
        return
    Text(
        text = stringResource(R.string.price_title),
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = price,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
internal fun Details(
    product: ProductUi,
    onBuyNowClick: (ProductUi) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        ProductDetailImages(images = product.productImages)

        Spacer(Modifier.height(8.dp))

        SpannableText(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
            spannableText = product.descriptionSpanned
        )

        product.sizesInStock?.let { SizesInStock(it) }

        PriceSection(product.price)

        CtaButton(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.add_to_cart),
            onClick = { onBuyNowClick(product) }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun SizesInStock(
    sizesInStock: List<ProductUi.SizesInStock>,
) {
    Text(
        modifier = Modifier
            .padding(top = 8.dp),
        text = stringResource(R.string.available_sizes_title),
        style = MaterialTheme.typography.bodyMedium
    )

    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        sizesInStock.forEach {
            Box(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        end = 8.dp,
                    ),
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = it.name,
                )
            }
        }
    }
}

@Composable
internal fun ProductDetailImages(
    images: List<ProductUi.FeaturedMedia>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .heightIn(max = 256.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(
            count = images.size,
            key = { images[it].url }
        ) { index ->
            ProductDetailsMediaItem(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                media = images[index],
            )
        }
    }
}

@Composable
internal fun ProductDetailsMediaItem(
    media: ProductUi.FeaturedMedia,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        RemoteImage(
            imageUrl = media.url,
            contentDescription = stringResource(R.string.product_media_image)
        )
    }
}