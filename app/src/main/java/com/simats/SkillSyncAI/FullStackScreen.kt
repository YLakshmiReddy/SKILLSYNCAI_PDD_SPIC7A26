package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

val FullStackBgStart = Color(0xFF1A237E)
val FullStackBgEnd = Color(0xFF00B0FF)
val FullStackTextPrimary = Color(0xFFFFFFFF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullStackScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Full Stack Roadmap", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Brush.verticalGradient(listOf(FullStackBgStart, FullStackBgEnd)))
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Full Stack\nDeveloper.",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = FullStackTextPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "The complete path to mastering both client-side and server-side development. Build entire applications from scratch.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White.copy(alpha = 0.8f),
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Frontend Fundamentals
            SectionHeader("1. FRONTEND FUNDAMENTALS")
            FullStackRoadmapCard(
                title = "Client Side",
                items = listOf("HTML & Semantic UI", "CSS & Responsive Design", "JavaScript (ES6+)", "React / Vue / Angular", "State Management (Redux/Zustand)", "TypeScript"),
                preferenceManager = preferenceManager,
                prefix = "fs_fe"
            )

            // 2. Backend & API
            SectionHeader("2. BACKEND & API")
            FullStackRoadmapCard(
                title = "Server Side",
                items = listOf("Node.js / Python / Java", "Express / Django / Spring Boot", "RESTful API Design", "GraphQL / gRPC", "Auth (JWT, OAuth2)", "Caching (Redis)"),
                preferenceManager = preferenceManager,
                prefix = "fs_be"
            )

            // 3. Data Management
            SectionHeader("3. DATA MANAGEMENT")
            FullStackRoadmapCard(
                title = "Databases",
                items = listOf("Relational (PostgreSQL, MySQL)", "NoSQL (MongoDB, DynamoDB)", "ORM / ODM (Prisma, Mongoose)", "Database Migration", "ACID & Transactions", "Search Engines (ElasticSearch)"),
                preferenceManager = preferenceManager,
                prefix = "fs_db"
            )

            // 4. Mobile & Hybrid
            SectionHeader("4. MOBILE DEVELOPMENT")
            FullStackRoadmapCard(
                title = "Mobile Platforms",
                items = listOf("React Native", "Flutter", "Kotlin Multiplatform", "iOS / Android Native Basics"),
                preferenceManager = preferenceManager,
                prefix = "fs_mob"
            )

            // 5. Deployment & DevOps
            SectionHeader("5. DEPLOYMENT & DEVOPS")
            FullStackRoadmapCard(
                title = "Infrastructure",
                items = listOf("Git & Collaboration", "Docker & Containers", "CI/CD Pipelines", "AWS / Azure / GCP", "Monitoring (Prometheus, Grafana)", "Kubernetes"),
                preferenceManager = preferenceManager,
                prefix = "fs_do"
            )

            // 6. Testing & Quality
            SectionHeader("6. QUALITY ASSURANCE")
            FullStackRoadmapCard(
                title = "Testing",
                items = listOf("Unit Testing (Jest, JUnit)", "Integration Testing", "E2E Testing (Cypress)", "TDD Practices"),
                preferenceManager = preferenceManager,
                prefix = "fs_qa"
            )

            // 7. Software Architecture
            SectionHeader("7. ARCHITECTURE & DESIGN")
            FullStackRoadmapCard(
                title = "Design Patterns",
                items = listOf("SOLID Principles", "Microservices", "Serverless Architecture", "System Design", "Scalability Patterns"),
                preferenceManager = preferenceManager,
                prefix = "fs_arch"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Black,
        color = Color.White,
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun FullStackRoadmapCard(title: String, items: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = FullStackBgStart
            )
            Spacer(modifier = Modifier.height(12.dp))
            items.forEach { item ->
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
                        colors = CheckboxDefaults.colors(checkedColor = FullStackBgStart)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item,
                        fontSize = 14.sp,
                        color = if (checked) Color.Gray else Color.Black,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
