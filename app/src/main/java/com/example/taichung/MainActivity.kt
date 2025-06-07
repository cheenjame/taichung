package com.example.taichung

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taichung.ui.theme.TaichungTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val flowerList by viewModel.flowerList.observeAsState(emptyList())
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainScreen(
                        flowerList = flowerList,
                        onWebClick = { url -> navController.navigate("web/$url") },
                        onSwitchLang = { recreate() } // 只是範例，可以搭配多語言切換實作
                    )
                }
                composable(
                    "web/{url}",
                    arguments = listOf(navArgument("url") { type = NavType.StringType })
                ) { backStackEntry ->
                    WebViewScreen(url = backStackEntry.arguments?.getString("url") ?: "")
                }
            }
        }

        viewModel.fetchFlowers()
    }
}
