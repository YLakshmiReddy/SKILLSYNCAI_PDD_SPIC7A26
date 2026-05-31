package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoleMatcherScreen(
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF0F172A)
                )
            }
            Text(
                text = "Role Matcher",
                modifier = Modifier.weight(1f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
            Spacer(modifier = Modifier.width(48.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp)
        ) {
            Text(
                text = "Suggested Career Roles",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Selected Role Chip
            Surface(
                color = Color(0xFF1D4ED8),
                shape = RoundedCornerShape(24.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Senior Product Designer",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Skill Roadmap",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "Path to Senior Product Designer",
                        fontSize = 14.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
                Surface(
                    color = Color(0xFFDBEAFE),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "60% COMPLETE",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1D4ED8)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Roadmap Timeline
            RoadmapItem(
                title = "UI Design Fundamentals",
                description = "Mastered typography, color theory, and layout.",
                status = "Completed",
                icon = Icons.Default.Check,
                iconColor = Color(0xFF22C55E),
                isLast = false
            )

            RoadmapItem(
                title = "User Research",
                description = "Qualitative and quantitative methodology.",
                status = "Completed",
                icon = Icons.Default.Check,
                iconColor = Color(0xFF22C55E),
                isLast = false
            )

            RoadmapItem(
                title = "Prototyping & Motion",
                description = "Advanced Figma animations and logic flows.",
                status = "In Progress (68%)",
                icon = Icons.Default.PlayArrow,
                iconColor = Color(0xFF1D4ED8),
                progress = 0.68f,
                isLast = false
            )

            RoadmapItem(
                title = "React for Designers",
                description = "Connecting design components to code.",
                status = "Locked",
                icon = Icons.Default.Lock,
                iconColor = Color(0xFF94A3B8),
                isLocked = true,
                isLast = false
            )

            RoadmapItem(
                title = "Cloud Architecture",
                description = "Designing for scalable enterprise systems.",
                status = "Locked",
                icon = Icons.Default.Lock,
                iconColor = Color(0xFF94A3B8),
                isLocked = true,
                isLast = true
            )
        }
    }
}

@Composable
fun RoadmapItem(
    title: String,
    description: String,
    status: String,
    icon: ImageVector,
    iconColor: Color,
    progress: Float? = null,
    isLocked: Boolean = false,
    isLast: Boolean = false
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (isLocked) Color(0xFFF1F5F9) else iconColor.copy(alpha = 0.1f))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(if (isLocked) Color(0xFFE2E8F0) else iconColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(80.dp)
                        .background(Color(0xFFE2E8F0))
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.padding(top = 4.dp)) {
            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = if (isLocked) Color(0xFF94A3B8) else Color(0xFF0F172A)
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color(0xFF64748B),
                lineHeight = 20.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
            
            if (progress != null) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .width(180.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = Color(0xFF1D4ED8),
                    trackColor = Color(0xFFE2E8F0)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                if (status == "Completed") {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFF22C55E),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                } else if (isLocked) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                
                Text(
                    text = status,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (status == "Completed") Color(0xFF22C55E) else if (progress != null) Color(0xFF1D4ED8) else Color(0xFF94A3B8)
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
