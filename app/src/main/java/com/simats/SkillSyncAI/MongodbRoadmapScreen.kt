package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Storage
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

val MongoBgStart = Color(0xFFF0FDF4)
val MongoBgEnd = Color(0xFFFFFFFF)
val MongoPrimary = Color(0xFF00ED64) // MongoDB Green
val MongoSecondary = Color(0xFF00684A)
val MongoDark = Color(0xFF001E2B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MongodbRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Storage, null, tint = MongoPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MongoDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MongoDark)
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
                .background(Brush.verticalGradient(listOf(MongoBgStart, MongoBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Surface(
                color = MongoSecondary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "PROFESSIONAL PATH",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MongoSecondary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "MongoDB\nDeveloper\nMastery Roadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MongoDark,
                    lineHeight = 38.sp
                )
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            // 1. MongoDB Basics
            MongoRoadmapCard(
                number = "1",
                title = "MongoDB Basics",
                items = listOf("SQL vs NoSQL", "What is MongoDB?", "When to use MongoDB?", "MongoDB Atlas", "Terminology & Core Concepts"),
                preferenceManager = preferenceManager,
                prefix = "mg_b"
            )

            // 2. Data Model & Types
            MongoRoadmapCard(
                number = "2",
                title = "Data Model & Types",
                items = listOf("BSON vs JSON Specification", "Embedded Objects & Arrays", "Primitives", "Complex Types", "DBRef & Manual Refs"),
                preferenceManager = preferenceManager,
                prefix = "mg_dm"
            )

            // 3. Collections & Methods
            MongoRoadmapCard(
                number = "3",
                title = "Collections & Methods",
                items = listOf("CRUD Operations", "countDocuments()", "createIndex()", "drop()"),
                preferenceManager = preferenceManager,
                prefix = "mg_cm"
            )

            // 4. Query & Projection
            MongoRoadmapCard(
                number = "4",
                title = "Query & Projection",
                items = listOf("Comparison Operators", "Logical (And, Or)", "Element (\$exists)", "Array (Size, All)", "Projection Logic", "Regex Pagination"),
                preferenceManager = preferenceManager,
                prefix = "mg_qp"
            )

            // 5. Advanced & Aggregation
            MongoRoadmapCard(
                number = "5",
                title = "Advanced & Aggregation",
                items = listOf("Aggregation Framework", "Text/Search Indexes", "ACID Transactions"),
                preferenceManager = preferenceManager,
                prefix = "mg_aa"
            )

            // 6. Performance & Scaling
            MongoRoadmapCard(
                number = "6",
                title = "Performance & Scaling",
                items = listOf("Indexing Strategy (Single & Compound)", "Atlas Search Indexes", "EXPLAIN for Performance", "Replication & High Availability", "Sharded Clusters"),
                preferenceManager = preferenceManager,
                prefix = "mg_ps"
            )

            // 7. Security & Dev Tools
            MongoRoadmapCard(
                number = "7",
                title = "Security & Dev Tools",
                items = listOf("Auth: RBAC Controls", "Privacy: Encryption at Rest", "Ops: Backup & Recovery", "Data: Kafka Connectors", "Audit: Activity Logs", "Dev: Language Drivers"),
                preferenceManager = preferenceManager,
                prefix = "mg_sd"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun MongoRoadmapCard(
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
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = MongoPrimary)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "$number. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MongoDark
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))

            items.forEach { item ->
                MongoCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun MongoCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
            },
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(checkedColor = MongoPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else MongoDark,
            lineHeight = 20.sp
        )
    }
}
