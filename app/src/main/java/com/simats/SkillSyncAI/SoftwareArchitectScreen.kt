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

val ArchBgStart = Color(0xFFF1F4FF)
val ArchBgEnd = Color(0xFFFFFFFF)
val ArchPrimary = Color(0xFF1A237E)
val ArchAccent = Color(0xFFE8EAF6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoftwareArchitectScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountTree, null, tint = ArchPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Architect Path", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ArchPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = ArchPrimary)
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
                .background(Brush.verticalGradient(listOf(ArchBgStart, ArchBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Software\nArchitect\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = ArchPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "This roadmap details the journey from technical expert to strategic leader and designer of high-level structures of software systems.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Understand the Basics
            ArchRoadmapCard(
                stepNum = "1",
                title = "Understand the Basics",
                icon = Icons.Default.Lightbulb,
                items = listOf(
                    "Software Architect Role",
                    "Application Architecture",
                    "Systems Architecture",
                    "Enterprise Architecture"
                ),
                preferenceManager = preferenceManager,
                prefix = "arch_s1"
            )

            // 2. Responsibilities & Skills
            ArchRoadmapCard(
                stepNum = "2",
                title = "Responsibilities & Skills",
                icon = Icons.Default.Groups,
                items = listOf(
                    "Define Technical Standards",
                    "Design Complex Systems",
                    "Stakeholder Management",
                    "Mentorship & Leadership"
                ),
                preferenceManager = preferenceManager,
                prefix = "arch_s2"
            )

            // 3. Technical Skills & Tools
            ArchRoadmapCard(
                stepNum = "3",
                title = "Technical Skills & Tools",
                icon = Icons.Default.Computer,
                items = listOf(
                    "UML & Modeling",
                    "Design Patterns",
                    "Microservices vs Monolith",
                    "Serverless & Cloud Native"
                ),
                preferenceManager = preferenceManager,
                prefix = "arch_s3"
            )

            // 4. Architecture & Security
            ArchRoadmapCard(
                stepNum = "4",
                title = "Architecture & Security",
                icon = Icons.Default.Security,
                items = listOf(
                    "Secure by Design",
                    "Zero Trust Architecture",
                    "IAM & RBAC",
                    "Data Privacy Compliance"
                ),
                preferenceManager = preferenceManager,
                prefix = "arch_s4"
            )

            // 5. Data & APIs
            ArchRoadmapCard(
                stepNum = "5",
                title = "Data & APIs",
                icon = Icons.Default.Storage,
                items = listOf(
                    "Relational & NoSQL Strategy",
                    "API Design (REST, GraphQL)",
                    "Message Queues & Pub/Sub",
                    "Data Consistency Patterns"
                ),
                preferenceManager = preferenceManager,
                prefix = "arch_s5"
            )

            // 6. Management & Methodology
            ArchRoadmapCard(
                stepNum = "6",
                title = "Management & Methodology",
                icon = Icons.Default.Assignment,
                items = listOf(
                    "Agile & Scrum Integration",
                    "DevOps Culture & MLOps",
                    "Release Management",
                    "Documentation (ADRs, RFCs)"
                ),
                preferenceManager = preferenceManager,
                prefix = "arch_s6"
            )

            // 7. Infrastructure & Operations
            ArchRoadmapCard(
                stepNum = "7",
                title = "Infrastructure & Operations",
                icon = Icons.Default.Cloud,
                items = listOf(
                    "Cloud Providers (AWS, Azure)",
                    "Containerization (Docker, K8s)",
                    "Monitoring & Observability",
                    "Site Reliability Engineering"
                ),
                preferenceManager = preferenceManager,
                prefix = "arch_s7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ArchRoadmapCard(
    stepNum: String,
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
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = ArchAccent,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = ArchPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Text(
                    text = stepNum,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFF1F3F4)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = ArchPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            items.forEach { item ->
                ArchCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun ArchCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = ArchPrimary)
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
