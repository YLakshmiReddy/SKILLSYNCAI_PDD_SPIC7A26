package com.simats.SkillSyncAI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color Palette
val PrimaryBlue = Color(0xFF1E88E5)
val SuccessGreen = Color(0xFF4CAF50)
val BgGradientLight = Color(0xFFF0F7FF)
val CardOutline = Color(0xFFE3F2FD)

@Composable
fun AnalysisResultsScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    val matchScore = preferenceManager.getCurrentScore()
    
    val compatibilityText = when {
        matchScore >= 80 -> "HIGH COMPATIBILITY"
        matchScore >= 60 -> "GOOD COMPATIBILITY"
        matchScore >= 40 -> "MODERATE COMPATIBILITY"
        else -> "LOW COMPATIBILITY"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(BgGradientLight, Color.White)
                )
            )
    ) {
        TopBar(onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // --- SCORE SECTION ---
            Box(contentAlignment = Alignment.Center, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                CircularProgressIndicator(
                    progress = { matchScore / 100f },
                    modifier = Modifier.size(200.dp),
                    color = PrimaryBlue,
                    strokeWidth = 12.dp,
                    trackColor = Color(0xFFE1EBF7),
                    strokeCap = StrokeCap.Round
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("$matchScore%", fontSize = 42.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1A1C1E))
                    Text("MATCH SCORE", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue, letterSpacing = 1.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    compatibilityText,
                    color = PrimaryBlue,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- SKILLS SECTION ---
            Text("Skills Deep Dive", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Strengths",
                color = PrimaryBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            HorizontalDivider(color = PrimaryBlue, thickness = 2.dp)

            Spacer(modifier = Modifier.height(16.dp))

            StrengthItem(icon = Icons.Default.Code, title = "Java", level = "Expert Level")
            StrengthItem(icon = Icons.Default.Layers, title = "Spring Boot", level = "Intermediate")
            StrengthItem(icon = Icons.Default.Storage, title = "SQL", level = "Advanced")

            Spacer(modifier = Modifier.height(24.dp))

            // --- SKILL GAPS ---
            Text(
                "SKILL GAPS TO ADDRESS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                GapChip("Docker")
                GapChip("AWS")
                GapChip("Kubernetes")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- AI RECOMMENDATION CARD ---
            AIRecommendationCard()

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun TopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick, modifier = Modifier.background(Color.White, CircleShape)) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.SwapHoriz, contentDescription = null, tint = PrimaryBlue)
            Spacer(modifier = Modifier.width(4.dp))
            Text("SkillSync AI", fontWeight = FontWeight.ExtraBold, color = Color(0xFF1A1C1E))
        }
    }
    Text(
        "Analysis Results",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@Composable
fun StrengthItem(icon: ImageVector, title: String, level: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, CardOutline)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(modifier = Modifier.size(40.dp), color = Color(0xFFF0F7FF), shape = RoundedCornerShape(8.dp)) {
                Icon(icon, null, tint = PrimaryBlue, modifier = Modifier.padding(8.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, color = Color(0xFF1A1C1E))
                Text(level, color = Color.Gray, fontSize = 12.sp)
            }
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SuccessGreen)
        }
    }
}

@Composable
fun GapChip(label: String) {
    Surface(
        color = Color(0xFFF1F3F4),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Icon(Icons.Default.Add, null, modifier = Modifier.size(14.dp), tint = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(label, fontSize = 13.sp, color = Color.DarkGray, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun AIRecommendationCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PrimaryBlue),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, null, tint = Color.White, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("AI Recommendation", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "You're a strong candidate for this role! To secure the position, focus on demonstrating your backend expertise. We recommend taking a quick crash course on Docker deployment to round out your profile.",
                color = Color.White.copy(alpha = 0.9f),
                lineHeight = 22.sp,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Text("View Learning Path", color = PrimaryBlue, fontWeight = FontWeight.Bold)
            }
        }
    }
}