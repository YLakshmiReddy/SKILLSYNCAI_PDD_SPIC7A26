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

val UXBgStart = Color(0xFFF1F4FF)
val UXBgEnd = Color(0xFFFFFFFF)
val UXPrimary = Color(0xFF1A237E)
val UXAccent = Color(0xFFE8EAF6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UXDesignScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.AutoAwesome, null, tint = UXPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = UXPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
                .background(Brush.verticalGradient(listOf(UXBgStart, UXBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "UX Design\nLearning Roadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = UXPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A strategic journey from human psychology to measurable business impact. Track your progress across multiple core modules.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Module 01
            UXRoadmapCard(
                moduleNum = "01",
                title = "Human Decision Making",
                icon = Icons.Default.Psychology,
                items = listOf("Mental Models", "Cognitive Load", "Fitts's Law", "Hick's Law", "Gestalt Principles", "Behavioral Economics"),
                preferenceManager = preferenceManager,
                prefix = "ux_m1"
            )

            // Module 02
            UXRoadmapCard(
                moduleNum = "02",
                title = "User Research & Strategy",
                icon = Icons.Default.Search,
                items = listOf("User Interviews", "Surveys & Questionnaires", "Persona Development", "Empathy Mapping", "Customer Journey Mapping", "Competitive Audit"),
                preferenceManager = preferenceManager,
                prefix = "ux_m2"
            )

            // Module 03
            UXRoadmapCard(
                moduleNum = "03",
                title = "Information Architecture",
                icon = Icons.Default.AccountTree,
                items = listOf("Card Sorting", "Site Maps", "User Flows", "Navigation Design", "Labeling Systems", "Search Systems"),
                preferenceManager = preferenceManager,
                prefix = "ux_m3"
            )

            // Module 04
            UXRoadmapCard(
                moduleNum = "04",
                title = "Interaction Design (IxD)",
                icon = Icons.Default.TouchApp,
                items = listOf("Micro-interactions", "Animations & Transitions", "States (Default, Hover, Error)", "Feedback Loops", "Affordance & Signifiers"),
                preferenceManager = preferenceManager,
                prefix = "ux_m4"
            )

            // Module 05
            UXRoadmapCard(
                moduleNum = "05",
                title = "Conceptual Design & Wireframing",
                icon = Icons.Default.Edit,
                items = listOf("Low-Fidelity Sketching", "Wireframing Tools (Figma/Sketch)", "Responsive Grid Systems", "UI Patterns & Kits"),
                subHeader = "Execution",
                subItems = listOf("High-Fidelity Prototyping", "Design Specifications", "Handoff Documentation"),
                preferenceManager = preferenceManager,
                prefix = "ux_m5"
            )

            // Module 06
            UXRoadmapCard(
                moduleNum = "06",
                title = "Accessibility & Inclusive Design",
                icon = Icons.Default.AccessibilityNew,
                items = listOf("WCAG Guidelines", "Color Contrast Ratios", "Screen Reader Support", "Keyboard Navigation", "Alt Text Best Practices"),
                preferenceManager = preferenceManager,
                prefix = "ux_m6"
            )

            // Module 07
            UXRoadmapCard(
                moduleNum = "07",
                title = "UX Writing & Content Strategy",
                icon = Icons.Default.Article,
                items = listOf("Voice & Tone", "Microcopy", "Error Message Design", "Content Audits", "Style Guides"),
                preferenceManager = preferenceManager,
                prefix = "ux_m7"
            )

            // Module 08
            UXRoadmapCard(
                moduleNum = "08",
                title = "Usability Testing",
                icon = Icons.Default.Analytics,
                description = "Validate your design decisions through rigorous testing and data.",
                items = listOf("Moderated vs Unmoderated", "A/B Testing", "Tree Testing", "System Usability Scale (SUS)", "Eye Tracking Analysis"),
                preferenceManager = preferenceManager,
                prefix = "ux_m8"
            )

            // Module 09
            UXRoadmapCard(
                moduleNum = "09",
                title = "Business of UX",
                icon = Icons.Default.MonetizationOn,
                items = listOf("ROI of Design", "UX KPIs & Metrics", "Design Ops", "Stakeholder Management", "Agile Design Integration"),
                preferenceManager = preferenceManager,
                prefix = "ux_m9"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun UXRoadmapCard(
    moduleNum: String,
    title: String,
    icon: ImageVector,
    items: List<String>,
    description: String? = null,
    subHeader: String? = null,
    subItems: List<String>? = null,
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
                    color = UXAccent,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = UXPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Surface(
                    color = Color(0xFFF1F3F4),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "MODULE $moduleNum",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = UXPrimary
            )

            if (description != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            items.forEach { item ->
                UXCheckboxItem(item, title, prefix, preferenceManager)
            }

            if (subHeader != null && subItems != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = subHeader,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = UXPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                subItems.forEach { item ->
                    UXCheckboxItem(item, title + "_sub", prefix, preferenceManager)
                }
            }
        }
    }
}

@Composable
fun UXCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = UXPrimary)
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
