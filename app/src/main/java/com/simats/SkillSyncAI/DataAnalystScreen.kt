package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star

val DABgStart = Color(0xFFF1F4FF)
val DABgEnd = Color(0xFFFFFFFF)
val DAPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataAnalystScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.BarChart, null, tint = DAPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DAPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = DAPrimary)
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
                .background(Brush.verticalGradient(listOf(DABgStart, DABgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "CAREER PATH",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DAPrimary,
                letterSpacing = 1.2.sp
            )
            Text(
                text = "Data Analyst\nRoadmap 2024",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = DAPrimary,
                    lineHeight = 40.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the art of storytelling through data. From Excel foundations to advanced Machine Learning architectures.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Info Badges
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                InfoBadge(Icons.Default.Bolt, "EST. TIME", "6 Months", modifier = Modifier.weight(1f))
                InfoBadge(Icons.Default.Star, "LEVEL", "Beginner+", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sections
            TimelineSection(
                title = "Building a Strong Foundation",
                isFirst = true,
                content = {
                    DARoadmapCard(
                        title = "Introduction to Analytics",
                        items = listOf("What is Data Analytics?", "Types (Descriptive, Diagnostic, Predictive, Prescriptive)"),
                        preferenceManager = preferenceManager,
                        prefix = "da_p1_intro"
                    )
                    DARoadmapCard(
                        title = "Analysis & Reporting (Excel)",
                        items = listOf("Functions: VLOOKUP, IF, SUM, CONCAT, TRIM", "Features: Pivot Tables, Dynamic Charting"),
                        preferenceManager = preferenceManager,
                        prefix = "da_p1_excel"
                    )
                }
            )

            TimelineSection(
                title = "Programming & Handling",
                content = {
                    DARoadmapCard(
                        title = "Programming (Python or R)",
                        items = listOf("Syntax, Manipulation (Pandas/Dplyr)", "Visualisation (Seaborn/Ggplot2)"),
                        preferenceManager = preferenceManager,
                        prefix = "da_p2_prog"
                    )
                    DARoadmapCard(
                        title = "Collection & Database",
                        items = listOf("SQL for querying, APIs, Web Scraping", "Databases"),
                        preferenceManager = preferenceManager,
                        prefix = "da_p2_db"
                    )
                }
            )

            TimelineSection(
                title = "Data Analysis Techniques",
                content = {
                    DARoadmapCard(
                        title = "Statistical Analysis",
                        items = listOf("Hypothesis Testing, Correlation, Regression"),
                        preferenceManager = preferenceManager,
                        prefix = "da_p3_stat"
                    )
                    DARoadmapCard(
                        title = "Data Visualisation (BI)",
                        items = listOf("Tableau", "Power BI"),
                        preferenceManager = preferenceManager,
                        prefix = "da_p3_bi"
                    )
                }
            )

            TimelineSection(
                title = "Advanced Topics",
                isLast = true,
                content = {
                    DARoadmapCard(
                        title = "Machine Learning (ML)",
                        items = listOf("Supervised, Unsupervised, KNN, K-Means", "Logistic Regression"),
                        preferenceManager = preferenceManager,
                        prefix = "da_p4_ml"
                    )
                    DARoadmapCard(
                        title = "Deep Learning (Optional)",
                        items = listOf("Neural Networks (CNNs, RNNs)", "Frameworks (Pytorch, TF)"),
                        preferenceManager = preferenceManager,
                        prefix = "da_p4_dl"
                    )
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun InfoBadge(icon: ImageVector, label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 2.dp
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = DAPrimary, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(label, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DAPrimary)
            }
        }
    }
}

@Composable
fun TimelineSection(title: String, isFirst: Boolean = false, isLast: Boolean = false, content: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(DAPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(14.dp))
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f, fill = false)
                        .height(200.dp) // Approximation
                        .background(DAPrimary.copy(alpha = 0.2f))
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = DAPrimary)
            Spacer(modifier = Modifier.height(12.dp))
            content()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun DARoadmapCard(title: String, items: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = DAPrimary, modifier = Modifier.weight(1f))
                
                // Track state for the whole box if needed, or just items. 
                // In image, there's a checkbox at the top right of card.
                val boxKey = "${prefix}_card_done"
                var boxChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(boxKey, false)) }
                Checkbox(
                    checked = boxChecked,
                    onCheckedChange = { 
                        boxChecked = it
                        preferenceManager.getSharedPrefs().edit().putBoolean(boxKey, it).apply()
                    },
                    modifier = Modifier.size(20.dp),
                    colors = CheckboxDefaults.colors(checkedColor = DAPrimary)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            items.forEach { item ->
                Text(
                    text = "• $item",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}
