package com.vulture.superradio.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.vulture.superradio.screens.elements.NavigationBar
import com.vulture.superradio.screens.radiostations.RadioStationsScreen

@Composable
fun HomeScreen() {
    Column {
        RadioStationsScreen()
        NavigationBar()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun HomePreview() {
    HomeScreen()
}