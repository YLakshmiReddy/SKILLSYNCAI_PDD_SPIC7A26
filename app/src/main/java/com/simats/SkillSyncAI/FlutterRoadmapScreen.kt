package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FlutterDash
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

val FlutterBgStart = Color(0xFFEDF0FF)
val FlutterBgEnd = Color(0xFFFFFFFF)
val FlutterPrimary = Color(0xFF02569B) // Flutter Blue
val FlutterSecondary = Color(0xFF0175C2)
val FlutterDark = Color(0xFF042B59)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlutterRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.FlutterDash, null, tint = FlutterPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Flutter Architect", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = FlutterDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = FlutterDark)
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
                .background(Brush.verticalGradient(listOf(FlutterBgStart, FlutterBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            // Header Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E7FF).copy(alpha = 0.5f)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Master the\nFlutter\nEcosystem",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = FlutterDark,
                            lineHeight = 38.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "A definitive structural guide from immersion basics to high-level deployment. Watch your progress as you evolve from a Developer to an ARCHITECT.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = FlutterDark.copy(alpha = 0.7f),
                            lineHeight = 20.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            FlutterRoadmapCard(
                phase = "01",
                title = "Basics & Environment",
                items = listOf(
                    "Installation & SDK Setup (Doctor)",
                    "Dart Language Fundamentals",
                    "IDE Setup (VS Code, Android Studio)",
                    "Project Structure & Pubspec"
                ),
                preferenceManager = preferenceManager,
                prefix = "flt_p1"
            )

            // Phase 2
            FlutterRoadmapCard(
                phase = "02",
                title = "Core Widgets & Assets",
                items = listOf(
                    "Stateless vs Stateful Widgets",
                    "Material vs Cupertino Design",
                    "Building Layouts (Column, Row, Stack)",
                    "Handling Assets & Icons"
                ),
                preferenceManager = preferenceManager,
                prefix = "flt_p2"
            )

            // Phase 3
            FlutterRoadmapCard(
                phase = "03",
                title = "Advanced Dart & Data Handling",
                items = listOf(
                    "Streams & Futures (Async/Await)",
                    "Generics, Mixins & Extensions",
                    "Local Storage (Shared Prefs, SQLite)",
                    "JSON Serialization & APIs"
                ),
                preferenceManager = preferenceManager,
                prefix = "flt_p3"
            )

            // Phase 4
            FlutterRoadmapCard(
                phase = "04",
                title = "Architecture & State",
                items = listOf(
                    "MVVM & Clean Architecture",
                    "BLoC / Provider / Riverpod",
                    "Dependency Injection (GetIt)",
                    "Navigation & Routing (AutoRoute)"
                ),
                preferenceManager = preferenceManager,
                prefix = "flt_p4"
            )

            // Phase 5
            FlutterRoadmapCard(
                phase = "05",
                title = "UI/UX & Internals",
                items = listOf(
                    "Custom Painting & Render Objects",
                    "Animations (Tween, Physics-based)",
                    "Accessibility & Platform Channels",
                    "Native Integration (MethodChannels)"
                ),
                preferenceManager = preferenceManager,
                prefix = "flt_p5"
            )

            // Phase 6
            FlutterRoadmapCard(
                phase = "06",
                title = "Testing & Analytics",
                items = listOf(
                    "Unit, Widget & Integration Tests",
                    "Firebase Analytics & Crashlytics",
                    "Performance Profiling",
                    "Error Handling (Sentry)"
                ),
                preferenceManager = preferenceManager,
                prefix = "flt_p6"
            )

            // Phase 7
            FlutterRoadmapCard(
                phase = "07",
                title = "DevOps & Deploy",
                items = listOf(
                    "GitHub Actions CI/CD",
                    "Fastlane Automation",
                    "Flavor Management",
                    "App Store & Play Store Publishing"
                ),
                preferenceManager = preferenceManager,
                prefix = "flt_p7",
                isDark = true
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun FlutterRoadmapCard(
    phase: String,
    title: String,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String,
    isDark: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDark) FlutterPrimary else Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "PHASE $phase",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) Color.White.copy(alpha = 0.7f) else FlutterSecondary
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = if (isDark) Color.White else FlutterDark
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                FlutterCheckboxItem(item, title, prefix, preferenceManager, isDark)
            }
        }
    }
}

@Composable
fun FlutterCheckboxItem(
    item: String, 
    title: String, 
    prefix: String, 
    preferenceManager: PreferenceManager,
    isDark: Boolean
) {
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
                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
            },
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = if (isDark) Color.White else FlutterPrimary,
                uncheckedColor = if (isDark) Color.White.copy(alpha = 0.5f) else Color.LightGray,
                checkmarkColor = if (isDark) FlutterPrimary else Color.White
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (isDark) Color.White else if (checked) Color.Gray else FlutterDark,
            lineHeight = 20.sp
        )
    }
}
