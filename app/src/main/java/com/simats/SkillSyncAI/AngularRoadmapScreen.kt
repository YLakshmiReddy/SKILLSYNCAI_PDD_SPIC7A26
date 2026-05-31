package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Code
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

val AngularBgStart = Color(0xFFF1F4FF)
val AngularBgEnd = Color(0xFFFFFFFF)
val AngularPrimary = Color(0xFFDD0031) // Angular Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AngularRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Code, null, tint = AngularPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Angular Roadmap", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AngularPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = AngularPrimary)
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
                .background(Brush.verticalGradient(listOf(AngularBgStart, AngularBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Mastering\nAngular",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A comprehensive architectural path for building enterprise-grade web applications. Track your progress across 7 core mastery phases.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            AngularRoadmapCard(
                phaseNum = "1",
                title = "Prerequisites & TypeScript",
                items = listOf(
                    "JavaScript Roadmap",
                    "Basics (Types, Interfaces, Enums)",
                    "Advanced (Generics, Decorators)",
                    "Configuration (tsconfig.json)"
                ),
                preferenceManager = preferenceManager,
                prefix = "angular_p1"
            )

            // Phase 2
            AngularRoadmapCard(
                phaseNum = "2",
                title = "Workspace & Components",
                items = listOf(
                    "Angular CLI",
                    "Components Basics (Structure, Lifecycle Hooks, Communication)",
                    "Templates & Directives (Directives, Interpolation, Pipes)"
                ),
                preferenceManager = preferenceManager,
                prefix = "angular_p2"
            )

            // Phase 3
            AngularRoadmapCard(
                phaseNum = "3",
                title = "Services & DI",
                items = listOf(
                    "Dependency Injection (DI)",
                    "Services",
                    "RxJS Basics"
                ),
                preferenceManager = preferenceManager,
                prefix = "angular_p3"
            )

            // Phase 4
            AngularRoadmapCard(
                phaseNum = "4",
                title = "Forms & API",
                items = listOf(
                    "Angular Forms (Template Driven, Reactive)",
                    "HTTP Client (CRUD, Interceptors)"
                ),
                preferenceManager = preferenceManager,
                prefix = "angular_p4"
            )

            // Phase 5
            AngularRoadmapCard(
                phaseNum = "5",
                title = "Routing & State",
                items = listOf(
                    "Angular Router (Routes, Guards, Lazy Loading)",
                    "State Management (NgRx, NGXS, Akita, RxJS Services)"
                ),
                preferenceManager = preferenceManager,
                prefix = "angular_p5"
            )

            // Phase 6
            AngularRoadmapCard(
                phaseNum = "6",
                title = "Testing & Maintenance",
                items = listOf(
                    "Unit Testing (Jasmine, Karma)",
                    "End-to-End Testing (Cypress, Playwright)"
                ),
                preferenceManager = preferenceManager,
                prefix = "angular_p6"
            )

            // Phase 7
            AngularRoadmapCard(
                phaseNum = "7",
                title = "Advanced Concepts & Ecosystem",
                items = listOf(
                    "Advanced Features (Change Detection, Angular Signals, SSR)",
                    "Deployment & Environments",
                    "Mobile Applications (Ionic, NativeScript)"
                ),
                preferenceManager = preferenceManager,
                prefix = "angular_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AngularRoadmapCard(
    phaseNum: String,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = AngularPrimary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = phaseNum, color = AngularPrimary, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                AngularCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun AngularCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = AngularPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else Color.Black,
            lineHeight = 20.sp
        )
    }
}
