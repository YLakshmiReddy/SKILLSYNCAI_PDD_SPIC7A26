package com.simats.SkillSyncAI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    onSignInClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F9)) // Light grayish blue background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Logo Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
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
                Spacer(modifier = Modifier.width(10.dp))
                Row {
                    Text(
                        text = "SkillSync ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "Ai",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1D4ED8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Central Icon Box
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_welcome_nodes),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Headline
            Text(
                text = "Elevate Your\nPotential",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                color = Color(0xFF0F172A)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sub-headline
            Text(
                text = "Your AI-powered skill matching companion.",
                fontSize = 16.sp,
                color = Color(0xFF64748B),
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // White bottom sheet container
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 20.dp),
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .padding(bottom = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "GET STARTED",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3B82F6),
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Sign In Button
                    Button(
                        onClick = onSignInClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Login,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Sign In", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Create Account Button
                    Button(
                        onClick = onCreateAccountClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE3F2FD))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.PersonAdd,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = Color(0xFF1E88E5)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Create Account",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E88E5)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Small Security Icons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_shield_check), contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(painter = painterResource(id = R.drawable.ic_shield), contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(painter = painterResource(id = R.drawable.ic_chip), contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(20.dp))
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    // Terms and Privacy Text
                    val annotatedString = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFF64748B))) {
                            append("By continuing, you agree to our ")
                        }
                        pushStringAnnotation(tag = "terms", annotation = "terms")
                        withStyle(style = SpanStyle(color = Color(0xFF3B82F6), fontWeight = FontWeight.Medium)) {
                            append("Terms")
                        }
                        pop()
                        withStyle(style = SpanStyle(color = Color(0xFF64748B))) {
                            append(" & ")
                        }
                        pushStringAnnotation(tag = "privacy", annotation = "privacy")
                        withStyle(style = SpanStyle(color = Color(0xFF3B82F6), fontWeight = FontWeight.Medium)) {
                            append("Privacy Policy")
                        }
                        pop()
                    }

                    ClickableText(
                        text = annotatedString,
                        style = TextStyle(
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        ),
                        onClick = { offset ->
                            annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset)
                                .firstOrNull()?.let {
                                    onTermsClick()
                                }
                            annotatedString.getStringAnnotations(tag = "privacy", start = offset, end = offset)
                                .firstOrNull()?.let {
                                    onPrivacyClick()
                                }
                        }
                    )
                }
            }
        }
    }
}
