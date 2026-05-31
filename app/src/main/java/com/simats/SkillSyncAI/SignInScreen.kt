package com.simats.SkillSyncAI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignInScreen(
    onBackClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignInSuccessClick: (Boolean, String, String) -> Unit,
    onGoogleSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Validation: Enable button only if email and password are not empty
    val isSignInEnabled = email.isNotBlank() && password.isNotBlank()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Back Arrow
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackClick() },
            tint = Color(0xFF0F172A)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Logo and Branding
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1D4ED8)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_skillsync_logo),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "SkillSync Ai",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Welcome Back",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F172A),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Please enter your details to sign in.",
            fontSize = 16.sp,
            color = Color(0xFF64748B),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Email Field
        Text(text = "Email", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Enter your email", color = Color(0xFF94A3B8)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3B82F6),
                unfocusedBorderColor = Color(0xFFE2E8F0)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Password Field
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Password", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
            Text(
                text = "Forgot password?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B82F6),
                modifier = Modifier.clickable { onForgotPasswordClick() }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Enter your password", color = Color(0xFF94A3B8)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null, tint = Color(0xFF94A3B8))
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF3B82F6),
                unfocusedBorderColor = Color(0xFFE2E8F0)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Remember Me
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { rememberMe = !rememberMe }
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B82F6))
            )
            Text(text = "Remember me for 30 days", fontSize = 14.sp, color = Color(0xFF64748B))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sign In Button - Enabled only if validation passes
        Button(
            onClick = {
                coroutineScope.launch {
                    isLoading = true
                    errorMessage = null
                    try {
                        val response = RetrofitClient.apiService.login(LoginRequest(email, password))
                        if (response.isSuccessful && response.body() != null) {
                            val loginResponse = response.body()!!
                            onSignInSuccessClick(
                                rememberMe,
                                loginResponse.user.full_name,
                                loginResponse.user.email
                            )
                        } else {
                            val errorBody = response.errorBody()?.string()
                            errorMessage = if (!errorBody.isNullOrBlank() && errorBody.contains("detail")) {
                                // Extract detail message if present
                                if (errorBody.contains("\"detail\":\"")) {
                                    errorBody.substringAfter("\"detail\":\"").substringBefore("\"")
                                } else {
                                    errorBody
                                }
                            } else {
                                "Invalid email or password. Please try again."
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        errorMessage = "Network error. Please check your connection."
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    } finally {
                        isLoading = false
                    }
                }
            },
            enabled = isSignInEnabled && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E88E5),
                disabledContainerColor = Color(0xFFB0BEC5)
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(text = "Sign In", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = errorMessage!!,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Divider
        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
            Text(
                text = "OR CONTINUE WITH",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF94A3B8),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Social Sign In
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            SocialButton(
                iconRes = R.drawable.ic_google,
                label = "Google",
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = onGoogleSignInClick
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Sign Up Link
        val signUpText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(0xFF64748B))) {
                append("Don't have an account? ")
            }
            withStyle(style = SpanStyle(color = Color(0xFF3B82F6), fontWeight = FontWeight.Bold)) {
                append("Sign up for free")
            }
        }
        Text(
            text = signUpText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
                .clickable { onSignUpClick() },
            textAlign = TextAlign.Center,
            fontSize = 15.sp
        )
    }
}

@Composable
fun SocialButton(iconRes: Int, label: String, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .height(56.dp)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = iconRes), contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
        }
    }
}
