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

val DSBRgStart = Color(0xFFF8F9FE)
val DSBRgEnd = Color(0xFFFFFFFF)
val DSBPrimary = Color(0xFF2E3A8C) // Deep Blue
val DSBDark = Color(0xFF1A1F36)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignSystemRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Architecture, null, tint = DSBPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DSBDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = DSBDark)
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
                .background(Brush.verticalGradient(listOf(DSBRgStart, DSBRgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Surface(
                color = DSBPrimary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "PHASE TRACKER",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = DSBPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Design System\nArchitecture\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = DSBPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "The stratified guide to building, scaling, and maintaining professional digital languages within the SkillSync AI ecosystem.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            DSBRoadmapCard(
                number = "1",
                title = "Understand the Basics",
                icon = Icons.Default.Lightbulb,
                items = listOf("Core Concepts", "Methodology", "Value Proposition", "Stakeholder Alignment"),
                preferenceManager = preferenceManager,
                prefix = "ds_p1"
            )

            // Phase 2
            DSBRoadmapCard(
                number = "2",
                title = "Terminology & Structure",
                icon = Icons.Default.AccountTree,
                items = listOf("The Building Blocks", "Rules & Assets", "Atomic Design Principles", "Naming Conventions"),
                preferenceManager = preferenceManager,
                prefix = "ds_p2"
            )

            // Phase 3
            DSBRoadmapCard(
                number = "3",
                title = "Analysis & Making",
                icon = Icons.Default.BarChart,
                items = listOf("Existing Design Analysis", "Creation Strategy", "Inventory Audit", "Interface Inventory"),
                preferenceManager = preferenceManager,
                prefix = "ds_p3"
            )

            // Phase 4
            DSBRoadmapCard(
                number = "4",
                title = "Design Language",
                icon = Icons.Default.Language,
                items = listOf("Brand Foundation", "Defining Design Tokens", "Color Theory & Palettes", "Typography Scales", "Spacing Systems"),
                preferenceManager = preferenceManager,
                prefix = "ds_p4"
            )

            // Phase 5
            DSBRoadmapCard(
                number = "5",
                title = "Components & Tooling",
                icon = Icons.Default.Inventory,
                items = listOf("Component Catalog", "Tooling & Dev", "Figma/Sketch Library", "Storybook Integration", "Version Control"),
                preferenceManager = preferenceManager,
                prefix = "ds_p5"
            )

            // Phase 6
            DSBRoadmapCard(
                number = "6",
                title = "Management & Comms",
                icon = Icons.Default.GroupWork,
                items = listOf("Project Management", "Analytics & Growth", "Contribution Model", "Release Notes", "Community Support"),
                preferenceManager = preferenceManager,
                prefix = "ds_p6"
            )

            // Phase 7
            DSBRoadmapCard(
                number = "7",
                title = "Documentation & Styleguides",
                icon = Icons.Default.Description,
                items = listOf("Usage Guidelines", "Code Implementation", "Design Patterns", "Tone of Voice", "Live Examples"),
                preferenceManager = preferenceManager,
                prefix = "ds_p7"
            )

            // Phase 8
            DSBRoadmapCard(
                number = "8",
                title = "Governance & Maintenance",
                icon = Icons.Default.Settings,
                items = listOf("Ownership Models", "Audit Workflows", "Retirement Policy", "KPIs & Success Metrics", "Evolution Strategy"),
                preferenceManager = preferenceManager,
                prefix = "ds_p8"
            )

            // Phase 9
            DSBRoadmapCard(
                number = "9",
                title = "Accessibility & Inclusion",
                icon = Icons.Default.Accessibility,
                items = listOf("WCAG Compliance", "Inclusive Components", "Screen Reader Testing", "Color Contrast Check", "Keyboard Navigation"),
                preferenceManager = preferenceManager,
                prefix = "ds_p9"
            )

            // Phase 10
            DSBRoadmapCard(
                number = "10",
                title = "Multi-platform Support",
                icon = Icons.Default.Devices,
                items = listOf("Web, iOS, & Android", "Responsive Patterns", "Token Transformation", "Native Modules", "Platform Consistency"),
                preferenceManager = preferenceManager,
                prefix = "ds_p10"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DSBRoadmapCard(
    number: String,
    title: String,
    icon: ImageVector,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Surface(
                    color = Color(0xFFF3F4F9),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = DSBPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = DSBPrimary)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "$number. $title",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = DSBDark
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                DSBCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun DSBCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Dot indicator
        Surface(
            modifier = Modifier.size(6.dp),
            shape = RoundedCornerShape(3.dp),
            color = if (checked) DSBPrimary else Color.LightGray
        ) {}
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else DSBDark,
            modifier = Modifier.weight(1f)
        )

        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
            },
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(checkedColor = DSBPrimary)
        )
    }
}
