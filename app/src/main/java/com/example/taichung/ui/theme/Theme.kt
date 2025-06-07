package com.example.taichung.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFE0E0E0),
    secondary = Color(0xFFBDBDBD),
    tertiary = Color(0xFF9E9E9E),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    error = Color(0xFFCF6679),
    primaryContainer = Color(0xFF2C2C2C),
    onPrimary = Color.Black,
    onPrimaryContainer = Color.White,
    onSurface = Color.White,
    onBackground = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF757575),
    secondary = Color(0xFF9E9E9E),
    tertiary = Color(0xFFBDBDBD),
    background = Color.White,
    surface = Color.White,
    error = Color(0xFFB00020),
    primaryContainer = Color(0xFFF5F5F5),
    onPrimary = Color.White,
    onPrimaryContainer = Color(0xFF424242),
    onSurface = Color(0xFF212121),
    onBackground = Color(0xFF212121),
    surfaceVariant = Color(0xFFFAFAFA),
    onSurfaceVariant = Color(0xFF424242)
)

@Composable
fun TaichungTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}