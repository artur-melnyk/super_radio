package com.vulture.superradio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vulture.superradio.data.models.Station
import com.vulture.superradio.ui.theme.SuperRadioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperRadioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    RadioStationsList(stations = emptyList())
                }
            }
        }
    }
}

@Composable
fun RadioStationsList(stations: List<Station>) {
    LazyColumn {
        items(stations) { station ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(64.dp),
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = "Station image",
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = station.name)
                    Text(text = station.genre)
                }
                Button(onClick = { /*TODO*/ }) {
                    Image(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val stationsStub = listOf(
        Station(
            name = "Super station", imageUrl = "", audioSourceUrl = "", genre = "Metal"
        ), Station(
            name = "Mega station", imageUrl = "", audioSourceUrl = "", genre = "Rock"
        )
    )

    SuperRadioTheme {
        RadioStationsList(stations = stationsStub)
    }
}