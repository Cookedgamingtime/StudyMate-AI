package com.studymate.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val WarmColors = lightColorScheme(
    primary = Color(0xFFFF8A80),
    secondary = Color(0xFFA8E6CF),
    tertiary = Color(0xFFFFD93D),
    background = Color(0xFFFFF9F0),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF4A3F35),
    onBackground = Color(0xFF4A3F35),
    onSurface = Color(0xFF4A3F35)
)

@Composable
fun StudyMateTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WarmColors,
        content = content
    )
}
