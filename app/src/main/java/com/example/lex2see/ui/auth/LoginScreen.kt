package com.example.lex2see.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lex2see.R
import com.example.lex2see.ui.components.LexBackButton
import com.example.lex2see.ui.theme.LexBackground
import com.example.lex2see.ui.theme.LexCoral
import com.example.lex2see.ui.theme.LexDivider
import com.example.lex2see.ui.theme.LexPrimary
import com.example.lex2see.ui.theme.LexSurface
import com.example.lex2see.ui.theme.LexText
import com.example.lex2see.ui.theme.LexTextSecondary
@Composable
fun LoginRoute(
    viewModel: LoginViewModel,
    onBack: () -> Unit,
    onForgotPassword: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.loginSucceeded) {
        if (state.loginSucceeded) {
            viewModel.onLoginHandled()
            onLoginSuccess()
        }
    }

    LaunchedEffect(state.message) {
        state.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.onMessageShown()
        }
    }

    LoginScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onPhoneChanged = viewModel::onPhoneChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onLogin = viewModel::login,
        onGoogleLogin = viewModel::loginWithGoogle,
        onForgotPassword = onForgotPassword,
        onBack = onBack
    )
}

@Composable
fun LoginScreen(
    state: LoginUiState,
    snackbarHostState: SnackbarHostState,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLogin: () -> Unit,
    onGoogleLogin: () -> Unit,
    onForgotPassword: () -> Unit,
    onBack: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LexBackground)
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(25.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
                    .padding(horizontal = 22.dp)
            ) {
                LexBackButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                Text(
                    text = "Đăng nhập",
                    color = LexText,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(Modifier.height(93.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
            ) {
                Column(modifier = Modifier.padding(start = 4.dp)) {
                    Text(
                        text = "Vào việc nào bạn ơi!",
                        color = LexText,
                        fontSize = 28.sp,
                        lineHeight = 42.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Chào mừng trở lại,\nBạn bỏ tui đi đâu thế!!",
                        color = LexTextSecondary,
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(Modifier.height(27.dp))

                LexLoginField(
                    value = state.phone,
                    onValueChange = onPhoneChanged,
                    placeholder = "Số điện thoại",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                Spacer(Modifier.height(39.dp))

                LexLoginField(
                    value = state.password,
                    onValueChange = onPasswordChanged,
                    placeholder = "Mật khẩu",
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onLogin()
                        }
                    )
                )

                Spacer(Modifier.height(7.dp))

                Text(
                    text = "Quên mật khẩu?",
                    color = LexText,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable(enabled = !state.isLoading, onClick = onForgotPassword)
                )

                Spacer(Modifier.height(44.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .background(LexPrimary, RoundedCornerShape(10.dp))
                        .clickable(enabled = !state.isLoading, onClick = onLogin),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(22.dp),
                            color = LexText,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = "Đăng nhập",
                            color = LexText,
                            fontSize = 18.sp,
                            lineHeight = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.height(44.dp))

                OrDivider()

                Spacer(Modifier.height(44.dp))

                GoogleLoginButton(
                    enabled = !state.isLoading,
                    onClick = onGoogleLogin
                )

                Spacer(Modifier.height(36.dp))
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}

@Composable
private fun LexLoginField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    isPassword: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(LexCoral, RoundedCornerShape(10.dp))
            .padding(horizontal = 17.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = LexText,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight.Light
            ),
            cursorBrush = SolidColor(LexText),
            visualTransformation = if (isPassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = LexText,
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
private fun OrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(LexDivider)
        )
        Text(
            text = "Hoặc",
            color = LexText,
            fontSize = 12.sp,
            lineHeight = 15.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(LexDivider)
        )
    }
}

@Composable
private fun GoogleLoginButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(LexSurface, RoundedCornerShape(10.dp))
            .clickable(enabled = enabled, onClick = onClick)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_google),
            contentDescription = "Google",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 48.dp)
                .size(18.dp)
        )

        Text(
            text = "Đăng nhập với Google",
            color = LexText,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

