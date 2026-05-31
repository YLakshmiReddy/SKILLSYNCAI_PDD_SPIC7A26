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

val NoSqlBgStart = Color(0xFFF8FAFF)
val NoSqlBgEnd = Color(0xFFFFFFFF)
val NoSqlPrimary = Color(0xFF4F46E5) // Indigo
val NoSqlDark = Color(0xFF1E1B4B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoSqlRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Storage, null, tint = NoSqlPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("NoSQL Database", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NoSqlDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = NoSqlDark)
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
                .background(Brush.verticalGradient(listOf(NoSqlBgStart, NoSqlBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "NoSQL Systems\nMastery Roadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = NoSqlDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master non-relational database architectures. From document and key-value stores to wide-column and graph databases.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1: Fundamentals
            NoSqlRoadmapCard(
                phase = "01",
                title = "NoSQL Fundamentals",
                items = listOf(
                    "The CAP Theorem",
                    "ACID vs BASE consistency",
                    "Sharding & Horizontal Scaling",
                    "Replication Strategies",
                    "Eventual Consistency"
                ),
                preferenceManager = preferenceManager,
                prefix = "nosql_p1"
            )

            // Phase 2: Document Stores
            NoSqlRoadmapCard(
                phase = "02",
                title = "Document Databases",
                items = listOf(
                    "MongoDB & BSON",
                    "Couchbase",
                    "JSON Schema Design",
                    "Indexing in Document Stores",
                    "Aggregation Pipelines"
                ),
                preferenceManager = preferenceManager,
                prefix = "nosql_p2"
            )

            // Phase 3: Key-Value Stores
            NoSqlRoadmapCard(
                phase = "03",
                title = "Key-Value Databases",
                items = listOf(
                    "Redis & Data Structures",
                    "Amazon DynamoDB",
                    "Caching Patterns",
                    "TTL & Eviction Policies",
                    "Pub/Sub Mechanisms"
                ),
                preferenceManager = preferenceManager,
                prefix = "nosql_p3"
            )

            // Phase 4: Wide-Column Stores
            NoSqlRoadmapCard(
                phase = "04",
                title = "Wide-Column Databases",
                items = listOf(
                    "Apache Cassandra",
                    "HBase & Bigtable",
                    "Column Families",
                    "LSM Trees Architecture",
                    "Write-heavy Optimization"
                ),
                preferenceManager = preferenceManager,
                prefix = "nosql_p4"
            )

            // Phase 5: Graph Databases
            NoSqlRoadmapCard(
                phase = "05",
                title = "Graph Databases",
                items = listOf(
                    "Neo4j & Cypher",
                    "Nodes, Relationships & Props",
                    "Graph Traversal Algorithms",
                    "Amazon Neptune",
                    "Use cases for Graphs"
                ),
                preferenceManager = preferenceManager,
                prefix = "nosql_p5"
            )

            // Phase 6: Search Engines
            NoSqlRoadmapCard(
                phase = "06",
                title = "Search & Analytics",
                items = listOf(
                    "Elasticsearch & Lucene",
                    "Full-text Search indexing",
                    "Meilisearch",
                    "Inverted Indices",
                    "Ranking & Relevance"
                ),
                preferenceManager = preferenceManager,
                prefix = "nosql_p6"
            )

            // Phase 7: Professional Ops
            NoSqlRoadmapCard(
                phase = "07",
                title = "Ops & Security",
                items = listOf(
                    "Backup & Disaster Recovery",
                    "Monitoring & Observability",
                    "Data Privacy & Masking",
                    "Infrastructure as Code for DBs",
                    "Cloud Migration Patterns"
                ),
                preferenceManager = preferenceManager,
                prefix = "nosql_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun NoSqlRoadmapCard(
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
                Surface(
                    color = NoSqlPrimary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = phase,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = NoSqlPrimary
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = NoSqlDark
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                NoSqlCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun NoSqlCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = NoSqlPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else NoSqlDark,
            lineHeight = 20.sp
        )
    }
}
