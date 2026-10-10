package com.example.lex2see.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lex2see.ui.components.LexBackButton
import com.example.lex2see.ui.theme.LexBackground
import com.example.lex2see.ui.theme.LexPrimary
import com.example.lex2see.ui.theme.LexText
import com.example.lex2see.ui.theme.LexTextSecondary

@Composable
fun ForgotPasswordScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LexBackground)
            .statusBarsPadding()
            .padding(horizontal = 22.dp)
    ) {
        Spacer(Modifier.height(25.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
        ) {
            LexBackButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart))
            Text(
                text = "Quên mật khẩu",
                color = LexText,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(Modifier.height(72.dp))
        Text(
            text = "Khôi phục tài khoản",
            color = LexText,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Màn này đã được nối Navigation. Luồng gửi OTP/email sẽ triển khai cùng Firebase Authentication.",
            color = LexTextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(LexPrimary, RoundedCornerShape(10.dp))
                .clickable(onClick = onBack),
            contentAlignment = Alignment.Center
        ) {
            Text("Quay lại đăng nhập", color = LexText, style = MaterialTheme.typography.titleMedium)
        }
    }
}
