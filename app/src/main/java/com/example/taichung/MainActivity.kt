package com.example.taichung

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.taichung.ui.screens.MainScreen
import com.example.taichung.ui.theme.TaichungTheme
import com.example.taichung.utils.LocaleHelper
import com.example.taichung.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaichungTheme {
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) { MainScreen(viewModel = viewModel, onSwitchLanguage = { switchLanguage() }) }
            }
        }
    }

    private fun switchLanguage() {
        val currentLang = LocaleHelper.getLanguage(resources)
        val newLang = if (currentLang == "zh") "en" else "zh"
        
        // 儲存語言選擇
        getSharedPreferences("settings", Context.MODE_PRIVATE)
            .edit()
            .putString("language", newLang)
            .apply()
        
        val context = LocaleHelper.setLocale(this, newLang)
        recreate()
    }

    override fun attachBaseContext(newBase: Context) {
        val lang =
                newBase.getSharedPreferences("settings", Context.MODE_PRIVATE).getString("language", "zh")
                        ?: "zh"
        super.attachBaseContext(LocaleHelper.setLocale(newBase, lang))
    }
}
