package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Verified
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

// Color Palette
val RoadmapBgStart = Color(0xFFF3F3FF) // Soft Lavender
val RoadmapBgEnd = Color(0xFFFFFFFF)
val IconBoxTint = Color(0xFFE8EAF6)
val PrimaryPurple = Color(0xFF5C6BC0)
val TextDark = Color(0xFF1A1C1E)
val TextSecondary = Color(0xFF757575)

@Composable
fun RoadmapScreen(
    onDashboardClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onProfileClick: () -> Unit,
    onProjectIdeasClick: () -> Unit,
    onBestPracticesClick: () -> Unit,
    onRoleBasedRoadmapsClick: () -> Unit,
    onSkillBasedRoadmapsClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { RoadmapBottomBar(onDashboardClick, onAnalysisClick, onProfileClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(RoadmapBgStart, RoadmapBgEnd),
                        startY = 0f,
                        endY = 1000f
                    )
                )
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // Logo / Header
            Row(
                modifier = Modifier.statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = PrimaryPurple,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "SkillSync AI",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextDark
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Title and Subtitle
            Text(
                text = "Roadmap",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp,
                    color = TextDark
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Forge your path through the digital canyon. Track your progression and discover specialized learning trajectories.",
                color = TextSecondary,
                lineHeight = 22.sp,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Roadmap Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextDark
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Roadmap Category Cards
            RoadmapCategoryCard(
                icon = Icons.Default.AssignmentInd,
                title = "Role Based Roadmaps",
                description = "Structured learning paths curated for specific job titles like AI Engineer, Data Architect, or UI/UX Lead.",
                onClick = onRoleBasedRoadmapsClick
            )
            RoadmapCategoryCard(
                icon = Icons.Default.Psychology,
                title = "Skill Based Roadmaps",
                description = "Deep dive into specific technologies or soft skills. Master React, Python, or Project Management at your own pace.",
                onClick = onSkillBasedRoadmapsClick
            )
            RoadmapCategoryCard(
                icon = Icons.Default.Lightbulb,
                title = "Project Ideas",
                description = "Apply your knowledge with AI-suggested projects that challenge your current skill level and build your portfolio.",
                onClick = onProjectIdeasClick
            )
            RoadmapCategoryCard(
                icon = Icons.Default.Verified,
                title = "Best Practices",
                description = "Curated guides on clean code, system design, and AI ethics to ensure your skills meet professional industry standards.",
                onClick = onBestPracticesClick
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun RoadmapCategoryCard(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Surface(
                color = IconBoxTint,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = PrimaryPurple, modifier = Modifier.size(20.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                color = TextSecondary,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun RoadmapBottomBar(
    onDashboardClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.navigationBarsPadding()
    ) {
        NavigationBarItem(
            selected = false,
            onClick = onDashboardClick,
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("HOME", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onAnalysisClick,
            icon = { Icon(Icons.Default.Analytics, contentDescription = "Analysis") },
            label = { Text("ANALYSIS", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Map, contentDescription = "Roadmap") },
            label = { Text("ROADMAP", fontSize = 10.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                indicatorColor = PrimaryPurple
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = onProfileClick,
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("PROFILE", fontSize = 10.sp) }
        )
    }
}
