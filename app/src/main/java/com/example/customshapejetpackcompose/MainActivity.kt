package com.example.customshapejetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import com.example.customshapejetpackcompose.ui.theme.CustomShapeJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomShapeJetpackComposeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.Black
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CustomShape(text = "Hexagon", numVertices = 6, color = Color.Red)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomShape(text: String, numVertices: Int, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                val roundedPolygon = RoundedPolygon(
                    numVertices = numVertices,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2
                )

                val roundedPolygonPath = roundedPolygon
                    .toPath()
                    .asComposePath()

                onDrawBehind {
                    drawPath(
                        path = roundedPolygonPath,
                        color = color
                    )
                }
                onDrawWithContent {
                    drawPath(
                        path = roundedPolygonPath,
                        color = color
                    )
                    drawContent()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomShapeJetpackComposeTheme {}
}