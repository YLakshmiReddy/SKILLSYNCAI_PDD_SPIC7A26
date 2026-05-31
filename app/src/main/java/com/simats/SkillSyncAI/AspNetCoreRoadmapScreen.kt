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

val AspNetBgStart = Color(0xFFF1F4FF)
val AspNetBgEnd = Color(0xFFFFFFFF)
val AspNetPrimary = Color(0xFF512BD4) // .NET Purple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AspNetCoreRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Layers, null, tint = AspNetPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("ASP.NET Core Roadmap", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AspNetPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = AspNetPrimary)
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
                .background(Brush.verticalGradient(listOf(AspNetBgStart, AspNetBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "ASP.NET Core\nMastery Path",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = AspNetPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the powerful, cross-platform framework for building modern, cloud-based, Internet-connected applications.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. C# Fundamentals
            AspNetRoadmapCard(
                phase = "1",
                title = "C# Fundamentals",
                items = listOf("Syntax & Basic Types", "Object-Oriented Programming", "LINQ", "Async / Await", "Generics & Collections"),
                preferenceManager = preferenceManager,
                prefix = "aspnet_p1"
            )

            // 2. Web Basics & .NET Setup
            AspNetRoadmapCard(
                phase = "2",
                title = "Web Basics & .NET Setup",
                items = listOf("HTTP Protocol", "REST Architecture", "Dotnet CLI", "Project Structure", "Startup & Middleware"),
                preferenceManager = preferenceManager,
                prefix = "aspnet_p2"
            )

            // 3. ASP.NET Core MVC & APIs
            AspNetRoadmapCard(
                phase = "3",
                title = "MVC & Web APIs",
                items = listOf("Controllers & Actions", "Routing", "Model Binding", "Dependency Injection", "Filters & Attributes"),
                preferenceManager = preferenceManager,
                prefix = "aspnet_p3"
            )

            // 4. Data Access
            AspNetRoadmapCard(
                phase = "4",
                title = "Data Access",
                items = listOf("Entity Framework Core", "Migrations", "Repository Pattern", "Dapper (Micro-ORM)", "Database Provider (SQL Server/Postgres)"),
                preferenceManager = preferenceManager,
                prefix = "aspnet_p4"
            )

            // 5. Security & Auth
            AspNetRoadmapCard(
                phase = "5",
                title = "Security & Auth",
                items = listOf("Identity Framework", "JWT Authentication", "Role-based Authorization", "CORS & Data Protection", "Secret Management"),
                preferenceManager = preferenceManager,
                prefix = "aspnet_p5"
            )

            // 6. Real-time & Communication
            AspNetRoadmapCard(
                phase = "6",
                title = "Communication",
                items = listOf("SignalR for Real-time", "gRPC Services", "GraphQL with HotChocolate", "HttpClient & Refit"),
                preferenceManager = preferenceManager,
                prefix = "aspnet_p6"
            )

            // 7. Performance & Testing
            AspNetRoadmapCard(
                phase = "7",
                title = "Performance & Testing",
                items = listOf("Caching (In-memory/Redis)", "Background Tasks (Worker Services)", "Unit Testing (xUnit/NUnit)", "Integration Testing"),
                preferenceManager = preferenceManager,
                prefix = "aspnet_p7"
            )

            // 8. Deployment & Cloud
            AspNetRoadmapCard(
                phase = "8",
                title = "Deployment & Cloud",
                items = listOf("Docker & Containers", "CI/CD (GitHub Actions/Azure DevOps)", "Azure App Service / AWS", "Logging (Serilog/ELK)"),
                preferenceManager = preferenceManager,
                prefix = "aspnet_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AspNetRoadmapCard(
    phase: String,
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
                    color = AspNetPrimary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = phase, color = AspNetPrimary, fontWeight = FontWeight.Bold)
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
                AspNetCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun AspNetCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = AspNetPrimary)
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
