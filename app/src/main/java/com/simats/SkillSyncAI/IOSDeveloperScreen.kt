package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val IOSBgStart = Color(0xFFF8F9FF)
val IOSBgEnd = Color(0xFFFFFFFF)
val IOSPrimary = Color(0xFF1A237E)
val IOSDarkCardBg = Color(0xFF1A1C4E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IOSDeveloperScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = IOSPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("iOS Roadmap", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = IOSPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = IOSPrimary)
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
                .background(Brush.verticalGradient(listOf(IOSBgStart, IOSBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "SkillSync AI\niOS Developer",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = IOSPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the Apple ecosystem through our stratified learning path. From Swift fundamentals to advanced ARKit and App Store distribution.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            IOSRoadmapCard(
                phase = "1",
                title = "The Fundamentals",
                items = listOf("Swift", "Objective-C", "iOS Architecture", "Core Concepts"),
                preferenceManager = preferenceManager,
                prefix = "ios_p1"
            )

            // Phase 2
            IOSRoadmapCard(
                phase = "2",
                title = "App Components & UI",
                items = listOf("Xcode", "SwiftUI", "UIKit", "HIG (Human Interface Guidelines)"),
                preferenceManager = preferenceManager,
                prefix = "ios_p2"
            )

            // Phase 3
            IOSRoadmapCard(
                phase = "3",
                title = "Design Architecture & Techniques",
                items = listOf("Architectural Patterns", "Delegate", "Reactive", "Closures", "Free Animation"),
                preferenceManager = preferenceManager,
                prefix = "ios_p3"
            )

            // Phase 4
            IOSRoadmapCard(
                phase = "4",
                title = "Storage & Networking",
                items = listOf("Core Data", "UserDefaults", "Networking", "Concurrency"),
                preferenceManager = preferenceManager,
                prefix = "ios_p4"
            )

            // Phase 5
            IOSRoadmapCard(
                phase = "5",
                title = "Common Services",
                items = listOf("ARKit", "MapKit", "CoreGravity", "Dependency Managers"),
                preferenceManager = preferenceManager,
                prefix = "ios_p5"
            )

            // Phase 6
            IOSRoadmapCard(
                phase = "6",
                title = "Testing & Distribution",
                items = listOf("XCTest", "Fastlane", "CI/CD", "TestFlight & App Store"),
                preferenceManager = preferenceManager,
                prefix = "ios_p6"
            )

            // Phase 7 - Dark Card
            IOSDarkPhaseCard(
                phase = "7",
                title = "Continuous Learning",
                description = "Stay updated with the latest from Cupertino. Learn what brings new API and paradigms.",
                items = listOf("WWDC Updates"),
                preferenceManager = preferenceManager,
                prefix = "ios_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun IOSRoadmapCard(
    phase: String,
    title: String,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Phase $phase: $title",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = IOSPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            items.forEach { item ->
                IOSCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun IOSDarkPhaseCard(
    phase: String,
    title: String,
    description: String,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = IOSDarkCardBg),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Phase $phase: $title",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.7f),
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                IOSCheckboxItem(item, title, prefix, preferenceManager, isDark = true)
            }
        }
    }
}

@Composable
fun IOSCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager, isDark: Boolean = false) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().apply {
                    putBoolean(key, isChecked)
                    apply()
                }
            },
            colors = CheckboxDefaults.colors(
                checkedColor = if (isDark) Color.White else IOSPrimary,
                checkmarkColor = if (isDark) IOSDarkCardBg else Color.White,
                uncheckedColor = if (isDark) Color.White.copy(alpha = 0.4f) else Color.Gray
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (isDark) Color.White.copy(alpha = if (checked) 0.5f else 0.9f) else (if (checked) Color.Gray else Color.Black),
            lineHeight = 20.sp
        )
    }
}
