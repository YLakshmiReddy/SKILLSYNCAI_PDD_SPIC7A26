package com.simats.SkillSyncAI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
fun SignUpScreen(
    onBackClick: () -> Unit,
    onSignUpClick: (String, String) -> Unit,
    onGoogleSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    // Root background #F6F7F8
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7F8))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Main scrollable area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // White Card matching CSS specs (box-shadow equivalent)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .shadow(
                        elevation = 25.dp,
                        shape = RoundedCornerShape(12.dp),
                        ambientColor = Color.Black.copy(alpha = 0.25f),
                        spotColor = Color.Black.copy(alpha = 0.25f)
                    ),
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    
                    // Header Section (72px height from CSS)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color(0xFF0F172A),
                                modifier = Modifier.size(16.dp) // Icon 16x16 from CSS
                            )
                        }
                        
                        Text(
                            text = "Create Account",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A),
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        
                        // Empty box to center the title text correctly
                        Box(modifier = Modifier.size(48.dp))
                    }

                    // Gradient Hero Section (200px height from CSS)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFFFF9A9E), Color(0xFFFECFEF), Color(0xFFFEADA6)),
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        // Branding Icon Overlay (54x51px from CSS)
                        Surface(
                            modifier = Modifier
                                .size(54.dp, 51.dp)
                                .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp)),
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White.copy(alpha = 0.9f)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_signup_sync),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp, 27.dp)
                                )
                            }
                        }
                    }

                    // Form and Content
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Join SkillSync Ai",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A),
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Sync your skills with the future",
                            fontSize = 16.sp,
                            color = Color(0xFF475569),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Form Fields using reusable component
                        SignUpTextFieldItem(
                            label = "Full Name",
                            value = fullName,
                            onValueChange = { fullName = it },
                            placeholder = "Enter your full name"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        SignUpTextFieldItem(
                            label = "Email Address",
                            value = email,
                            onValueChange = { email = it },
                            placeholder = "example@email.com"
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Password Field
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Password",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF0F172A),
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                placeholder = { Text("Min. 8 characters", color = Color(0xFF94A3B8)) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                trailingIcon = {
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        Icon(
                                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                            contentDescription = null,
                                            tint = Color(0xFF94A3B8),
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color(0xFFF8FAFC),
                                    unfocusedContainerColor = Color(0xFFF8FAFC),
                                    focusedBorderColor = Color(0xFF137FEC),
                                    unfocusedBorderColor = Color(0xFFE2E8F0)
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        SignUpTextFieldItem(
                            label = "Confirm Password",
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            placeholder = "Re-type password"
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Sign Up Button (52px height from CSS)
                        Button(
                            onClick = {
                                if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
                                    errorMessage = "Please fill in all fields."
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    return@Button
                                }
                                if (password != confirmPassword) {
                                    errorMessage = "Passwords do not match."
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    return@Button
                                }
                                if (password.length < 8) {
                                    errorMessage = "Password must be at least 8 characters."
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    return@Button
                                }

                                coroutineScope.launch {
                                    isLoading = true
                                    errorMessage = null
                                    try {
                                        val response = RetrofitClient.apiService.register(
                                            RegisterRequest(fullName, email, password)
                                        )
                                        if (response.isSuccessful && response.body() != null) {
                                            val registerResponse = response.body()!!
                                            Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                            onSignUpClick(
                                                registerResponse.user.full_name,
                                                registerResponse.user.email
                                            )
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            errorMessage = if (!errorBody.isNullOrBlank() && errorBody.contains("detail")) {
                                                if (errorBody.contains("\"detail\":\"")) {
                                                    errorBody.substringAfter("\"detail\":\"").substringBefore("\"")
                                                } else {
                                                    errorBody
                                                }
                                            } else {
                                                "Failed to create account. Please try again."
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
                            enabled = !isLoading,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp), spotColor = Color(0xFF137FEC).copy(alpha = 0.2f)),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF1E88E5),
                                disabledContainerColor = Color(0xFFB0BEC5)
                            )
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                            } else {
                                Text(
                                    text = "Sign Up",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
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

                        Spacer(modifier = Modifier.height(24.dp))

                        // Continue With Divider Section
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
                            Text(
                                text = "Or continue with",
                                modifier = Modifier.padding(horizontal = 16.dp),
                                fontSize = 14.sp,
                                color = Color(0xFF94A3B8),
                                fontWeight = FontWeight.Medium
                            )
                            HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Social Sign Up Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            SignUpSocialButtonItem(
                                iconRes = R.drawable.ic_google,
                                label = "Google",
                                modifier = Modifier.fillMaxWidth(0.8f),
                                onClick = onGoogleSignUpClick
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        // Bottom Navigation Link
                        val loginText = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color(0xFF64748B))) {
                                append("Already have an account? ")
                            }
                            withStyle(style = SpanStyle(color = Color(0xFF137FEC), fontWeight = FontWeight.Bold)) {
                                append("Log In")
                            }
                        }
                        Text(
                            text = loginText,
                            modifier = Modifier
                                .clickable { onLoginClick() }
                                .padding(bottom = 16.dp),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            // Final bottom spacer to ensure scrolling reaches the very end
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun SignUpTextFieldItem(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF0F172A),
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFF94A3B8)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF8FAFC),
                unfocusedContainerColor = Color(0xFFF8FAFC),
                focusedBorderColor = Color(0xFF137FEC),
                unfocusedBorderColor = Color(0xFFE2E8F0)
            )
        )
    }
}

@Composable
fun SignUpSocialButtonItem(
    iconRes: Int,
    label: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(46.dp),
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF334155))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
