package com.alexruskovski.design.components

import android.text.Spanned
import android.text.util.Linkify
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.material.textview.MaterialTextView

@Composable
public fun SpannableText(
    spannableText: Spanned,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier,
        factory = {
            MaterialTextView(it).apply {
                autoLinkMask = Linkify.WEB_URLS
                linksClickable = true
            }
        },
        update = { it.text = spannableText }
    )
}