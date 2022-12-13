package com.vulture.superradio.ui.screens.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val iconsSize = 64.dp

        IconButton(onClick = onHomeClick) {
            Icon(
                modifier = Modifier.size(iconsSize),
                imageVector = Icons.Default.Home,
                contentDescription = "Home"
            )
        }
        IconButton(onClick = onSearchClick) {
            Icon(
                modifier = Modifier.size(iconsSize),
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        }
        IconButton(onClick = onFavoriteClick) {
            Icon(
                modifier = Modifier.size(iconsSize),
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorites"
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun NavigationPreview() {
    NavigationBar(modifier = Modifier.fillMaxWidth())
}