package com.example.lex2see.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.lex2see.ui.theme.LexCoral
import com.example.lex2see.ui.theme.LexText

@Composable
fun LexBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(46.dp)
            .background(LexCoral, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(22.dp)) {
            val stroke = 2.6.dp.toPx()
            drawLine(
                color = LexText,
                start = Offset(size.width * 0.68f, size.height * 0.18f),
                end = Offset(size.width * 0.32f, size.height * 0.50f),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
            drawLine(
                color = LexText,
                start = Offset(size.width * 0.32f, size.height * 0.50f),
                end = Offset(size.width * 0.68f, size.height * 0.82f),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}
