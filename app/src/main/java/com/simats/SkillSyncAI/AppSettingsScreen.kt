package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSettingsScreen(
    onBackClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var emailUpdatesEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Settings", fontWeight = FontWeight.Bold, color = Color(0xFF1A1C1E)) },
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
                .padding(24.dp)
        ) {
            Text(
                text = "GENERAL",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            SettingsSwitchItem(
                icon = Icons.Filled.Notifications,
                title = "Push Notifications",
                description = "Receive alerts about roadmap updates",
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "ACCOUNT & PRIVACY",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            SettingsSwitchItem(
                icon = Icons.Filled.Email,
                title = "Email Updates",
                description = "Stay informed about new features",
                checked = emailUpdatesEnabled,
                onCheckedChange = { emailUpdatesEnabled = it }
            )

            SettingsClickItem(
                icon = Icons.Filled.Lock,
                title = "Change Password",
                onClick = onChangePasswordClick
            )

            SettingsClickItem(
                icon = Icons.Filled.Language,
                title = "Language",
                value = "English (US)",
                showArrow = false, // Removed arrow
                onClick = { /* No action needed as English is standard */ }
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "ABOUT",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            SettingsClickItem(
                icon = Icons.Filled.Info,
                title = "Terms of Service",
                onClick = onTermsClick
            )

            SettingsClickItem(
                icon = Icons.Filled.PrivacyTip,
                title = "Privacy Policy",
                onClick = onPrivacyClick
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurpleProfile)
            ) {
                Text("Save Settings", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Composable
fun SettingsSwitchItem(
    icon: ImageVector,
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(8.dp),
            color = PrimaryPurpleProfile.copy(alpha = 0.1f)
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.padding(8.dp), tint = PrimaryPurpleProfile)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF212121))
            Text(text = description, fontSize = 12.sp, color = Color.Gray)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryPurpleProfile
            )
        )
    }
}

@Composable
fun SettingsClickItem(
    icon: ImageVector,
    title: String,
    value: String? = null,
    showArrow: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = showArrow || onClick != {}) { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(8.dp),
            color = PrimaryPurpleProfile.copy(alpha = 0.1f)
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.padding(8.dp), tint = PrimaryPurpleProfile)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), fontSize = 16.sp, color = Color(0xFF212121))
        if (value != null) {
            Text(text = value, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(horizontal = 8.dp))
        }
        if (showArrow) {
            Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = Color.Gray)
        }
    }
}
