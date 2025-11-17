package com.phenyo.gigapp.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

private val LightColorScheme = lightColorScheme(
    primary = PurpleSolid,
    onPrimary = Color.White,
    background = Background,
    surface = Surface,
    onSurface = TextPrimary
)

@Composable
fun GigAppTheme(content: @Composable () -> Unit) {
    val colors = LightColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = Typography(defaultFontFamily = FontFamily.SansSerif),
        shapes = Shapes(),
        content = content
    ) {
        content()
    }
}