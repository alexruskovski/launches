package com.alexruskovski.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ChipColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
public fun SimpleChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    SuggestionChip(
        modifier = modifier,
        label = { Text(text) },
        onClick = {},
    )
}
