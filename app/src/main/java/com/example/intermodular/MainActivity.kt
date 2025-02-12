package com.example.intermodular

// Archivo: MainActivity.kt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.intermodular.navigation.AppNavigation
import com.example.intermodular.room.FacturaDatabaseDao
import com.example.intermodular.ui.theme.IntermodularTheme
import com.example.intermodular.viewmodels.FacturasViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = FacturaDatabaseDao()
        val viewModel = FacturasViewModel(dao)

        setContent {
            IntermodularTheme {
                Surface(color = Color.White) {
                    val navController = rememberNavController()
                    AppNavigation(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}