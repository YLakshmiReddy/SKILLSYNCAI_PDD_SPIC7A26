package com.simats.SkillSyncAI

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val AndroidBgStart = Color(0xFF1A237E)
val AndroidBgEnd = Color(0xFF3949AB)
val AndroidPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidDeveloperScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = Color.White, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AndroidBgStart)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(AndroidBgStart, AndroidBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Android Developer",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // 1. The Starting Line
            AndroidRoadmapCard(
                step = "1",
                title = "The Starting Line",
                subtitle = "Set your foundation in mobile development.",
                icon = Icons.Default.Flag,
                items = listOf(
                    "Pick a Language: Kotlin (Recommended) or Java",
                    "The Fundamentals: Principles",
                    "Development IDE: Android Studio",
                    "Second Path: Classes and OOP",
                    "Data Structures and Algorithms",
                    "SOLID, Clean Code",
                    "Version Control: Git"
                ),
                preferenceManager = preferenceManager,
                prefix = "android_s1"
            )

            // 2. Core App Components
            AndroidRoadmapCard(
                step = "2",
                title = "Core App Components",
                subtitle = "The building blocks of an Android App.",
                icon = Icons.Default.Apps,
                items = listOf(
                    "Activities & Lifecycle",
                    "Intents: Basic and Filters",
                    "Services, Broadcast Receiver, Content Providers"
                ),
                preferenceManager = preferenceManager,
                prefix = "android_s2"
            )

            // 3. Interface & Navigation
            AndroidRoadmapCard(
                step = "3",
                title = "Interface & Navigation",
                subtitle = "Designing what users see and feel.",
                icon = Icons.Default.Dashboard,
                items = listOf(
                    "Jetpack Compose (Modern UI)",
                    "Navigation Component",
                    "Context UI: Fragments, ViewModels",
                    "Material Design Principles"
                ),
                preferenceManager = preferenceManager,
                prefix = "android_s3"
            )

            // 4. Data, Network & Logic
            AndroidRoadmapCard(
                step = "4",
                title = "Data, Network & Logic",
                icon = Icons.Default.Wifi,
                items = listOf(
                    "Working with Room DB",
                    "REST API with Retrofit",
                    "JSON Parsing: Gson, Moshi",
                    "Dependency Injection: Hilt / Koin"
                ),
                preferenceManager = preferenceManager,
                prefix = "android_s4"
            )

            // 5. Architecture
            AndroidRoadmapCard(
                step = "5",
                title = "Architecture",
                icon = Icons.Default.AccountTree,
                items = listOf(
                    "MVVM / MVI Pattern",
                    "Jetpack Components",
                    "Clean Architecture",
                    "Coroutines & Flow"
                ),
                preferenceManager = preferenceManager,
                prefix = "android_s5"
            )

            // 6. Quality & Maintenance
            AndroidRoadmapCard(
                step = "6",
                title = "Quality & Maintenance",
                subtitle = "Building apps that are reliable.",
                icon = Icons.Default.BugReport,
                items = listOf(
                    "Unit Testing: JUnit, Mockito",
                    "UI Testing: Espresso, Compose Test",
                    "Debugging, LeakCanary"
                ),
                preferenceManager = preferenceManager,
                prefix = "android_s6"
            )

            // 7. Distribution
            AndroidRoadmapCard(
                step = "7",
                title = "Distribution",
                subtitle = "Launching your creation to millions.",
                icon = Icons.Default.RocketLaunch,
                items = listOf(
                    "Preparation: Proguard, R8, Bundle",
                    "Distribution: Play Store, Internal testing"
                ),
                preferenceManager = preferenceManager,
                prefix = "android_s7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AndroidRoadmapCard(
    step: String,
    title: String,
    subtitle: String? = null,
    icon: ImageVector,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = AndroidPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "$step. $title",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                AndroidCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun AndroidCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = AndroidPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 13.sp,
            color = if (checked) Color.Gray else Color.Black,
            lineHeight = 18.sp
        )
    }
}
