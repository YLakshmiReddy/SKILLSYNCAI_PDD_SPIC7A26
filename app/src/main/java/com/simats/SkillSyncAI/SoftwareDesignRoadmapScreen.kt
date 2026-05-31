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

val SDBgStart = Color(0xFFF8F9FF)
val SDBgEnd = Color(0xFFFFFFFF)
val SDPrimary = Color(0xFF5361BB) // Soft Purple/Blue
val SDDark = Color(0xFF1E1B4B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoftwareDesignRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountTree, null, tint = SDPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = SDDark)
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
                .background(Brush.verticalGradient(listOf(SDBgStart, SDBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Software Design &\nArchitecture\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the art of building scalable, maintainable, and robust systems. From code to cloud, a structured path for the modern engineer.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Clean Code
            SDRoadmapCard(
                title = "Clean Code",
                icon = Icons.Default.Code,
                items = listOf(
                    "Consistency: Planning & Structure",
                    "Meaningful Names",
                    "Formatting",
                    "Function & Class Design",
                    "Advanced Patterns (Cohesion, Coupling)"
                ),
                preferenceManager = preferenceManager,
                prefix = "sd_clean"
            )

            // 2. Paradigms
            SDRoadmapCard(
                title = "Paradigms",
                icon = Icons.Default.FilterCenterFocus,
                sections = listOf(
                    "STRUCTURAL" to listOf("Structured & Functional"),
                    "OOP PRINCIPLES" to listOf("SOLID Foundation", "Interfaces & DI Core")
                ),
                preferenceManager = preferenceManager,
                prefix = "sd_para"
            )

            // 3. Design Patterns
            SDRoadmapCard(
                title = "Design Patterns",
                icon = Icons.Default.GridView,
                sections = listOf(
                    "Core Design Principles" to listOf("SOLID, DRY, YAGNI, Law of Demeter"),
                    "OO Design" to listOf("Promote the use of Patterns, Proper logical abstraction"),
                    "Pattern Families" to listOf("GoF Patterns: Creational, Structural, Behavioral")
                ),
                preferenceManager = preferenceManager,
                prefix = "sd_patterns"
            )

            // 4. Architectural Styles
            SDRoadmapCard(
                title = "Architectural Styles",
                icon = Icons.Default.Dashboard,
                items = listOf(
                    "Structural Styles: Monolithic vs Layered, Component-Based",
                    "Communication: Messaging / Distributed Systems",
                    "Core Principles: Patterns, Detail, Coupling & Cohesion",
                    "Boundaries: Drawing the lines between consumers"
                ),
                preferenceManager = preferenceManager,
                prefix = "sd_styles"
            )

            // 5. Patterns (Specific Archi)
            SDRoadmapCard(
                title = "Patterns",
                icon = Icons.Default.AutoAwesomeMotion,
                items = listOf(
                    "DDD, MVC, Microservices",
                    "Blackboard & Hexagonal",
                    "Serverless Architecture",
                    "Event Sourcing & CQRS"
                ),
                preferenceManager = preferenceManager,
                prefix = "sd_patterns_archi"
            )

            // 6. Enterprise Logic
            SDRoadmapCard(
                title = "Enterprise Logic",
                icon = Icons.Default.Business,
                sections = listOf(
                    "DATA TRANSFER & ACCESS" to listOf("DTOs (Data Transfer Objects)", "Repositories & Mappers", "ORMs (Object-Relational Mapping)"),
                    "LOGIC STRUCTURE" to listOf("Use Cases: Application specific logic", "Transaction Script", "Domain Model & Entities")
                ),
                preferenceManager = preferenceManager,
                prefix = "sd_enterprise"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SDRoadmapCard(
    title: String,
    icon: ImageVector,
    items: List<String> = emptyList(),
    sections: List<Pair<String, List<String>>> = emptyList(),
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
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = SDPrimary, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = SDDark
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            if (sections.isNotEmpty()) {
                sections.forEach { (sectionTitle, sectionItems) ->
                    Text(
                        text = sectionTitle,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SDPrimary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    sectionItems.forEach { item ->
                        SDCheckboxItem(item, sectionTitle, prefix, preferenceManager)
                    }
                }
            } else {
                items.forEach { item ->
                    SDCheckboxItem(item, title, prefix, preferenceManager)
                }
            }
        }
    }
}

@Composable
fun SDCheckboxItem(item: String, section: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${section}_$item"
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
            colors = CheckboxDefaults.colors(checkedColor = SDPrimary)
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
