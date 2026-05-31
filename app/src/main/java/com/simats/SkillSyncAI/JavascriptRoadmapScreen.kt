package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Javascript
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

val JsBgStart = Color(0xFFFFFBE6)
val JsBgEnd = Color(0xFFFFFFFF)
val JsPrimary = Color(0xFFF7DF1E) // JS Yellow
val JsDark = Color(0xFF323330)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JavascriptRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Javascript, null, tint = JsDark, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("DevPath", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = JsDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = JsDark)
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
                .background(Brush.verticalGradient(listOf(JsBgStart, JsBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "JavaScript\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = JsDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the world's most versatile language through a structured 7-phase curriculum. Track your progress from fundamentals to advanced engine mechanics.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            JsRoadmapCard(
                title = "Introduction & Variable Fundamentals",
                items = listOf("Basics", "Variables", "Scopes"),
                preferenceManager = preferenceManager,
                prefix = "js_p1"
            )

            // Phase 2
            JsRoadmapCard(
                title = "Data Types & Structures",
                items = listOf("Primitive Types", "Objects & Prototypes", "Data Structures", "Type Casting"),
                preferenceManager = preferenceManager,
                prefix = "js_p2"
            )

            // Phase 3
            JsRoadmapCard(
                title = "Logic & Control Flow",
                items = listOf("Expressions & Operators", "Equality", "Control Flow", "Exception Handling"),
                preferenceManager = preferenceManager,
                prefix = "js_p3"
            )

            // Phase 4
            JsRoadmapCard(
                title = "Functions & Functional Concepts",
                items = listOf("Power of Functions", "Scope & Stack"),
                preferenceManager = preferenceManager,
                prefix = "js_p4"
            )

            // Phase 5
            JsRoadmapCard(
                title = "Advanced Context & Objects",
                items = listOf("This Keyword", "Function Borrowing", "Modern JS Features"),
                preferenceManager = preferenceManager,
                prefix = "js_p5"
            )

            // Phase 6
            JsRoadmapCard(
                title = "Asynchronous JavaScript & APIs",
                items = listOf("Event Loop", "Asynchrony", "Working with APIs"),
                preferenceManager = preferenceManager,
                prefix = "js_p6"
            )

            // Phase 7
            JsRoadmapCard(
                title = "Memory & Tooling",
                items = listOf("Memory Management", "Browser DevTools"),
                preferenceManager = preferenceManager,
                prefix = "js_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun JsRoadmapCard(
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
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color(0xFF1E3A8A), // Navy blue from image
                    modifier = Modifier.weight(1f)
                )
                
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFFE5E7EB), RoundedCornerShape(4.dp))
                ) {
                    Checkbox(
                        checked = cardChecked,
                        onCheckedChange = { isChecked ->
                            cardChecked = isChecked
                            preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                        },
                        colors = CheckboxDefaults.colors(checkedColor = JsPrimary)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                JsCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun JsCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(checkedColor = JsPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else JsDark,
            lineHeight = 20.sp
        )
    }
}
