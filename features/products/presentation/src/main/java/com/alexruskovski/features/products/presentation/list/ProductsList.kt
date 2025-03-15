package com.alexruskovski.features.products.presentation.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexruskovski.design.R
import com.alexruskovski.design.components.CircularCta
import com.alexruskovski.design.components.RemoteImage
import com.alexruskovski.design.components.SimpleChip
import com.alexruskovski.features.products.presentation.model.ProductUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsList(
    onProductClick: (ProductUi) -> Unit,
    viewModel: ProductsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    val productsState by viewModel.products.collectAsStateWithLifecycle()
    val isLoadingProducts by viewModel.isLoading.collectAsStateWithLifecycle()

    if (isLoadingProducts) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    Text(text = stringResource(R.string.products_label))
                },
            )

            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
            ) {
                items(
                    items = productsState,
                    key = { it.id },
                ) { product ->
                    ProductListItem(
                        modifier = Modifier.padding(bottom = 8.dp),
                        product = product,
                        onClick = { onProductClick(product) }
                    )
                }
            }
        }
    }
}

@Composable
internal fun ProductListItem(
    onClick: () -> Unit,
    product: ProductUi,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            RemoteImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(product.featuredMedia.aspectRation),
                imageUrl = product.featuredMedia.url,
                contentDescription = product.title,
                cornerShapeRadius = 8.dp,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SimpleChip(text = stringResource(product.isInStockTextRes))

                Spacer(Modifier.weight(1f))

                CircularCta(
                    icon = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = stringResource(R.string.cta_go_to_product_details_description),
                )
            }

            CardOverlay(
                title = product.title,
                type = product.type,
            )
        }
    }
}

@Composable
internal fun BoxScope.CardOverlay(
    title: String,
    type: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .height(height = 64.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = .65f),
                        Color.Transparent,
                    ),
                )
            ),
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.inversePrimary
        )

        Spacer(Modifier.weight(1f))

        Text(
            modifier = Modifier
                .padding(end = 16.dp),
            text = type,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }
}


