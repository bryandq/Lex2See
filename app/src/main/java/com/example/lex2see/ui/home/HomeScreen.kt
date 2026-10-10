package com.example.lex2see.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lex2see.ui.theme.LexBackground
import com.example.lex2see.ui.theme.LexPrimary
import com.example.lex2see.ui.theme.LexText

@Composable
fun HomeScreen(onSignOut: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LexBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            text = "Lex2See",
            color = LexText,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Đăng nhập demo thành công.\nFirebase Auth sẽ được nối ở milestone tiếp theo.",
            color = LexText,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(LexPrimary, RoundedCornerShape(10.dp))
                .clickable(onClick = onSignOut),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Đăng xuất",
                color = LexText,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(Modifier.weight(1f))
    }
}
