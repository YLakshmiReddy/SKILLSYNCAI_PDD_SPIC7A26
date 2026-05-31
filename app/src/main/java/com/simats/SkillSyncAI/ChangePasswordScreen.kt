package com.simats.SkillSyncAI

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    
    var currentPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Change Password", fontWeight = FontWeight.Bold, color = Color(0xFF1A1C1E)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF1A1C1E))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Secure your account by creating a new password. Make sure it's strong and unique.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            PasswordInputField(
                label = "Current Password",
                value = currentPassword,
                onValueChange = { currentPassword = it },
                visible = currentPasswordVisible,
                onVisibilityChange = { currentPasswordVisible = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordInputField(
                label = "New Password",
                value = newPassword,
                onValueChange = { newPassword = it },
                visible = newPasswordVisible,
                onVisibilityChange = { newPasswordVisible = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordInputField(
                label = "Confirm New Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                visible = confirmPasswordVisible,
                onVisibilityChange = { confirmPasswordVisible = it }
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    if (newPassword == confirmPassword && newPassword.isNotEmpty()) {
                        Toast.makeText(context, "Password updated successfully!", Toast.LENGTH_SHORT).show()
                        onBackClick()
                    } else {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurpleProfile)
            ) {
                Text("Update Password", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Composable
fun PasswordInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    visible: Boolean,
    onVisibilityChange: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121),
            modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = PrimaryPurpleProfile) },
            trailingIcon = {
                val icon = if (visible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { onVisibilityChange(!visible) }) {
                    Icon(icon, contentDescription = "Toggle password visibility")
                }
            },
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryPurpleProfile,
                unfocusedBorderColor = Color(0xFF757575)
            ),
            singleLine = true
        )
    }
}
