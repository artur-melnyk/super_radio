package com.vulture.superradio.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.vulture.superradio.ui.screens.elements.NavigationBar
import com.vulture.superradio.ui.screens.radiostations.RadioStationsScreen

@Composable
fun HomeScreen() {
    Column {
        //NavHost with RadioStation, Player
        NavigationBar()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun HomePreview() {
    HomeScreen()
}