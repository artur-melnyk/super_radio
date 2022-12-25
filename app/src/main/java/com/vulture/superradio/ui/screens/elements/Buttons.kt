package com.vulture.superradio.ui.screens.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

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
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
    }
}

@Composable
fun PlayButton(
    isPlaying: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        val playIcon = if (isPlaying) {
            Icons.Default.StopCircle
        } else {
            Icons.Default.PlayCircle
        }

        Icon(
            modifier = Modifier.size(96.dp),
            imageVector = playIcon,
            contentDescription = "Play/Stop"
        )
    }
}