package com.simats.SkillSyncAI

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
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

val VueBgStart = Color(0xFFF9FAFF)
val VueBgEnd = Color(0xFFFFFFFF)
val VuePrimary = Color(0xFF42B883) // Vue Green
val VueSecondary = Color(0xFF35495E) // Vue Dark Blue
val VueDark = Color(0xFF2C3E50)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VueRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.GridView, null, tint = VuePrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = VueDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = VueDark)
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
                .background(Brush.verticalGradient(listOf(VueBgStart, VueBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Surface(
                color = VuePrimary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "VUE.JS LEARNING PATH",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = VuePrimary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Vue.js\nDeveloper\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = VueSecondary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "This roadmap tracks the journey from core JavaScript proficiency to mastering the Vue.js ecosystem. Follow the strata of development to become a senior engineer.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Prerequisites
            VueRoadmapCard(
                phase = "01",
                title = "Prerequisites",
                items = listOf(
                    "JavaScript Basics (high proficiency required)",
                    "Git (optional)"
                ),
                preferenceManager = preferenceManager,
                prefix = "vue_p1"
            )

            // 2. Core Basics & Styling
            VueRoadmapCard(
                phase = "02",
                title = "Core Basics & Styling",
                items = listOf(
                    "App Configurations",
                    "Options API",
                    "Composition API"
                ),
                subsections = listOf(
                    "STYLING FRAMEWORKS" to listOf("Tailwind CSS", "Vuetify", "Element UI")
                ),
                preferenceManager = preferenceManager,
                prefix = "vue_p2"
            )

            // 3. Templates & Rendering
            VueRoadmapCard(
                phase = "03",
                title = "Templates & Rendering",
                sections = listOf(
                    "DIRECTIVES" to listOf("Interpolation (v-bind, v-model)", "Conditionals (v-if, v-else)", "Data loops (v-for)", "Event Handling (v-on)"),
                    "LOGIC" to listOf("Conditional Rendering", "Data Using Records", "Computed Properties")
                ),
                preferenceManager = preferenceManager,
                prefix = "vue_p3"
            )

            // 4. Life Cycle & Architecture
            VueRoadmapCard(
                phase = "04",
                title = "Life Cycle & Architecture",
                items = listOf(
                    "Complex navigation vs SEO",
                    "Props, Events & Two-way Binding",
                    "Lifecycle Hooks (onMounted)"
                ),
                preferenceManager = preferenceManager,
                prefix = "vue_p4"
            )

            // 5. State & Navigation
            VueRoadmapCard(
                phase = "05",
                title = "State & Navigation",
                description = "Managing data flow and routing is the heart of any Single Page Application (SPA). Master the official ecosystem.",
                items = listOf(
                    "Pinia (state management repository)",
                    "Vue Router (routing and sub-navigation)"
                ),
                preferenceManager = preferenceManager,
                prefix = "vue_p5"
            )

            // 6. Forms, APIs & Testing
            VueRoadmapCard(
                phase = "06",
                title = "Forms, APIs & Testing",
                sections = listOf(
                    "Vee-Validate" to listOf("Forms and validation scenarios"),
                    "API" to listOf("Using Axios to sync with APIs/Backends")
                ),
                items = listOf("Vitest, Cypress, Playwright"),
                preferenceManager = preferenceManager,
                prefix = "vue_p6"
            )

            // 7. Advanced & Ecosystem
            VueRoadmapCard(
                phase = "07",
                title = "Advanced & Ecosystem",
                items = listOf(
                    "Nuxt.js (SSR)",
                    "PWA & Native",
                    "Transitions"
                ),
                subNotes = listOf("Next, Git, Webpack, Vitest"),
                preferenceManager = preferenceManager,
                prefix = "vue_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun VueRoadmapCard(
    phase: String,
    title: String,
    description: String? = null,
    items: List<String> = emptyList(),
    sections: List<Pair<String, List<String>>> = emptyList(),
    subsections: List<Pair<String, List<String>>> = emptyList(),
    subNotes: List<String> = emptyList(),
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
                Box(modifier = Modifier.size(24.dp)) {
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
                Column {
                    Text(
                        text = "PHASE $phase",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = VueSecondary
                    )
                }
            }
            
            if (description != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                VueItemRow(item, title, prefix, preferenceManager)
            }

            sections.forEach { (sectionTitle, sectionItems) ->
                Text(
                    text = sectionTitle,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = VuePrimary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                sectionItems.forEach { item ->
                    VueItemRow(item, sectionTitle, prefix, preferenceManager)
                }
            }

            subsections.forEach { (sectionTitle, sectionItems) ->
                Text(
                    text = sectionTitle,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    sectionItems.forEach { item ->
                        VueChip(item, prefix, preferenceManager)
                    }
                }
            }

            if (subNotes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    subNotes.forEach { note ->
                        Text(text = note, fontSize = 10.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun VueItemRow(item: String, section: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${section}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(12.dp).padding(end = 4.dp)) {
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
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else VueDark,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
            },
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(checkedColor = VuePrimary)
        )
    }
}

@Composable
fun VueChip(label: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_chip_$label"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    FilterChip(
        selected = checked,
        onClick = {
            checked = !checked
            preferenceManager.getSharedPrefs().edit().putBoolean(key, checked).apply()
        },
        label = { Text(label, fontSize = 10.sp) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = VuePrimary.copy(alpha = 0.2f),
            selectedLabelColor = VuePrimary
        )
    )
}
