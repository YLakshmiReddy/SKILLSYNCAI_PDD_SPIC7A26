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

val RedisBgStart = Color(0xFFFFF5F5)
val RedisBgEnd = Color(0xFFFFFFFF)
val RedisPrimary = Color(0xFFD82C20) // Redis Red
val RedisDark = Color(0xFF1E1B4B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedisRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Storage, null, tint = RedisPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Redis Roadmap", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = RedisPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
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
                .background(Brush.verticalGradient(listOf(RedisBgStart, RedisBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Learning Path",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = "Master Redis from core concepts to production-grade architecture.",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            RedisRoadmapCard(
                phase = "01",
                title = "Introduction to Redis",
                items = listOf(
                    "CORE CONCEPTS" to listOf("What is Redis", "Redis vs Others", "Use Cases"),
                    "GETTING STARTED" to listOf("Installation & Running", "Basic Commands")
                ),
                preferenceManager = preferenceManager,
                prefix = "redis_p1"
            )

            // Phase 02
            RedisRoadmapCard(
                phase = "02",
                title = "Core Data Structures",
                items = listOf(
                    "" to listOf("Strings, Lists & Sets", "Hashes & Sorted Sets")
                ),
                preferenceManager = preferenceManager,
                prefix = "redis_p2"
            )

            // Phase 03
            RedisRoadmapCard(
                phase = "03",
                title = "Advanced Data Structures",
                items = listOf(
                    "" to listOf("Bitmaps", "HyperLogLog", "Streams", "Geospatial")
                ),
                preferenceManager = preferenceManager,
                prefix = "redis_p3"
            )

            // Phase 04
            RedisRoadmapCard(
                phase = "04",
                title = "Real-time Features",
                items = listOf(
                    "MESSAGING PATTERNS" to listOf("Pub / Sub"),
                    "ATOMIC OPERATIONS" to listOf("Pipelining & Transactions", "Lua Scripting")
                ),
                preferenceManager = preferenceManager,
                prefix = "redis_p4"
            )

            // Phase 05
            RedisRoadmapCard(
                phase = "05",
                title = "Persistence & HA",
                items = listOf(
                    "" to listOf("Persistence: RDB, AOF, Hybrid", "Scaling: Sentinel, Clustering", "Replication", "Clustering")
                ),
                preferenceManager = preferenceManager,
                prefix = "redis_p5"
            )

            // Phase 06
            RedisRoadmapCard(
                phase = "06",
                title = "Management & Security",
                items = listOf(
                    "SECURITY" to listOf("Auth & SSL", "Network"),
                    "OPTIMIZATION" to listOf("Eviction Policy", "Monitoring")
                ),
                preferenceManager = preferenceManager,
                prefix = "redis_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun RedisRoadmapCard(
    phase: String,
    title: String,
    items: List<Pair<String, List<String>>>,
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
            Text(
                text = "Phase $phase: $title",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { (subTitle, subItems) ->
                if (subTitle.isNotEmpty()) {
                    Text(
                        text = subTitle,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                subItems.forEach { item ->
                    RedisCheckboxItem(item, subTitle.ifEmpty { title }, prefix, preferenceManager)
                }
            }
        }
    }
}

@Composable
fun RedisCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = RedisPrimary)
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
