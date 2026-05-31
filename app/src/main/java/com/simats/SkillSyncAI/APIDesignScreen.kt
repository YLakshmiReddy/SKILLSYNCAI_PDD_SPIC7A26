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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val APIBgStart = Color(0xFFF1F4FF)
val APIBgEnd = Color(0xFFFFFFFF)
val APIPrimary = Color(0xFF1A237E)
val APIDarkCardBg = Color(0xFF1A1C4E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun APIDesignScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Api, null, tint = APIPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("API Architect Pro", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = APIPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = APIPrimary)
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
                .padding(padding)
                .background(Brush.verticalGradient(listOf(APIBgStart, APIBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "API Developer\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = APIPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the journey from HTTP fundamentals to governing complex microservice architectures. Your path to becoming a Senior API Architect starts here.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Learn the Basics
            APIRoadmapCard(
                title = "1. Learn the Basics",
                items = listOf(
                    "Core Concepts" to "What are APIs? Understanding the interface between software components.",
                    "HTTP Fundamentals" to "Methods (GET, POST, etc.), Status Codes, Headers, and CORS.",
                    "Networking & Discovery" to "DNS, Load Balancers, Gateways, and Service Discovery basics."
                ),
                preferenceManager = preferenceManager,
                prefix = "api_p1"
            )

            // 2. Styles (Dark Card)
            APIDarkRoadmapCard(
                title = "2. Styles",
                items = listOf(
                    "RESTful APIs",
                    "JSON-RPC",
                    "GraphQL APIs",
                    "gRPC & SOAP"
                ),
                preferenceManager = preferenceManager,
                prefix = "api_p2"
            )

            // 3. Building & Integrating APIs
            APIRoadmapCard(
                title = "3. Building & Integrating APIs",
                items = listOf(
                    "Design Patterns" to "Versioning, Pagination, Filtering, and Error Handling strategies.",
                    "Integration Patterns" to "Webhooks, Polling, and Event-Driven Architectures.",
                    "Microservices Architecture" to "Orchestrating services using API Gateways and Service Meshes."
                ),
                preferenceManager = preferenceManager,
                prefix = "api_p3"
            )

            // 4. Auth & Management
            APISimpleRoadmapCard(
                title = "4. Auth & Management",
                items = listOf(
                    "OAuth 2.0 & JWT Tokens",
                    "RBAC / ABAC Permissions",
                    "Secret Management"
                ),
                preferenceManager = preferenceManager,
                prefix = "api_p4"
            )

            // 5. Performance & Security
            APISimpleRoadmapCard(
                title = "5. Performance & Security",
                items = listOf(
                    "Load Balancing & Retries",
                    "OWASP API Top 10",
                    "Profiling & Monitoring"
                ),
                preferenceManager = preferenceManager,
                prefix = "api_p5"
            )

            // 6. Lifecycle & Management
            APIRoadmapCard(
                title = "6. Lifecycle & Management",
                items = listOf(
                    "OpenAPI / Swagger" to "Documentation and standardisation.",
                    "Contract Testing" to "Consumer-driven contracts.",
                    "Governance" to "Policies and compliance.",
                    "Web Sockets" to "Real-time bi-directional communication."
                ),
                preferenceManager = preferenceManager,
                prefix = "api_p6"
            )

            // 7. Standards & Compliance (Gradient Card)
            APIGradientRoadmapCard(
                title = "7. Standards & Compliance",
                description = "Ensure your APIs respect global privacy laws and industry regulations.",
                tags = listOf("GDPR", "HIPAA", "SOC2", "ISO"),
                preferenceManager = preferenceManager,
                prefix = "api_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun APIRoadmapCard(
    title: String,
    items: List<Pair<String, String>>,
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
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = APIPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            items.forEach { (name, desc) ->
                APICheckboxItem(name, desc, "${prefix}_$name", preferenceManager)
            }
        }
    }
}

// Overload for list of strings - Renamed to avoid Platform declaration clash
@Composable
fun APISimpleRoadmapCard(
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
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = APIPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            items.forEach { item ->
                APICheckboxItem(item, "", "${prefix}_$item", preferenceManager)
            }
        }
    }
}

@Composable
fun APIDarkRoadmapCard(
    title: String,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = APIDarkCardBg),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                APICheckboxItem(item, "", "${prefix}_$item", preferenceManager, isDark = true)
            }
        }
    }
}

@Composable
fun APIGradientRoadmapCard(
    title: String,
    description: String,
    tags: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Brush.linearGradient(listOf(Color(0xFF5C6BC0), Color(0xFF1A237E))))
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    tags.forEach { tag ->
                        Surface(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = tag,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                APICheckboxItem("Module Completed", "", "${prefix}_completed", preferenceManager, isDark = true)
            }
        }
    }
}

@Composable
fun APICheckboxItem(name: String, desc: String, key: String, preferenceManager: PreferenceManager, isDark: Boolean = false) {
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { 
                checked = it
                preferenceManager.getSharedPrefs().edit().apply {
                    putBoolean(key, it)
                    apply()
                }
            },
            colors = CheckboxDefaults.colors(
                checkedColor = if (isDark) Color.White else APIPrimary,
                checkmarkColor = if (isDark) APIDarkCardBg else Color.White,
                uncheckedColor = if (isDark) Color.White.copy(alpha = 0.4f) else Color.Gray
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) Color.White.copy(alpha = if (checked) 0.5f else 1f) else (if (checked) Color.Gray else Color.Black)
            )
            if (desc.isNotEmpty()) {
                Text(
                    text = desc,
                    fontSize = 12.sp,
                    color = if (isDark) Color.White.copy(alpha = 0.6f) else Color.Gray,
                    lineHeight = 16.sp
                )
            }
        }
    }
}
