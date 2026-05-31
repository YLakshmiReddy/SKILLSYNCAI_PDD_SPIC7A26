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

val BackendBgStart = Color(0xFF1A237E)
val BackendBgEnd = Color(0xFF3949AB)
val BackendTextPrimary = Color(0xFFFFFFFF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackendScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Backend Roadmap", fontWeight = FontWeight.Bold) },
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
                .background(Brush.verticalGradient(listOf(BackendBgStart, BackendBgEnd)))
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Backend\nDeveloper.",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = BackendTextPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A definitive guide to mastering the server-side components of apps, databases, routing, and infrastructure.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White.copy(alpha = 0.8f),
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            BackendRoadmapCard(
                title = "Internet",
                items = listOf(
                    "How does the internet work?",
                    "What is HTTP?",
                    "Browsers and how they work",
                    "DNS and how it works",
                    "What is Domain Name?",
                    "What is hosting?"
                ),
                preferenceManager = preferenceManager
            )

            BackendRoadmapCard(
                title = "Pick a Language",
                items = listOf(
                    "Java", "Python", "Go", "Rust", "Node.js", "C#", "PHP"
                ),
                preferenceManager = preferenceManager
            )

            BackendRoadmapCard(
                title = "VCS & Deployment",
                items = listOf(
                    "Git basics", "GitHub / GitLab", "SSH", "Docker", "Web Servers (Nginx, Apache)"
                ),
                preferenceManager = preferenceManager
            )

            BackendRoadmapCard(
                title = "Data Management Strategy",
                items = listOf(
                    "Relational DBs (PostgreSQL, MySQL)",
                    "NoSQL Databases (MongoDB, Redis, Cassandra)",
                    "Database Normalization",
                    "Transactions & ACID",
                    "Database Indexing"
                ),
                preferenceManager = preferenceManager
            )

            BackendRoadmapCard(
                title = "Logic, ACID & Security",
                items = listOf(
                    "Authentication (OAuth, JWT)",
                    "Authorization (RBAC)",
                    "Data Privacy",
                    "Hashing Algorithms",
                    "Web Security (CORS, CSRF, XSS)"
                ),
                preferenceManager = preferenceManager
            )

            BackendRoadmapCard(
                title = "APIs & Messaging",
                items = listOf(
                    "RESTful APIs", "GraphQL", "gRPC", "Message Queues (RabbitMQ, Kafka)", "WebSockets"
                ),
                preferenceManager = preferenceManager
            )

            BackendRoadmapCard(
                title = "Architectural Patterns",
                items = listOf(
                    "Monolithic Architecture",
                    "Microservices",
                    "Serverless",
                    "Event Driven",
                    "Clean Architecture"
                ),
                preferenceManager = preferenceManager
            )

            BackendRoadmapCard(
                title = "Infrastructure",
                items = listOf(
                    "Cloud Providers (AWS, Azure, GCP)",
                    "CI/CD Pipelines",
                    "Monitoring & Logging",
                    "Container Orchestration (Kubernetes)",
                    "Load Balancing"
                ),
                preferenceManager = preferenceManager
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun BackendRoadmapCard(title: String, items: List<String>, preferenceManager: PreferenceManager) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = BackendBgStart
            )
            Spacer(modifier = Modifier.height(12.dp))
            items.forEach { item ->
                val key = "backend_${title}_$item"
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
                        colors = CheckboxDefaults.colors(checkedColor = BackendBgStart)
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
