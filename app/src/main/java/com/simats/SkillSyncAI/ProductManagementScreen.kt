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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val PMBgStart = Color(0xFFF1F9FF)
val PMBgEnd = Color(0xFFE3F2FD)
val PMPrimary = Color(0xFF0277BD)
val PMAccent = Color(0xFFE1F5FE)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductManagementScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AdsClick, null, tint = PMPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("PM Catalyst", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PMPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PMPrimary)
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
                .background(Brush.verticalGradient(listOf(Color.White, PMBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Mastering Product\nManagement",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A),
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Your guided trajectory from foundational concepts to strategic leadership in modern product ecosystems.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Module 01
            PMRoadmapCard(
                moduleNum = "01",
                title = "Foundational Concepts",
                items = listOf("Understanding the Role", "Product Development Lifecycle"),
                preferenceManager = preferenceManager,
                prefix = "pm_m1"
            )

            // Module 02
            PMRoadmapCard(
                moduleNum = "02",
                title = "Idea Generation",
                items = listOf("Brainstorming Techniques", "Problem Identification", "Selection Process"),
                preferenceManager = preferenceManager,
                prefix = "pm_m2"
            )

            // Module 03
            PMRoadmapCard(
                moduleNum = "03",
                title = "Market & User Research",
                items = listOf("Market Analysis", "User Research Methodologies"),
                preferenceManager = preferenceManager,
                prefix = "pm_m3"
            )

            // Module 04
            PMRoadmapCard(
                moduleNum = "04",
                title = "Strategy & Positioning",
                items = listOf("Defining 'What' and 'Why'", "Strategic Thinking", "Defining North Star Metric"),
                preferenceManager = preferenceManager,
                prefix = "pm_m4"
            )

            // Module 05
            PMRoadmapCard(
                moduleNum = "05",
                title = "Planning & Requirements",
                items = listOf("Requirements Gathering (PRD)", "Prioritization & Ranking", "Roadmapping Basics"),
                preferenceManager = preferenceManager,
                prefix = "pm_m5"
            )

            // Module 06
            PMRoadmapCard(
                moduleNum = "06",
                title = "Design & Development",
                items = listOf("UI/UX Design Principles", "Agile Methodology & Scrum", "Release Strategies"),
                preferenceManager = preferenceManager,
                prefix = "pm_m6"
            )

            // Module 07
            PMRoadmapCard(
                moduleNum = "07",
                title = "Metrics & Growth",
                items = listOf("Key Product Metrics (KPIs)", "Data-Driven Decision Making", "Go-to-Market (GTM) Strategy"),
                preferenceManager = preferenceManager,
                prefix = "pm_m7"
            )

            // Module 08
            PMRoadmapCard(
                moduleNum = "08",
                title = "Stakeholder Management",
                items = listOf("Managing Stakeholders", "Communication Skills", "Power vs Interest Matrix"),
                preferenceManager = preferenceManager,
                prefix = "pm_m8"
            )

            // Module 09
            PMRoadmapCard(
                moduleNum = "09",
                title = "Risk Management",
                items = listOf("Risk Lifecycle", "Assessment & Mitigation", "Monitoring & Control"),
                preferenceManager = preferenceManager,
                prefix = "pm_m9"
            )

            // Module 10
            PMRoadmapCard(
                moduleNum = "10",
                title = "Advanced Topics",
                items = listOf("Scaling Product Growth", "Advanced Data Analysis", "Leadership and Influence"),
                preferenceManager = preferenceManager,
                prefix = "pm_m10"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PMRoadmapCard(
    moduleNum: String,
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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                    fontSize = 18.sp,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = moduleNum,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFF1F5F9) // Large background number
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))

            items.forEach { item ->
                PMCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun PMCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = PMPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else Color(0xFF334155),
            lineHeight = 20.sp
        )
    }
}
