package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
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

val BIBgStart = Color(0xFFF1F4FF)
val BIBgEnd = Color(0xFFFFFFFF)
val BIPrimary = Color(0xFF1A237E)
val BIAccent = Color(0xFFE8EAF6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BIAnalystScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = BIPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = BIPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = BIPrimary)
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
                .background(Brush.verticalGradient(listOf(BIBgStart, BIBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Surface(
                color = BIPrimary,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Text(
                    text = "MASTERY PATH • 8 Phases",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Text(
                text = "BI Analyst Roadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = BIPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A comprehensive curriculum designed to take you from foundational business concepts to advanced data architectures and professional AI-driven analytics.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Introduction & Business Fundamentals
            BIRoadmapCard(
                phase = "1",
                title = "Introduction & Business Fundamentals",
                week = "WEEK 1-2",
                items = listOf("Understanding the Role", "Key Business Functions", "Types of BI Operations"),
                preferenceManager = preferenceManager,
                prefix = "bi_p1"
            )

            // 2. Statistics Basics
            BIRoadmapCard(
                phase = "2",
                title = "Statistics Basics",
                week = "WEEK 3",
                items = listOf("Descriptive Statistics", "Inferential Statistics"),
                preferenceManager = preferenceManager,
                prefix = "bi_p2"
            )

            // 3. BI Core Skills: Data Handling
            BIRoadmapCard(
                phase = "3",
                title = "BI Core Skills: Data Handling",
                week = "WEEK 4-6",
                items = listOf("Fundamentals of Data", "Data Sources & Formats", "SQL Fundamentals", "Data Cleaning & EDA"),
                preferenceManager = preferenceManager,
                prefix = "bi_p3"
            )

            // 4. Visualizing Data
            BIRoadmapCard(
                phase = "4",
                title = "Visualizing Data",
                week = "WEEK 7-8",
                items = listOf("Visualization Fundamentals", "Visualization Best Practices", "BI Tools & Platforms"),
                preferenceManager = preferenceManager,
                prefix = "bi_p4"
            )

            // 5. Cloud Computing & Applications
            BIRoadmapCard(
                phase = "5",
                title = "Cloud Computing & Applications",
                week = "WEEK 9-10",
                items = listOf("Cloud Infrastructure", "Programming Languages (Python/R)", "Domain Applications"),
                preferenceManager = preferenceManager,
                prefix = "bi_p5"
            )

            // 6. Advanced BI Techniques
            BIRoadmapCard(
                phase = "6",
                title = "Advanced BI Techniques & Professional Excellence",
                week = "WEEK 11-12",
                items = listOf("Advanced Analysis", "Basic Machine Learning", "Professional 'Soft' Skills"),
                preferenceManager = preferenceManager,
                prefix = "bi_p6"
            )

            // 7. Data Governance & Ethics
            BIRoadmapCard(
                phase = "7",
                title = "Data Governance & Ethics",
                week = "WEEK 13",
                items = listOf("Governance & Ethics", "Data Architectures", "ETL Processes"),
                preferenceManager = preferenceManager,
                prefix = "bi_p7"
            )

            // 8. Career & Development
            BIRoadmapCard(
                phase = "8",
                title = "Career & Development",
                week = "WEEK 14+",
                items = listOf("Building Your Portfolio", "Networking & Job Prep"),
                preferenceManager = preferenceManager,
                prefix = "bi_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun BIRoadmapCard(
    phase: String,
    title: String,
    week: String,
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
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "$phase. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = BIPrimary,
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = week,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = BIPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                BICheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun BICheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = BIPrimary)
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
