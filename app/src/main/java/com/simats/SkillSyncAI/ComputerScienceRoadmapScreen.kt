package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Terminal
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

val CSBgStart = Color(0xFFF0F2F9)
val CSBgEnd = Color(0xFFFFFFFF)
val CSPrimary = Color(0xFF2D3E8E) // Deep Navy
val CSDark = Color(0xFF1A1F36)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComputerScienceRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Terminal, null, tint = CSPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Computer Science Roadmap", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = CSDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = CSDark)
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
                .background(Brush.verticalGradient(listOf(CSBgStart, CSBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Computer Science\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = CSPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A COMPREHENSIVE CURRICULUM FOR THE MODERN ENGINEER",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.Gray,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Programming & Data Structures
            CSRoadmapCard(
                number = "1",
                title = "Programming & Data Structures",
                isCore = true,
                items = listOf(
                    "Pick a Language" to listOf("Python", "Go", "Java / C#", "Rust / C++"),
                    "Data Structures" to listOf("LINEAR: Array / Linked List", "LINEAR: Stack / Queue", "NON LINEAR: Tree / Hash Table", "NON LINEAR: Graphs / Heaps"),
                    "Advanced Trees" to listOf("Binary Tree Variations", "AVL / Red-Black Trees", "B-Trees / Skip Lists")
                ),
                preferenceManager = preferenceManager,
                prefix = "cs_p1"
            )

            // 2. Algorithms & Complexity
            CSRoadmapCard(
                number = "2",
                title = "Algorithms & Complexity",
                items = listOf(
                    "Notation & Runtimes" to listOf("Big O, Theta, Omega", "Search & Sort Algorithms"),
                    "Graph Algorithms" to listOf("BFS / DFS", "Dijkstra's / A*"),
                    "Paradigms & Classes" to listOf("Greedy / Backtracking", "P vs NP Complexity")
                ),
                preferenceManager = preferenceManager,
                prefix = "cs_p2"
            )

            // 3. Mathematics & Logic
            CSRoadmapCard(
                number = "3",
                title = "Mathematics & Logic",
                items = listOf(
                    "LOGIC FOUNDATIONS" to listOf("Probability & Combinatorics", "String Manipulation"),
                    "PATTERN MATCHING" to listOf("Regex Concepts", "Pattern Matching"),
                    "DESIGN PATTERNS" to listOf("GoF Design Patterns", "Dependency Injection")
                ),
                preferenceManager = preferenceManager,
                prefix = "cs_p3"
            )

            // 4. Systems & Architecture
            CSRoadmapCard(
                number = "4",
                title = "Systems & Architecture",
                items = listOf(
                    "Hardware Layer" to listOf("CPU, Registers & RAM", "CPU Cache Systems"),
                    "Operating Systems" to listOf("Threads & Scheduling", "Memory Management"),
                    "Low-Level" to listOf("Instruction & Floating Point", "Bitwise Operations")
                ),
                preferenceManager = preferenceManager,
                prefix = "cs_p4"
            )

            // 5. Networking, Databases & Security
            CSRoadmapCard(
                number = "5",
                title = "Networking, Databases & Security",
                items = listOf(
                    "Networking" to listOf("OSI & TCP/IP Model", "HTTP / TLS / Sockets"),
                    "Databases" to listOf("ACID vs BASE Models", "Sharding & Partitioning"),
                    "System Design" to listOf("CDN & Load Balancing", "Hashing & Encryption")
                ),
                preferenceManager = preferenceManager,
                prefix = "cs_p5"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CSRoadmapCard(
    number: String,
    title: String,
    items: List<Pair<String, List<String>>>,
    preferenceManager: PreferenceManager,
    prefix: String,
    isCore: Boolean = false
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$number. $title",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = CSDark,
                    modifier = Modifier.weight(1f)
                )
                if (isCore) {
                    Surface(
                        color = Color(0xFFE0E7FF),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "CORE",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = CSPrimary
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { (subTitle, subItems) ->
                Text(
                    text = subTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = CSPrimary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                subItems.forEach { item ->
                    CSCheckboxItem(item, subTitle, prefix, preferenceManager)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun CSCheckboxItem(item: String, subTitle: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${subTitle}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Custom Checkbox Style (Hook shape from image)
        Surface(
            modifier = Modifier.size(18.dp),
            color = Color.Transparent,
            border = androidx.compose.foundation.BorderStroke(1.dp, if (checked) CSPrimary else Color.LightGray),
            shape = RoundedCornerShape(2.dp)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    checked = isChecked
                    preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Transparent,
                    uncheckedColor = Color.Transparent,
                    checkmarkColor = CSPrimary
                )
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else CSDark,
            lineHeight = 20.sp
        )
    }
}
