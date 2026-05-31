package com.simats.SkillSyncAI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val SystemDesignBgStart = Color(0xFFF8FAFF)
val SystemDesignBgEnd = Color(0xFFFFFFFF)
val SystemDesignPrimary = Color(0xFF4F46E5) // Indigo
val SystemDesignDark = Color(0xFF1E1B4B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemDesignRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Architecture, null, tint = SystemDesignPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = SystemDesignDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(SystemDesignBgStart, SystemDesignBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "System Design\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = SystemDesignDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the art of designing scalable, reliable, and maintainable large-scale systems. Your personalized path from foundations to advanced reliability patterns.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            SystemDesignCard(
                number = "1",
                title = "Core Concepts & Foundations",
                icon = Icons.Default.VerticalAlignTop,
                items = listOf("Performance Metrics", "Theoretical Frameworks"),
                estTime = "EST. 2 WEEKS",
                preferenceManager = preferenceManager,
                prefix = "sys_p1"
            )

            // Phase 2
            SystemDesignCard(
                number = "2",
                title = "Key Infrastructure Components",
                icon = Icons.Default.Dns,
                items = listOf("DNS & CDNs", "Load Balancers", "Caching Strategies"),
                estTime = "EST. 3 WEEKS",
                preferenceManager = preferenceManager,
                prefix = "sys_p2"
            )

            // Phase 3
            SystemDesignCard(
                number = "3",
                title = "Data & Storage Layer",
                icon = Icons.Default.Storage,
                items = listOf("SQL vs. NoSQL Types", "RDBMS Optimization", "ACID vs. BASE"),
                aiTip = "AI TIP: EXPLORE MONGODB",
                preferenceManager = preferenceManager,
                prefix = "sys_p3"
            )

            // Phase 4
            SystemDesignCard(
                number = "4",
                title = "Communication",
                icon = Icons.AutoMirrored.Filled.CompareArrows,
                items = listOf("HTTP, TCP, UDP", "Message/Task Queues", "Idempotent Operations"),
                estTime = "EST. 4 WEEKS",
                preferenceManager = preferenceManager,
                prefix = "sys_p4"
            )

            // Phase 5
            SystemDesignCard(
                number = "5",
                title = "Advanced Patterns",
                icon = Icons.Default.StarOutline,
                items = listOf("Microservices Architecture", "CQRS & Event Sourcing", "Cloud Design Patterns"),
                preferenceManager = preferenceManager,
                prefix = "sys_p5"
            )

            // Phase 6
            SystemDesignCard(
                number = "6",
                title = "Reliability & Monitoring",
                icon = Icons.Default.Timeline,
                items = listOf("Observability Stack", "Resiliency Patterns", "Performance Antipatterns"),
                preferenceManager = preferenceManager,
                prefix = "sys_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SystemDesignCard(
    number: String,
    title: String,
    icon: ImageVector,
    items: List<String>,
    estTime: String? = null,
    aiTip: String? = null,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Surface(
                    modifier = Modifier.size(24.dp),
                    color = Color.Transparent,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, if (cardChecked) SystemDesignPrimary else Color.LightGray)
                ) {
                    Checkbox(
                        checked = cardChecked,
                        onCheckedChange = { isChecked ->
                            cardChecked = isChecked
                            preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Transparent,
                            uncheckedColor = Color.Transparent,
                            checkmarkColor = SystemDesignPrimary
                        )
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$number. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = SystemDesignDark,
                    modifier = Modifier.weight(1f)
                )
                Icon(icon, null, tint = Color.LightGray, modifier = Modifier.size(16.dp))
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { item ->
                SystemDesignItemRow(item, title, prefix, preferenceManager)
            }

            if (estTime != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccessTime, null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = estTime, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                }
            }

            if (aiTip != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AutoAwesome, null, tint = SystemDesignPrimary, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = aiTip, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SystemDesignPrimary)
                }
            }
        }
    }
}

@Composable
fun SystemDesignItemRow(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(20.dp),
            color = Color.Transparent,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, if (checked) SystemDesignPrimary else Color.LightGray)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    checked = isChecked
                    preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Transparent,
                    uncheckedColor = Color.Transparent,
                    checkmarkColor = SystemDesignPrimary
                )
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else SystemDesignDark,
            lineHeight = 20.sp
        )
    }
}
