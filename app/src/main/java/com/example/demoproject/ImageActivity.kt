package com.example.demoproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

class ImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImageScreen("https://picsum.photos/800/1200")
                }
            }
        }
    }
}

@Composable
fun ImageScreen(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Loaded image from URL",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentScale = ContentScale.Fit
    )
}
