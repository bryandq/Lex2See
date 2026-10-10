package com.example.lex2see.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val Lex2SeeColorScheme = lightColorScheme(
    primary = LexPrimary,
    onPrimary = LexText,
    secondary = LexCoral,
    onSecondary = LexText,
    background = LexBackground,
    onBackground = LexText,
    surface = LexSurface,
    onSurface = LexText,
    error = LexError
)

@Composable
fun Lex2seeTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    // The current Figma design defines a fixed light palette, so dynamic/dark colors are disabled
    // intentionally for design fidelity. Parameters stay here for future theming milestones.
    MaterialTheme(
        colorScheme = Lex2SeeColorScheme,
        typography = Typography,
        content = content
    )
}