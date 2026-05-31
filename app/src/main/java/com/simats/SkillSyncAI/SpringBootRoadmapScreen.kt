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

val SBBgStart = Color(0xFFF9FAFF)
val SBBgEnd = Color(0xFFFFFFFF)
val SBPrimary = Color(0xFF6DB33F) // Spring Green (or use the blue from image if preferred, but original uses green. Image shows dark blue header)
val SBDarkBlue = Color(0xFF1E3A8A)
val SBHeaderColor = Color(0xFF2E3A8C)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpringBootRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.SettingsInputComponent, null, tint = SBDarkBlue, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Spring Architect", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = SBDarkBlue)
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
                .background(Brush.verticalGradient(listOf(SBBgStart, SBBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            // Header Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SBHeaderColor),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Spring Boot\nMastery Path",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            lineHeight = 38.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "A 6-phase curriculum engineered for SkillSync AI. Transcend standard development and master the architectural stratosphere.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(alpha = 0.8f),
                            lineHeight = 20.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            SBRoadmapCard(
                phase = "01",
                title = "Core Spring Concepts",
                icon = Icons.Default.Layers,
                items = listOf(
                    "Java Roadmap Prerequisites",
                    "Spring Framework Foundations",
                    "Core Principles: DI and IoC",
                    "Spring AOP & MVC Pattern",
                    "Bean Scopes and Annotations"
                ),
                preferenceManager = preferenceManager,
                prefix = "sb_p1"
            )

            // Phase 2
            SBRoadmapCard(
                phase = "02",
                title = "Spring Boot Internals",
                icon = Icons.Default.AutoFixHigh,
                items = listOf(
                    "The \"Magic\" of Spring Boot",
                    "Auto-configuration",
                    "Spring Boot Starters",
                    "Production Readiness & Actuators",
                    "Embedded Servers"
                ),
                preferenceManager = preferenceManager,
                prefix = "sb_p2"
            )

            // Phase 3
            SBRoadmapCard(
                phase = "03",
                title = "Data & Persistence",
                icon = Icons.Default.Storage,
                items = listOf(
                    "Hibernate (ORM) Concepts",
                    "Entity Lifecycle & Relationships",
                    "Transaction Management",
                    "Spring Data JPA / JDBC / MongoDB"
                ),
                preferenceManager = preferenceManager,
                prefix = "sb_p3"
            )

            // Phase 4
            SBRoadmapCard(
                phase = "04",
                title = "Spring Security",
                icon = Icons.Default.Security,
                items = listOf(
                    "Authentication & Authorization",
                    "Modern Standards: OAuth2",
                    "JWT Authentication flow"
                ),
                preferenceManager = preferenceManager,
                prefix = "sb_p4"
            )

            // Phase 5
            SBRoadmapCard(
                phase = "05",
                title = "Architecture & Cloud",
                icon = Icons.Default.Cloud,
                items = listOf(
                    "Web Development & MVC",
                    "Frontend Integration",
                    "Microservices & Spring Cloud",
                    "Service Discovery & Gateway",
                    "Resiliency & Cloud Config"
                ),
                preferenceManager = preferenceManager,
                prefix = "sb_p5"
            )

            // Phase 6
            SBRoadmapCard(
                phase = "06",
                title = "Testing & Quality",
                icon = Icons.Default.FactCheck,
                items = listOf(
                    "Testing Tools & Mocking",
                    "Integration Testing Patterns",
                    "Quality Assurance & Metrics"
                ),
                preferenceManager = preferenceManager,
                prefix = "sb_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SBRoadmapCard(
    phase: String,
    title: String,
    icon: ImageVector,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "PHASE $phase",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SBDarkBlue
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
                Icon(icon, null, tint = Color.LightGray.copy(0.5f), modifier = Modifier.size(24.dp))
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { item ->
                SBCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun SBCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = SBDarkBlue)
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
