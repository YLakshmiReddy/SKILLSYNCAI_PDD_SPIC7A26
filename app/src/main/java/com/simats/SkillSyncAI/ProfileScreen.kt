package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// Reusing colors or defining new ones to match the image
val ProfileGradientStart = Color(0xFFFFE0CC) // Light peach
val ProfileGradientEnd = Color(0xFFFFFFFF)
val PrimaryPurpleProfile = Color(0xFF673AB7)
val SkillChipBg = Color(0xFFE8EAF6)
val SkillChipText = Color(0xFF5C6BC0)
val SkillChipBgAlt = Color(0xFFFFF3E0)
val SkillChipTextAlt = Color(0xFFFF7043)
val SignOutBg = Color(0xFFFFEBEE)
val SignOutText = Color(0xFFE53935)

@Composable
fun ProfileScreen(
    onHomeClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onRoadmapClick: () -> Unit,
    onAccountInfoClick: () -> Unit,
    onAppSettingsClick: () -> Unit,
    onResumeManagementClick: () -> Unit,
    onHelpSupportClick: () -> Unit,
    onSignOutClick: () -> Unit
) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    
    val userName = preferenceManager.getUserName()
    val userRole = preferenceManager.getUserRole()
    val userImageUri = preferenceManager.getUserImageUri()

    Scaffold(
        bottomBar = { ProfileBottomBar(onHomeClick, onAnalysisClick, onRoadmapClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            // Header with Gradient and Profile Info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(ProfileGradientStart, ProfileGradientEnd)
                        )
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Profile Image
                    Surface(
                        modifier = Modifier
                            .size(120.dp)
                            .border(4.dp, Color.White, CircleShape),
                        shape = CircleShape,
                        color = Color.LightGray
                    ) {
                        if (userImageUri.isNullOrEmpty()) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = null,
                                modifier = Modifier.padding(24.dp),
                                tint = Color.Gray
                            )
                        } else {
                            AsyncImage(
                                model = userImageUri,
                                contentDescription = "Profile Picture",
                                modifier = Modifier.fillMaxSize().clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = userName,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1C1E)
                        )
                    )
                    Text(
                        text = userRole,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = PrimaryPurpleProfile,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            // Skill Summary Section
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = "SKILL SUMMARY",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color(0xFF424242),
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SkillChip(text = "Python", containerColor = SkillChipBg, textColor = SkillChipText)
                    SkillChip(text = "SQL", containerColor = SkillChipBg, textColor = SkillChipText)
                    SkillChip(text = "Tableau", containerColor = SkillChipBg, textColor = SkillChipText)
                    SkillChip(text = "AI Models", containerColor = SkillChipBgAlt, textColor = SkillChipTextAlt)
                }

                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(24.dp))

                // Menu Items
                ProfileMenuItem(
                    icon = Icons.Outlined.Person,
                    title = "Account Information",
                    iconColor = Color(0xFF7E57C2),
                    onClick = onAccountInfoClick
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Description,
                    title = "Resume Management",
                    iconColor = Color(0xFF5C6BC0),
                    onClick = onResumeManagementClick
                )
                ProfileMenuItem(
                    icon = Icons.Outlined.Settings,
                    title = "App Settings",
                    iconColor = Color(0xFF7986CB),
                    onClick = onAppSettingsClick
                )
                ProfileMenuItem(
                    icon = Icons.AutoMirrored.Outlined.HelpOutline,
                    title = "Help & Support",
                    iconColor = Color(0xFF9FA8DA),
                    onClick = onHelpSupportClick
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Sign Out Button
                Button(
                    onClick = onSignOutClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SignOutBg)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.AutoMirrored.Outlined.Logout, contentDescription = null, tint = SignOutText)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Sign Out",
                            color = SignOutText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Footer Branding
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "SkillSync ",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Color(0xFF424242)
                        )
                        Text(
                            "Ai",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Color(0xFFBF360C)
                        )
                    }
                    Text(
                        "Version 2.4.0",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun SkillChip(text: String, containerColor: Color, textColor: Color) {
    Surface(
        color = containerColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    iconColor: Color,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(8.dp),
            color = iconColor.copy(alpha = 0.1f)
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = iconColor
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121)
        )
        Icon(
            Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Composable
fun ProfileBottomBar(
    onHomeClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onRoadmapClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = false,
            onClick = onHomeClick,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("HOME", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onAnalysisClick,
            icon = { Icon(Icons.Default.Analytics, contentDescription = "Analysis") },
            label = { Text("ANALYSIS", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onRoadmapClick,
            icon = { Icon(Icons.Default.Map, contentDescription = "Roadmap") },
            label = { Text("ROADMAP", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
        )
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("PROFILE", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                indicatorColor = PrimaryPurpleProfile
            )
        )
    }
}
