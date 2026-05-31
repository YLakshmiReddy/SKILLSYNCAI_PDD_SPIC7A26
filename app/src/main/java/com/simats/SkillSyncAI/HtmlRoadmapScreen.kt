package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Html
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

val HtmlBgStart = Color(0xFFF5F7FF)
val HtmlBgEnd = Color(0xFFFFFFFF)
val HtmlPrimary = Color(0xFFE34F26) // HTML5 Orange
val HtmlDark = Color(0xFF1A1A1A)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HtmlRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Html, null, tint = HtmlPrimary, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = HtmlDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = HtmlDark)
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
                .background(Brush.verticalGradient(listOf(HtmlBgStart, HtmlBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "HTML Developer",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = HtmlDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the structural foundation of the modern web through this 7-phase atmospheric learning path.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            HtmlRoadmapCard(
                phase = "01",
                title = "Introduction & How the Web Works",
                items = listOf(
                    "Markup Languages basics",
                    "Frontend roles (HTML, CSS, JS)",
                    "Web Ecosystem (HTTP, DNS)",
                    "Browsers & Hosting",
                    "Intro to SEO"
                ),
                preferenceManager = preferenceManager,
                prefix = "html_p1"
            )

            // Phase 2
            HtmlRoadmapCard(
                phase = "02",
                title = "Your First HTML File",
                items = listOf(
                    "Document Structure",
                    "Syntax Rules & Attributes",
                    "HTML Entities",
                    "Formatting (strong, em)"
                ),
                preferenceManager = preferenceManager,
                prefix = "html_p2"
            )

            // Phase 3
            HtmlRoadmapCard(
                phase = "03",
                title = "Textual Tags & Attributes",
                items = listOf(
                    "Preformatting (pre)",
                    "Links (a, href)",
                    "Core Attributes (id, class)",
                    "Data Attributes"
                ),
                preferenceManager = preferenceManager,
                prefix = "html_p3"
            )

            // Phase 4
            HtmlRoadmapCard(
                phase = "04",
                title = "Lists, Tables & Media",
                items = listOf(
                    "Lists (ul, ol, li)",
                    "Tables (table, tr, td, th)",
                    "Images & Figures",
                    "Audio, Video, Iframe"
                ),
                preferenceManager = preferenceManager,
                prefix = "html_p4"
            )

            // Phase 5
            HtmlRoadmapCard(
                phase = "05",
                title = "Using Forms",
                items = listOf(
                    "Labels and Inputs",
                    "File Uploads",
                    "Form Validation"
                ),
                preferenceManager = preferenceManager,
                prefix = "html_p5"
            )

            // Phase 6
            HtmlRoadmapCard(
                phase = "06",
                title = "Semantic Markup & Layout",
                items = listOf(
                    "Semantic Tags (blockquote, q, abbr)",
                    "Document Structure (header, nav)",
                    "Content Sections (article, aside)"
                ),
                preferenceManager = preferenceManager,
                prefix = "html_p6"
            )

            // Phase 7
            HtmlRoadmapCard(
                phase = "07",
                title = "Styling, Scripting & SEO",
                items = listOf(
                    "Styling basics (internal/external)",
                    "Including Scripts",
                    "Accessibility & Screen Readers",
                    "SEO Metadata & Hierarchy"
                ),
                preferenceManager = preferenceManager,
                prefix = "html_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun HtmlRoadmapCard(
    phase: String,
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
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PHASE $phase",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F51B5)
                )
                
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    modifier = Modifier.size(24.dp),
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3F51B5))
                )
            }
            
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = HtmlDark
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                HtmlCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun HtmlCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Html,
            contentDescription = null,
            tint = if (checked) HtmlPrimary else Color.LightGray,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else HtmlDark,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
            },
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3F51B5))
        )
    }
}
