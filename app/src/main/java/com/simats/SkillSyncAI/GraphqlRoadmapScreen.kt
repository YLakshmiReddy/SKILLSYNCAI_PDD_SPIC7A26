package com.simats.SkillSyncAI

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val GraphqlBgStart = Color(0xFFFBF7FF)
val GraphqlBgEnd = Color(0xFFFFFFFF)
val GraphqlPrimary = Color(0xFFE10098) // GraphQL Pink
val GraphqlDark = Color(0xFF171E26)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraphqlRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Hub, null, tint = GraphqlPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = GraphqlDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = GraphqlDark)
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
                .background(Brush.verticalGradient(listOf(GraphqlBgStart, GraphqlBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Surface(
                color = GraphqlPrimary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "IN PROGRESS",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = GraphqlPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "GraphQL Developer\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = GraphqlDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the world's most powerful query language for APIs. From core concepts to advanced real-time implementations and server-side logic.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Introduction to GraphQL
            GraphqlRoadmapCard(
                title = "Introduction to GraphQL",
                items = listOf("Core Concepts", "Platform Overview"),
                preferenceManager = preferenceManager,
                prefix = "gql_intro"
            )

            // 2. GraphQL Queries & Mutations
            GraphqlRoadmapCard(
                title = "GraphQL Queries & Mutations",
                items = listOf("Queries", "Mutations"),
                preferenceManager = preferenceManager,
                prefix = "gql_ops"
            )

            // 3. Subscriptions & Real-time Data
            GraphqlRoadmapCard(
                title = "Subscriptions & Real-time Data",
                items = listOf("Subscriptions", "Advanced Directives"),
                preferenceManager = preferenceManager,
                prefix = "gql_realtime"
            )

            // 4. Schema & Type System
            GraphqlRoadmapCard(
                title = "Schema & Type System",
                items = listOf("Defining the Data Model", "Logic & Execution"),
                preferenceManager = preferenceManager,
                prefix = "gql_schema"
            )

            // 5. Serving GraphQL over the Internet
            GraphqlRoadmapCard(
                title = "Serving GraphQL over the Internet",
                items = listOf("Transport Protocols", "Real-time Transport", "Pagination"),
                preferenceManager = preferenceManager,
                prefix = "gql_serving"
            )

            // 6. Implementations & Servers
            GraphqlRoadmapCard(
                title = "Implementations & Servers",
                items = listOf("Language-Specific Implementations", "Client-Side Tools"),
                preferenceManager = preferenceManager,
                prefix = "gql_impl"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun GraphqlRoadmapCard(
    title: String,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFF).copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = GraphqlDark,
                    modifier = Modifier.weight(1f)
                )
                
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFFE5E7EB), RoundedCornerShape(4.dp))
                ) {
                    Checkbox(
                        checked = cardChecked,
                        onCheckedChange = { isChecked ->
                            cardChecked = isChecked
                            preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                        },
                        colors = CheckboxDefaults.colors(checkedColor = GraphqlPrimary)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                GraphqlSubItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun GraphqlSubItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(12.dp).padding(2.dp)) {
                // L-shape indicator
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = 2f
                    )
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 2f
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = item,
                fontSize = 14.sp,
                color = if (checked) Color.Gray else GraphqlDark,
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    checked = isChecked
                    preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
                },
                modifier = Modifier.size(20.dp),
                colors = CheckboxDefaults.colors(checkedColor = GraphqlPrimary)
            )
        }
    }
}
