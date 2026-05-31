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

val DevRelBgStart = Color(0xFFF1F4FF)
val DevRelBgEnd = Color(0xFFFFFFFF)
val DevRelPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevRelScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = DevRelPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DevRelPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = DevRelPrimary)
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
                .background(Brush.verticalGradient(listOf(DevRelBgStart, DevRelBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Developer\nRelations\nMastery Path",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = DevRelPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A stratified guide to becoming a world-class Developer Advocate. From core technical proficiency to strategic community building and high-impact content creation.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            DevRelRoadmapCard(
                phaseNum = "01",
                title = "Introduction & Concepts",
                items = listOf(
                    "What is DevRel?",
                    "Advocacy vs Evangelism",
                    "Education & Community",
                    "DX: Developer Experience",
                    "The Developer Journey",
                    "Ethical Marketing"
                ),
                preferenceManager = preferenceManager,
                prefix = "devrel_p1"
            )

            // Phase 02
            DevRelRoadmapCard(
                phaseNum = "02",
                title = "Technical Skills",
                items = listOf(
                    "JS / Go / Rust / Python",
                    "IDE Mastery (VS Code)",
                    "Git & GitHub (PR/Issue)",
                    "APIs & SDK Internals",
                    "API Design Patterns"
                ),
                preferenceManager = preferenceManager,
                prefix = "devrel_p2"
            )

            // Phase 03
            DevRelRoadmapCard(
                phaseNum = "03",
                title = "Communication",
                items = listOf(
                    "Tech Docs & Blogging",
                    "X / LinkedIn Strategy",
                    "Storytelling (State of IT)",
                    "Audience Hooks & Engagement",
                    "Q&A Handling Techniques"
                ),
                preferenceManager = preferenceManager,
                prefix = "devrel_p3"
            )

            // Phase 04
            DevRelRoadmapCard(
                phaseNum = "04",
                title = "Community Management",
                items = listOf(
                    "Discord & Slack Ops",
                    "Guidelines & CoC",
                    "Professional Networking",
                    "Recognition Programs",
                    "Post-Event Analysis"
                ),
                preferenceManager = preferenceManager,
                prefix = "devrel_p4"
            )

            // Phase 05
            DevRelRoadmapCard(
                phaseNum = "05",
                title = "Content Production",
                items = listOf(
                    "Blogging & Technical SEO",
                    "Video Production (BN)",
                    "Twitch / YouTube Live",
                    "Dev Onboarding Docs"
                ),
                preferenceManager = preferenceManager,
                prefix = "devrel_p5"
            )

            // Phase 06
            DevRelRoadmapCard(
                phaseNum = "06",
                title = "Onboarding & Support",
                items = listOf(
                    "Sample Projects & Repos",
                    "Forums & FAQ Curation",
                    "Office Hours Strategy",
                    "Interactive Webinars"
                ),
                preferenceManager = preferenceManager,
                prefix = "devrel_p6"
            )

            // Phase 07
            DevRelRoadmapCard(
                phaseNum = "07",
                title = "Metrics & Analytics",
                items = listOf(
                    "Community Growth KPI",
                    "Engagement Scoring",
                    "GA / Amplitude / Heap",
                    "Stakeholder Reporting"
                ),
                preferenceManager = preferenceManager,
                prefix = "devrel_p7"
            )

            // Phase 08
            DevRelRoadmapCard(
                phaseNum = "08",
                title = "Career Development",
                items = listOf(
                    "Thought Leadership",
                    "Conferences & Speaking",
                    "Personal Brand Identity",
                    "Continuous AI Learning"
                ),
                preferenceManager = preferenceManager,
                prefix = "devrel_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DevRelRoadmapCard(
    phaseNum: String,
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
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "PHASE $phaseNum",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = DevRelPrimary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                DevRelCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun DevRelCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = DevRelPrimary)
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
