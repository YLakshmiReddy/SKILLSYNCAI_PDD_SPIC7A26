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

val GcpBgStart = Color(0xFFF1F3F4)
val GcpBgEnd = Color(0xFFFFFFFF)
val GcpBlue = Color(0xFF4285F4)
val GcpRed = Color(0xFFEA4335)
val GcpYellow = Color(0xFFFBBC04)
val GcpGreen = Color(0xFF34A853)
val GcpDark = Color(0xFF202124)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GcpRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CloudQueue, null, tint = GcpBlue, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("GCP Cloud Engineer", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = GcpDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = GcpDark)
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
                .background(Brush.verticalGradient(listOf(GcpBgStart, GcpBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            // GCP Logo Style Header
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(4.dp).background(GcpBlue))
                Spacer(modifier = Modifier.width(4.dp))
                Box(modifier = Modifier.size(4.dp).background(GcpRed))
                Spacer(modifier = Modifier.width(4.dp))
                Box(modifier = Modifier.size(4.dp).background(GcpYellow))
                Spacer(modifier = Modifier.width(4.dp))
                Box(modifier = Modifier.size(4.dp).background(GcpGreen))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Google Cloud Platform",
                    style = MaterialTheme.typography.labelLarge.copy(color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Cloud Architect\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = GcpDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the infrastructure, platform, and data services of Google Cloud. Track your journey from fundamentals to professional architecture.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.DarkGray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1: GCP Foundations
            GcpRoadmapCard(
                phase = "01",
                title = "GCP Foundations",
                items = listOf(
                    "Resource Hierarchy (Org, Folders, Projects)",
                    "Identity & Access Management (IAM)",
                    "Cloud Console, SDK & Cloud Shell",
                    "Billing & Quotas management"
                ),
                preferenceManager = preferenceManager,
                prefix = "gcp_p1"
            )

            // Phase 2: Compute Services
            GcpRoadmapCard(
                phase = "02",
                title = "Compute & Orchestration",
                items = listOf(
                    "Compute Engine (Virtual Machines)",
                    "Google Kubernetes Engine (GKE)",
                    "Cloud Run (Serverless Containers)",
                    "App Engine (PaaS)",
                    "Cloud Functions (FaaS)"
                ),
                preferenceManager = preferenceManager,
                prefix = "gcp_p2"
            )

            // Phase 3: Networking
            GcpRoadmapCard(
                phase = "03",
                title = "VPC & Networking",
                items = listOf(
                    "Virtual Private Cloud (VPC)",
                    "Cloud Load Balancing",
                    "Cloud DNS & Cloud CDN",
                    "Cloud Interconnect & VPN"
                ),
                preferenceManager = preferenceManager,
                prefix = "gcp_p3"
            )

            // Phase 4: Storage & Databases
            GcpRoadmapCard(
                phase = "04",
                title = "Storage & Databases",
                items = listOf(
                    "Cloud Storage (Object Storage)",
                    "Cloud SQL (Relational)",
                    "Cloud Spanner (Global SQL)",
                    "Firestore (NoSQL Document)",
                    "Cloud Bigtable (NoSQL Wide-column)"
                ),
                preferenceManager = preferenceManager,
                prefix = "gcp_p4"
            )

            // Phase 5: Data & Analytics
            GcpRoadmapCard(
                phase = "05",
                title = "Big Data & Analytics",
                items = listOf(
                    "BigQuery (Data Warehouse)",
                    "Pub/Sub (Messaging)",
                    "Dataflow (Stream/Batch Processing)",
                    "Dataproc (Managed Spark/Hadoop)"
                ),
                preferenceManager = preferenceManager,
                prefix = "gcp_p5"
            )

            // Phase 6: AI & Machine Learning
            GcpRoadmapCard(
                phase = "06",
                title = "AI & Machine Learning",
                items = listOf(
                    "Vertex AI Platform",
                    "Pre-trained APIs (Vision, NL, Translation)",
                    "AutoML",
                    "BigQuery ML"
                ),
                preferenceManager = preferenceManager,
                prefix = "gcp_p6"
            )

            // Phase 7: Operations & Security
            GcpRoadmapCard(
                phase = "07",
                title = "Operations & Security",
                items = listOf(
                    "Cloud Monitoring & Logging",
                    "Cloud Trace & Profiler",
                    "Cloud Armor (WAF)",
                    "Identity-Aware Proxy (IAP)"
                ),
                preferenceManager = preferenceManager,
                prefix = "gcp_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun GcpRoadmapCard(
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = phase,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = GcpBlue.copy(alpha = 0.2f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = GcpDark
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                GcpCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun GcpCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = GcpBlue)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else GcpDark,
            lineHeight = 20.sp
        )
    }
}
