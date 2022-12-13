package com.vulture.superradio.ui.screens.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.vulture.superradio.ui.theme.mediumIconSize
import com.vulture.superradio.ui.theme.smallIconSize

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    val icon = if (isFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    IconButton(onClick = onClick) {
        Image(
            imageVector = icon,
            contentDescription = "Favorite",
            colorFilter = ColorFilter.tint(Color.Black)
        )
    }
}

@Composable
fun NavigateBackButton(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = Modifier.size(smallIconSize),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
    }
}