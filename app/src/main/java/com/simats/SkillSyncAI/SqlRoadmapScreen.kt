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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val SqlBgStart = Color(0xFFF1F5F9)
val SqlBgEnd = Color(0xFFFFFFFF)
val SqlPrimary = Color(0xFF3B82F6) // SQL Blue
val SqlDark = Color(0xFF1E293B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SqlRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Storage, null, tint = SqlPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SQL Stratum", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = SqlDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = SqlDark)
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
                .background(Brush.verticalGradient(listOf(SqlBgStart, SqlBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Master the\nLanguage\nof Data",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = SqlDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A structured, 6-stage curriculum designed for the stratified professional. Track your progress across the SQL landscape from foundational core concepts to complex analytical logic.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Learn the Basics
            SqlRoadmapCard(
                number = "1",
                title = "Learn the Basics",
                icon = Icons.Default.MenuBook,
                sections = listOf(
                    "CORE CONCEPTS" to listOf("RDBMS & NoSQL Fundamentals", "SQL Syntax & Keywords"),
                    "BASIC MANIPULATION" to listOf("SELECT, INSERT, UPDATE, DELETE", "Primitive Data Types")
                ),
                preferenceManager = preferenceManager,
                prefix = "sql_p1"
            )

            // 2. Definition & Manipulation
            SqlRoadmapCard(
                number = "2",
                title = "Definition & Manipulation",
                icon = Icons.Default.Edit,
                sections = listOf(
                    "DDL & DML" to listOf("CREATE, ALTER, DROP, TRUNCATE", "FROM, WHERE, JOINs", "GROUP BY & HAVING"),
                    "CONSTRAINTS" to listOf("Primary & Foreign keys", "UNIQUE, NOT NULL, CHECK")
                ),
                preferenceManager = preferenceManager,
                prefix = "sql_p2"
            )

            // 3. Queries & Functions
            SqlRoadmapCard(
                number = "3",
                title = "Queries & Functions",
                icon = Icons.Default.Functions,
                sections = listOf(
                    "ADVANCED JOINS" to listOf("Inner, Left, Right, Full", "Self & Cross Joins"),
                    "AGGREGATES & SCALAR" to listOf("SUM, COUNT, AVG, MIN/MAX", "String, Numeric, Date Functions"),
                    "SUBQUERIES" to listOf("Scalar & Table Subqueries", "Correlated & Nested")
                ),
                preferenceManager = preferenceManager,
                prefix = "sql_p3"
            )

            // 4. Objects & Security
            SqlRoadmapCard(
                number = "4",
                title = "Objects & Security",
                icon = Icons.Default.Security,
                sections = listOf(
                    "ARCHITECTURE" to listOf("Views & Indexes", "Stored Procedures"),
                    "ACCESS CONTROL" to listOf("RBAC & Data Integrity", "Security Best Practices")
                ),
                preferenceManager = preferenceManager,
                prefix = "sql_p4"
            )

            // 5. Optimization
            SqlRoadmapCard(
                number = "5",
                title = "Optimization",
                icon = Icons.Default.Speed,
                sections = listOf(
                    "TRANSACTIONS" to listOf("ACID Properties", "COMMIT & ROLLBACK"),
                    "PERFORMANCE" to listOf("Query Execution Plans", "Selective Projection")
                ),
                preferenceManager = preferenceManager,
                prefix = "sql_p5"
            )

            // 6. Advanced Analytical SQL
            SqlRoadmapCard(
                number = "6",
                title = "Advanced Analytical SQL",
                icon = Icons.Default.Analytics,
                sections = listOf(
                    "WINDOW FUNCTIONS" to listOf("RANK, DENSE_RANK, ROW_NUMBER", "LEAD & LAG Functions", "Cumulative Aggregates"),
                    "COMPLEX LOGIC" to listOf("Common Table Expressions (CTEs)", "Recursive Queries & PIVOT", "Dynamic SQL Generation")
                ),
                preferenceManager = preferenceManager,
                prefix = "sql_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SqlRoadmapCard(
    number: String,
    title: String,
    icon: ImageVector,
    sections: List<Pair<String, List<String>>>,
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
                    color = SqlPrimary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = SqlPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$number. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = SqlDark
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            sections.forEach { (sectionTitle, items) ->
                Text(
                    text = sectionTitle,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = SqlPrimary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                items.forEach { item ->
                    SqlCheckboxItem(item, sectionTitle, prefix, preferenceManager)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun SqlCheckboxItem(item: String, section: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${section}_$item"
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
            colors = CheckboxDefaults.colors(checkedColor = SqlPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else SqlDark,
            lineHeight = 20.sp
        )
    }
}
