package com.mrh.planes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mrh.planes.ui.navigation.Navigator
import com.mrh.planes.ui.theme.PlanesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanesTheme {
                Navigator()
            }
        }
    }
}
