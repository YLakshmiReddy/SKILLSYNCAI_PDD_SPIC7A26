package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Storage
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

val DEBgStart = Color(0xFFF8F9FF)
val DEBgEnd = Color(0xFFFFFFFF)
val DEPrimary = Color(0xFF1A237E)
val DEAccent = Color(0xFFE8EAF6)
val DEDarkCardBg = Color(0xFF1A1C4E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataEngineerScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Engineering, null, tint = DEPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DEPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = DEPrimary)
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
                .background(Brush.verticalGradient(listOf(DEBgStart, DEBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "DATA ENGINEER PATH",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "A structured engineering curriculum designed for the depth of big data systems. Follow this path through 7 strategic layers of mastery.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Introduction & Foundational Skills
            DERoadmapCard(
                title = "1. Introduction & Foundational Skills",
                icon = Icons.Default.Info,
                content = {
                    DESubSection("CORE PREREQUISITES", listOf("Python & Scala Foundations", "SQL Proficiency (Advanced Joins)"), preferenceManager, "de_p1_pre")
                    Spacer(modifier = Modifier.height(16.dp))
                    DESubSection("SYSTEM FUNDAMENTALS", listOf("Terminal, Git & Version Control", "Distributed Systems Basics"), preferenceManager, "de_p1_sys")
                }
            )

            // 2. Data Engineering Lifecycle
            DERoadmapCard(
                title = "2. Data Engineering Lifecycle",
                content = {
                    DELifecycleItem("Generation", "Data Sources & Lineage", "de_p2_gen", preferenceManager)
                    DELifecycleItem("Storage", "Ingestion & Processing", "de_p2_store", preferenceManager)
                    DELifecycleItem("Serving", "Serving & Analytics", "de_p2_serve", preferenceManager)
                }
            )

            // 3. Data Storage & Database
            DERoadmapCard(
                title = "3. Data Storage & Database",
                icon = Icons.Default.Storage,
                content = {
                    DESubSection("MODELING", listOf("Scaling Logic (Sharding, Partitioning)", "Normal vs Denormalized"), preferenceManager, "de_p3_mod")
                    Spacer(modifier = Modifier.height(16.dp))
                    DESubSection("RELATIONAL DBS", listOf("PostgreSQL & MySQL Mastery", "Execution Plan Optimization"), preferenceManager, "de_p3_rel")
                    Spacer(modifier = Modifier.height(16.dp))
                    DESubSection("NOSQL DBS", listOf("Document (MongoDB)", "Columnar (Cassandra)"), preferenceManager, "de_p3_nosql")
                    Spacer(modifier = Modifier.height(16.dp))
                    DESubSection("WAREHOUSING", listOf("Snowflake & BigQuery", "OLAP vs OLTP"), preferenceManager, "de_p3_ware")
                }
            )

            // 4. Data Ingestion & Pipelines
            DERoadmapCard(
                title = "4. Data Ingestion & Pipelines",
                icon = Icons.Default.Layers,
                content = {
                    DEPipelineItem("ETL / ELT Strategies", "Batch processing vs Real-time streams. Ingestion patterns.", "de_p4_etl", preferenceManager)
                    DEPipelineItem("Orchestration Tools", "Managing DAGs, retries and failure handling in complex workflows.", "de_p4_orch", preferenceManager)
                }
            )

            // 5. Cluster & Big Data (Dark Card)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                colors = CardDefaults.cardColors(containerColor = DEDarkCardBg),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = Color.White, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("5. Cluster & Big Data", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    DEBigDataItem("Spark & Hadoop", "Distributed computing and data warehousing at scale.", "de_p5_spark", preferenceManager)
                    DEBigDataItem("Cloud Ecosystem", "Amazon EMR, GCP Dataproc, Azure HDInsight managed platforms.", "de_p5_cloud", preferenceManager)
                }
            }

            // 6. Operations & Serving
            DERoadmapCard(
                title = "6. Operations & Serving",
                content = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            DESubSection("SECURITY", listOf("Access control"), preferenceManager, "de_p6_sec")
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            DESubSection("MESSAGING", listOf("Kafka, Pub/Sub"), preferenceManager, "de_p6_msg")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    DESubSection("ANALYTICS", listOf("Serving ETL, BI integration"), preferenceManager, "de_p6_ana")
                }
            )

            // 7. Governance
            DERoadmapCard(
                title = "7. Governance",
                content = {
                    DEPipelineItem("Security Essentials", "Data privacy, IAM roles, and Secret management.", "de_p7_sec", preferenceManager)
                    DEPipelineItem("Privacy & Identity", "GDPR compliance and user data protection.", "de_p7_priv", preferenceManager)
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DERoadmapCard(title: String, icon: ImageVector? = null, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    Surface(color = DEAccent, shape = RoundedCornerShape(8.dp), modifier = Modifier.size(32.dp)) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(icon, null, tint = DEPrimary, modifier = Modifier.size(18.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(20.dp))
            content()
        }
    }
}

@Composable
fun DESubSection(title: String, items: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    if (title.isNotEmpty()) {
        Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Black, color = DEPrimary, letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(8.dp))
    }
    items.forEach { item ->
        DECheckboxItem(item, "${prefix}_$item", preferenceManager)
    }
}

@Composable
fun DECheckboxItem(text: String, key: String, preferenceManager: PreferenceManager, isDark: Boolean = false) {
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
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
            colors = CheckboxDefaults.colors(checkedColor = if (isDark) Color.White else DEPrimary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 13.sp, color = if (isDark) Color.White else (if (checked) Color.Gray else Color.Black))
    }
}

@Composable
fun DELifecycleItem(label: String, desc: String, key: String, preferenceManager: PreferenceManager) {
    Surface(
        modifier = Modifier.padding(vertical = 6.dp).fillMaxWidth(),
        color = Color(0xFFF8FAFC),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            DECheckboxItem("", key, preferenceManager)
            Column {
                Text(label.uppercase(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DEPrimary)
                Text(desc, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

@Composable
fun DEPipelineItem(title: String, desc: String, key: String, preferenceManager: PreferenceManager) {
    Surface(
        modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
        color = Color(0xFFF8FAFC),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            DECheckboxItem("", key, preferenceManager)
            Column {
                Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(desc, fontSize = 12.sp, color = Color.Gray, lineHeight = 18.sp)
            }
        }
    }
}

@Composable
fun DEBigDataItem(title: String, desc: String, key: String, preferenceManager: PreferenceManager) {
    Surface(
        modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
        color = Color.White.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            DECheckboxItem("", key, preferenceManager, isDark = true)
            Column {
                Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(desc, fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f), lineHeight = 18.sp)
            }
        }
    }
}
