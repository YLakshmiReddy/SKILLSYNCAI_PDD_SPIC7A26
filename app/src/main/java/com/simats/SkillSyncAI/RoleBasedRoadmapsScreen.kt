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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Theme Colors
val PathBgStart = Color(0xFFF3F5FF)
val PathBgEnd = Color(0xFFFFFFFF)
val PathPrimary = Color(0xFF1A237E)
val PathAccent = Color(0xFFE8EAF6)

@Composable
fun CareerPathScreen(
    onBackClick: () -> Unit,
    onFrontendClick: () -> Unit,
    onBackendClick: () -> Unit,
    onFullStackClick: () -> Unit,
    onAIEngineerClick: () -> Unit,
    onDevOpsClick: () -> Unit,
    onUXDesignClick: () -> Unit,
    onCyberSecurityClick: () -> Unit,
    onGameDeveloperClick: () -> Unit,
    onServerSideGameDevClick: () -> Unit,
    onMLOpsClick: () -> Unit,
    onProductManagerClick: () -> Unit,
    onDevSecOpsClick: () -> Unit,
    onDataAnalystClick: () -> Unit,
    onBIAnalystClick: () -> Unit,
    onDevRelClick: () -> Unit,
    onDataScientistClick: () -> Unit,
    onDataEngineerClick: () -> Unit,
    onMachineLearningClick: () -> Unit,
    onAndroidClick: () -> Unit,
    onPostgresClick: () -> Unit,
    onIOSClick: () -> Unit,
    onBlockchainClick: () -> Unit,
    onQAClick: () -> Unit,
    onSoftwareArchitectClick: () -> Unit,
    onTechnicalWriterClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(PathBgStart, PathBgEnd)))
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp)
    ) {
        // Top Navigation
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PathPrimary)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Header Section
        Surface(
            color = Color.White.copy(alpha = 0.5f),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Choose\nyour Path.",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = PathPrimary,
                        lineHeight = 38.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Expert-curated career roadmaps driven by AI. Select a role to begin your journey through the digital canyon of knowledge.",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Main Roles
        PrimaryRoleCard("Frontend", Icons.Default.Code, onFrontendClick)
        PrimaryRoleCard("Backend", Icons.Default.Storage, onBackendClick)
        PrimaryRoleCard("Full Stack", Icons.Default.Layers, onFullStackClick)
        PrimaryRoleCard("AI Engineer", Icons.Default.Psychology, onAIEngineerClick)
        PrimaryRoleCard("DevOps", Icons.Default.AllInclusive, onDevOpsClick)
        PrimaryRoleCard("UX Design", Icons.Default.Palette, onUXDesignClick)
        PrimaryRoleCard("Cyber Security", Icons.Default.Security, onCyberSecurityClick)
        PrimaryRoleCard("Game Developer", Icons.Default.Gamepad, onGameDeveloperClick)
        PrimaryRoleCard("Server Side Game Dev", Icons.Default.Dns, onServerSideGameDevClick)
        PrimaryRoleCard("MLOps", Icons.Default.AutoGraph, onMLOpsClick)
        PrimaryRoleCard("Product Manager", Icons.Default.Inventory, onProductManagerClick)

        Spacer(modifier = Modifier.height(48.dp))

        // Specialized Section
        Text(
            text = "Specialized &\nLeadership Roles",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = PathPrimary,
                lineHeight = 28.sp
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Specialized Roles Grid
        val specializedRoles = listOf(
            "DevSecOps" to onDevSecOpsClick,
            "Data Analysis" to onDataAnalystClick,
            "BI Analyst" to onBIAnalystClick,
            "Developer Relations" to onDevRelClick,
            "Data Science" to onDataScientistClick,
            "Data Engineer" to onDataEngineerClick,
            "Android" to onAndroidClick,
            "Machine Learning" to onMachineLearningClick,
            "PostgreSQL" to onPostgresClick,
            "iOS" to onIOSClick,
            "Blockchain" to onBlockchainClick,
            "QA" to onQAClick,
            "Software Architect" to onSoftwareArchitectClick,
            "Technical Writer" to onTechnicalWriterClick
        )

        specializedRoles.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { (title, onClick) ->
                    SpecializedRoleCard(title, onClick, modifier = Modifier.weight(1f))
                }
                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun PrimaryRoleCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Surface(
                color = PathAccent,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, null, tint = PathPrimary, modifier = Modifier.size(18.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(title, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

@Composable
fun SpecializedRoleCard(title: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(0.5.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Default.DashboardCustomize,
                null,
                tint = PathPrimary.copy(alpha = 0.3f),
                modifier = Modifier.size(16.dp)
            )
            Text(
                title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = PathPrimary,
                lineHeight = 14.sp
            )
        }
    }
}
