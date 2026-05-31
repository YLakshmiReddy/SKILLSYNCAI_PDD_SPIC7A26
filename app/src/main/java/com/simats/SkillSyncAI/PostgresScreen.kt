package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Launch
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

val PostgresBgStart = Color(0xFFF1F4FF)
val PostgresBgEnd = Color(0xFFFFFFFF)
val PostgresPrimary = Color(0xFF1A237E)
val PostgresAccent = Color(0xFFE8EAF6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostgresScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Storage, null, tint = PostgresPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("PostgreSQL Roadmap", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PostgresPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PostgresPrimary)
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
                .background(Brush.verticalGradient(listOf(PostgresBgStart, PostgresBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Surface(
                color = PostgresPrimary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Text(
                    text = "SKILLSYNC AI LEARNER PATH",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = PostgresPrimary
                )
            }

            Text(
                text = "Mastering the\nCanyon of\nPostgreSQL",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A structured, stratified journey from relational fundamentals to low-level internal optimization. Designed for architects and database engineers.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            PostgresRoadmapCard(
                phaseNum = "01",
                title = "Phase 1: Introduction",
                subtitle = "Basic RDBMS Concepts & Core Fundamentals.",
                icon = Icons.AutoMirrored.Filled.Launch,
                items = listOf("Core Fundamentals", "Object & Relational Model", "Architecture Overview", "ACID Properties"),
                preferenceManager = preferenceManager,
                prefix = "pg_p1"
            )

            // Phase 2
            PostgresRoadmapCard(
                phaseNum = "02",
                title = "Phase 2: Setup & SQL",
                subtitle = "Installation, Setup & SQL Basics.",
                icon = Icons.Default.Settings,
                items = listOf("Deployment (Docker, Cloud)", "SQL Foundations (DML, DDL)", "Advanced SQL: CTEs, Window Functions"),
                preferenceManager = preferenceManager,
                prefix = "pg_p2"
            )

            // Phase 3
            PostgresRoadmapCard(
                phaseNum = "03",
                title = "Phase 3: Security",
                subtitle = "Configuring & Security hardening.",
                icon = Icons.Default.Security,
                items = listOf("pg_hba.conf Configuration", "Role-Based Access Control (RBAC)", "Row Level Security (RLS)", "SSL/TLS Connections"),
                preferenceManager = preferenceManager,
                prefix = "pg_p3"
            )

            // Phase 4
            PostgresRoadmapCard(
                phaseNum = "04",
                title = "Phase 4: Ecosystem",
                subtitle = "Infrastructure & Application Skills.",
                icon = Icons.Default.Hub,
                items = listOf("High Availability (Patroni, Stolon)", "Backup & Recovery (pg_dump, WAL)", "PostGIS for Geospatial", "Full Text Search"),
                preferenceManager = preferenceManager,
                prefix = "pg_p4"
            )

            // Phase 5
            PostgresRoadmapCard(
                phaseNum = "05",
                title = "Phase 5: Internals",
                subtitle = "Advanced Internals & Optimization.",
                icon = Icons.Default.Memory,
                items = listOf("MVCC & Locking", "Storage Engine & Buffer Cache", "Query Planner & Optimizer", "Indexing: B-Tree, GIN, GiST"),
                preferenceManager = preferenceManager,
                prefix = "pg_p5"
            )

            // Phase 6
            PostgresRoadmapCard(
                phaseNum = "06",
                title = "Phase 6: Performance & Ops",
                subtitle = "Tuning and monitoring.",
                icon = Icons.Default.Speed,
                items = listOf("Vacuum & Autovacuum Tuning", "Shared Buffers & Work Mem", "pg_stat_statements Analysis", "EXPLAIN ANALYZE Deep-dive"),
                preferenceManager = preferenceManager,
                prefix = "pg_p6"
            )

            // Phase 7
            PostgresRoadmapCard(
                phaseNum = "07",
                title = "Phase 7: Development",
                subtitle = "Troubleshooting & Tooling.",
                icon = Icons.Default.Code,
                items = listOf("PL/pgSQL Procedures", "Custom Extensions", "Automation (Flyway, Liquibase)", "Change Data Capture (CDC)"),
                preferenceManager = preferenceManager,
                prefix = "pg_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PostgresRoadmapCard(
    phaseNum: String,
    title: String,
    subtitle: String,
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
                    color = PostgresAccent,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = PostgresPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Text(
                    text = phaseNum,
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
                color = Color.Black
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { item ->
                PostgresCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun PostgresCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
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
            colors = CheckboxDefaults.colors(checkedColor = PostgresPrimary)
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
