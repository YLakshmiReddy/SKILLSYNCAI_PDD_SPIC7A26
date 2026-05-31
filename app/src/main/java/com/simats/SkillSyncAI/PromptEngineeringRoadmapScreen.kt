package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Psychology
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

val PEBgStart = Color(0xFFF9FAFF)
val PEBgEnd = Color(0xFFFFFFFF)
val PEPrimary = Color(0xFF4F46E5) // Indigo
val PEDark = Color(0xFF1E1B4B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptEngineeringRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Psychology, null, tint = PEPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PEDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PEDark)
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
                .background(Brush.verticalGradient(listOf(PEBgStart, PEBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Prompt\nEngineering\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = PEPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the art of communicating with Large Language Models. From core concepts to advanced reasoning patterns, track your journey through the strata of AI expertise.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            PERoadmapCard(
                phase = "1",
                title = "Introduction to LLMs",
                items = listOf("Core Concepts", "Common Terminology", "Model Providers"),
                preferenceManager = preferenceManager,
                prefix = "pe_p1"
            )

            // Phase 2
            PERoadmapCard(
                phase = "2",
                title = "LLM Configuration & Sampling",
                items = listOf("Sampling Parameters", "Output Control", "Penalties"),
                preferenceManager = preferenceManager,
                prefix = "pe_p2"
            )

            // Phase 3
            PERoadmapCard(
                phase = "3",
                title = "Prompting Techniques",
                items = listOf("Foundational Techniques", "Instructional Frameworks", "Advanced Reasoning Patterns", "Structural Outputs"),
                preferenceManager = preferenceManager,
                prefix = "pe_p3"
            )

            // Phase 4
            PERoadmapCard(
                phase = "4",
                title = "Prompting Best Practices",
                items = listOf("Clarity & Structure", "Dynamic Configuration", "Optimization"),
                preferenceManager = preferenceManager,
                prefix = "pe_p4"
            )

            // Phase 5
            PERoadmapCard(
                phase = "5",
                title = "Improving Reliability & Security",
                items = listOf(
                    "Reliability Strategies" to "Implementation of fallback mechanisms and consistency checks.",
                    "Security & Testing" to "Mitigating prompt injection and validating output safety."
                ),
                preferenceManager = preferenceManager,
                prefix = "pe_p5",
                isDetailed = true
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PERoadmapCard(
    phase: String,
    title: String,
    items: List<Any>,
    preferenceManager: PreferenceManager,
    prefix: String,
    isDetailed: Boolean = false
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
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = PEPrimary)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "PHASE $phase",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PEPrimary
                )
            }
            
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = PEDark,
                modifier = Modifier.padding(start = 44.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                if (isDetailed && item is Pair<*, *>) {
                    PEDetailedItem(item.first as String, item.second as String, title, prefix, preferenceManager)
                } else if (item is String) {
                    PECheckboxItem(item, title, prefix, preferenceManager)
                }
            }
        }
    }
}

@Composable
fun PECheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(20.dp),
            color = Color(0xFFF3F4F6),
            shape = RoundedCornerShape(4.dp)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    checked = isChecked
                    preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
                },
                colors = CheckboxDefaults.colors(checkedColor = PEPrimary)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else PEDark,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun PEDetailedItem(subTitle: String, desc: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$subTitle"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFF).copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    checked = isChecked
                    preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
                },
                colors = CheckboxDefaults.colors(checkedColor = PEPrimary)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = subTitle, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = if (checked) Color.Gray else PEDark)
                Text(text = desc, fontSize = 11.sp, color = Color.Gray, lineHeight = 14.sp)
            }
        }
    }
}
