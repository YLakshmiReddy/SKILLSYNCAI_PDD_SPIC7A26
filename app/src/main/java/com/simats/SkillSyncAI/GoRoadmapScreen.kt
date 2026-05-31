package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ElectricBolt
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

val GoBgStart = Color(0xFFF0F9FF)
val GoBgEnd = Color(0xFFFFFFFF)
val GoPrimary = Color(0xFF00ADD8) // Go Blue
val GoSecondary = Color(0xFF29BEB0)
val GoDark = Color(0xFF007D9C)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.ElectricBolt, null, tint = GoPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Go Developer", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = GoDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
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
                .background(Brush.verticalGradient(listOf(GoBgStart, GoBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Go Developer\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Mastering Google's Go language: from syntax basics to high-concurrency systems and cloud-native architecture.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            GoRoadmapCard(
                phase = "1",
                title = "Introduction to Go",
                items = listOf(
                    "Foundational Concepts" to listOf("Why use Go?", "History of Go"),
                    "Environment Setup" to listOf("Go SDK Install", "Editor & Workspace")
                ),
                preferenceManager = preferenceManager,
                prefix = "go_p1"
            )

            // Phase 2
            GoRoadmapCard(
                phase = "2",
                title = "Language Basics",
                items = listOf(
                    "Variables & Constants" to listOf("Declaration Syntax", "Zero Values", "Shothand (:=)"),
                    "Data Types" to listOf("Numerical", "Boolean", "Strings"),
                    "Composite Types" to listOf("Maps", "Arrays", "Slices & Caps", "Structs & Tags")
                ),
                preferenceManager = preferenceManager,
                prefix = "go_p2"
            )

            // Phase 3
            GoRoadmapCard(
                phase = "3",
                title = "Control Flow & Functions",
                items = listOf(
                    "Anonymous Closures" to listOf("Variadic input", "Deferred execution"),
                    "Pointers & GC" to listOf("Memory Safety", "Heap vs Stack")
                ),
                preferenceManager = preferenceManager,
                prefix = "go_p3"
            )

            // Phase 4
            GoRoadmapCard(
                phase = "4",
                title = "Methods & Interfaces",
                items = listOf(
                    "Generics Introduction" to listOf("Type parameters", "Constraint checks"),
                    "Pointer vs Value Receivers" to listOf("State mutation", "Copy overhead")
                ),
                preferenceManager = preferenceManager,
                prefix = "go_p4"
            )

            // Phase 5
            GoRoadmapCard(
                phase = "5",
                title = "Org & Error Handling",
                items = listOf(
                    "Panic & Recover Patterns" to listOf("Recovery stacks"),
                    "Project Layout & Modules" to listOf("Go Mod workflow")
                ),
                preferenceManager = preferenceManager,
                prefix = "go_p5"
            )

            // Phase 6 - Concurrency (Highlighted)
            GoConcurrencyCard(
                preferenceManager = preferenceManager
            )

            // Phase 7
            GoRoadmapCard(
                phase = "7",
                title = "Testing & Ecosystem",
                items = listOf(
                    "Table driven testing" to listOf("Test suites"),
                    "Ginkgo CLI & Gin/Echo Web" to listOf("Framework choices")
                ),
                preferenceManager = preferenceManager,
                prefix = "go_p7"
            )

            // Phase 8
            GoRoadmapCard(
                phase = "8",
                title = "Advanced Performance",
                items = listOf(
                    "Profiling with pprof" to listOf("CPU & Mem profiles"),
                    "Escape Analysis & CGO" to listOf("Assembly interop")
                ),
                preferenceManager = preferenceManager,
                prefix = "go_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun GoRoadmapCard(
    phase: String,
    title: String,
    items: List<Pair<String, List<String>>>,
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
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFF0F7FF),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(phase, fontWeight = FontWeight.Bold, color = GoPrimary, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Phase $phase: $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { (subTitle, subItems) ->
                Text(
                    text = subTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = GoDark,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                subItems.forEach { item ->
                    GoCheckboxItem(item, subTitle, prefix, preferenceManager)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun GoConcurrencyCard(preferenceManager: PreferenceManager) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF5361BB)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Phase 6: Concurrency",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            ConcurrencyBox("Goroutines", "Lightweight thread abstraction", "go_con_1", preferenceManager)
            ConcurrencyBox("Channels", "Communication over shared memory", "go_con_2", preferenceManager)
            ConcurrencyBox("Sync & Context", "WaitGroups, Mutexes, Cancellations", "go_con_3", preferenceManager)
        }
    }
}

@Composable
fun ConcurrencyBox(title: String, desc: String, key: String, preferenceManager: PreferenceManager) {
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(desc, color = Color.White.copy(alpha = 0.7f), fontSize = 11.sp)
            }
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    preferenceManager.getSharedPrefs().edit().putBoolean(key, it).apply()
                },
                colors = CheckboxDefaults.colors(checkedColor = Color.White, checkmarkColor = GoPrimary)
            )
        }
    }
}

@Composable
fun GoCheckboxItem(item: String, subTitle: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${subTitle}_$item"
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
            colors = CheckboxDefaults.colors(checkedColor = GoPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 13.sp,
            color = if (checked) Color.Gray else Color.Black,
            lineHeight = 18.sp
        )
    }
}
