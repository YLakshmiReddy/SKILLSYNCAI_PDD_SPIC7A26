package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudQueue
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

val CloudflareBgStart = Color(0xFFFDF8F5)
val CloudflareBgEnd = Color(0xFFFFFFFF)
val CloudflarePrimary = Color(0xFFF38020) // Cloudflare Orange
val CloudflareDark = Color(0xFF05192D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloudflareRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CloudQueue, null, tint = CloudflarePrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cloudflare Roadmap", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = CloudflareDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = CloudflareDark)
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
                .background(Brush.verticalGradient(listOf(CloudflareBgStart, CloudflareBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Mastering the Edge",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = CloudflareDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A professional curriculum designed for full-stack developers to harness the power of Cloudflare's global network and serverless ecosystem.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Section 1
            CloudflareRoadmapCard(
                number = "1",
                title = "Fundamentals & Global Network",
                items = listOf(
                    "Edge Computing Concepts",
                    "V8 Isolates Architecture",
                    "Domain Onboarding & DNS",
                    "Traffic Control (Page Rules)",
                    "WAF & Firewall Rules",
                    "Zero Trust Fundamentals"
                ),
                preferenceManager = preferenceManager,
                prefix = "cf_s1"
            )

            // Section 2
            CloudflareRoadmapCard(
                number = "2",
                title = "Cloudflare Workers",
                items = listOf(
                    "Fetch / Scheduled / Queue handlers",
                    "The env Object & Secrets",
                    "Wrangler CLI Mastery",
                    "Hono Framework Integration",
                    "HTMLRewriter API",
                    "Service Bindings"
                ),
                preferenceManager = preferenceManager,
                prefix = "cf_s2"
            )

            // Section 3
            CloudflareRoadmapCard(
                number = "3",
                title = "The Storage Suite",
                items = listOf(
                    "Workers KV (Key-Value Storage)",
                    "R2 Storage (S3-compatible)",
                    "D1 Database (SQLite at Edge)",
                    "Durable Objects (Stateful)"
                ),
                preferenceManager = preferenceManager,
                prefix = "cf_s3"
            )

            // Section 4
            CloudflareRoadmapCard(
                number = "4",
                title = "Full-Stack & AI Integration",
                items = listOf(
                    "Cloudflare Pages (Static/Dynamic)",
                    "Pages Functions",
                    "Workers AI (Inference at Edge)",
                    "Vectorize (Vector Database)",
                    "AI Gateway"
                ),
                preferenceManager = preferenceManager,
                prefix = "cf_s4"
            )

            // Section 5
            CloudflareRoadmapCard(
                number = "5",
                title = "Real-Time & Event Patterns",
                items = listOf(
                    "Queues (Message Retries)",
                    "Workflows (Long-running)",
                    "Browser Rendering API"
                ),
                preferenceManager = preferenceManager,
                prefix = "cf_s5"
            )

            // Section 6
            CloudflareRoadmapCard(
                number = "6",
                title = "Monitoring & Operations",
                items = listOf(
                    "Observability (Analytics Engine)",
                    "Logpush & Tail",
                    "Testing (Vitest, Miniflare)",
                    "Deployment Safety (Gradual Rollouts)"
                ),
                preferenceManager = preferenceManager,
                prefix = "cf_s6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CloudflareRoadmapCard(
    number: String,
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "$number. $title",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = CloudflareDark
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                CloudflareCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun CloudflareCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = CloudflarePrimary)
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
