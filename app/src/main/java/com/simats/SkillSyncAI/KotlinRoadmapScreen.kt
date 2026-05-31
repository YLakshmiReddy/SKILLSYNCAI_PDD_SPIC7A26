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

val KotlinBgStart = Color(0xFFF7F8FF)
val KotlinBgEnd = Color(0xFFFFFFFF)
val KotlinPrimary = Color(0xFF7F52FF) // Kotlin Purple
val KotlinSecondary = Color(0xFFE24462) // Kotlin Pink/Orange accent
val KotlinDark = Color(0xFF1A1C2E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KotlinRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = null,
                            tint = KotlinPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Kotlin Roadmap",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = KotlinDark
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = KotlinDark
                        )
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
                .background(Brush.verticalGradient(listOf(KotlinBgStart, KotlinBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            // Header Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Master Modern\nDevelopment",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = KotlinDark,
                            lineHeight = 38.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "A comprehensive journey through the Kotlin ecosystem, from syntax fundamentals to multiplatform cloud-native architecture.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray,
                            lineHeight = 22.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Language Basics
            KotlinRoadmapCard(
                title = "Language Basics",
                icon = Icons.Default.Terminal,
                items = listOf(
                    "Foundational Knowledge" to listOf("Variables & Constants", "Basic Syntax", "IDE Setup"),
                    "Core Logic" to listOf("Control Flow", "Functions Basics"),
                    "Data Types" to listOf("Type System", "Nullability Basics")
                ),
                preferenceManager = preferenceManager,
                prefix = "kt_basics",
                tag = "BEGINNER TRACK"
            )

            // 2. Control Flow
            KotlinRoadmapCard(
                title = "Control Flow",
                icon = Icons.Default.FilterList,
                items = listOf(
                    "Conditional Logic" to listOf("If/Else Expressions", "When Expression"),
                    "Loops & Ranges" to listOf("For, While, Do-While", "Ranges & Progressions"),
                    "Collections" to listOf("Lists, Sets, Maps", "Mutability vs Immutability")
                ),
                preferenceManager = preferenceManager,
                prefix = "kt_flow",
                tag = "INTERMEDIATE TRACK"
            )

            // 3. Functions
            KotlinRoadmapCard(
                title = "Functions",
                icon = Icons.Default.Functions,
                items = listOf(
                    "Functional Programming" to listOf("Lambdas & HOFs", "Inline Functions"),
                    "Collection Operations" to listOf("Filter, Map, FlatMap", "Sequences"),
                    "Error Handling" to listOf("Exceptions", "Nothing Type")
                ),
                preferenceManager = preferenceManager,
                prefix = "kt_functions",
                tag = "ADVANCED | 01"
            )

            // 4. Classes & Null Safety
            KotlinRoadmapCard(
                title = "Classes & Null Safety",
                icon = Icons.Default.Security,
                items = listOf(
                    "OOP & Data Classes" to listOf("Inheritance", "Data & Sealed Classes"),
                    "Generics & Delegation" to listOf("Type Parameters", "Class Delegation"),
                    "The Billion Dollar Error" to listOf("Null Safety", "Smart Casts")
                ),
                preferenceManager = preferenceManager,
                prefix = "kt_classes",
                tag = "HIGHER LEVEL"
            )

            // 5. Async & Ecosystem
            KotlinRoadmapCard(
                title = "Async & Ecosystem",
                icon = Icons.Default.Update,
                items = listOf(
                    "Coroutines" to listOf("Suspending Functions", "Scopes & Jobs"),
                    "Build Tools" to listOf("Gradle Kotlin DSL", "Multi-module Setup")
                ),
                preferenceManager = preferenceManager,
                prefix = "kt_ecosystem",
                tag = "EXPERT TIER"
            )

            // 6. Multiplatform Mastery
            MultiplatformMasterySection()

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun KotlinRoadmapCard(
    title: String,
    icon: ImageVector,
    items: List<Pair<String, List<String>>>,
    preferenceManager: PreferenceManager,
    prefix: String,
    tag: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = KotlinPrimary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = KotlinPrimary, modifier = Modifier.size(18.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = KotlinDark
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { (subTitle, subItems) ->
                Text(
                    text = subTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = KotlinPrimary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                subItems.forEach { item ->
                    KotlinCheckboxItem(item, subTitle, prefix, preferenceManager)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.FadedGray())

            Text(
                text = tag,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun KotlinCheckboxItem(item: String, subTitle: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${subTitle}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // L-shape indicator
        Box(modifier = Modifier.size(12.dp).padding(end = 4.dp)) {
            androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 2f
                drawLine(
                    color = Color.LightGray,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(0f, size.height),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = Color.LightGray,
                    start = androidx.compose.ui.geometry.Offset(0f, size.height),
                    end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                    strokeWidth = strokeWidth
                )
            }
        }
        
        Text(
            text = item,
            fontSize = 13.sp,
            color = if (checked) Color.Gray else KotlinDark,
            modifier = Modifier.weight(1f)
        )
        
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
            },
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(checkedColor = KotlinPrimary)
        )
    }
}

@Composable
fun MultiplatformMasterySection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = KotlinDark),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Devices, null, tint = Color.White, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Multiplatform Mastery",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Deploy Kotlin anywhere: From native iOS apps to high-performance microservices and frontend web development.",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                PlatformBox("MOBILE", "Android & iOS", Modifier.weight(1f))
                PlatformBox("BACKEND", "Ktor & Spring", Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                PlatformBox("WEB", "Kotlin/JS & Wasm", Modifier.weight(1f))
                PlatformBox("DATA", "ML & Analytics", Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun PlatformBox(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = Color.White.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = KotlinPrimary)
            Text(subtitle, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
        }
    }
}

fun Color.Companion.FadedGray() = Color(0xFFF1F1F1)
