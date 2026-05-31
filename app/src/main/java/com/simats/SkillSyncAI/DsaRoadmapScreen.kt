package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
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

val DsaBgStart = Color(0xFFF9FAFF)
val DsaBgEnd = Color(0xFFFFFFFF)
val DsaPrimary = Color(0xFF3F51B5) // DSA Indigo
val DsaDark = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DsaRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Menu, null, tint = DsaPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DsaDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = DsaDark)
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
                .background(Brush.verticalGradient(listOf(DsaBgStart, DsaBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Data Structures &\nAlgorithms\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = DsaDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A comprehensive step-by-step guide to\nmastering core CS foundations.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Programming Fundamentals
            DsaRoadmapCard(
                number = "1",
                title = "Programming Fundamentals",
                items = listOf(
                    "Pick a Language: C#, C++, Python, Java, or JS",
                    "Core Logic: Syntax, Control, Functions",
                    "OOP Basics: Classes, Objects, Inheritance",
                    "Pseudo Code"
                ),
                preferenceManager = preferenceManager,
                prefix = "dsa_p1"
            )

            // 2. Algorithmic Complexity
            DsaRoadmapCard(
                number = "2",
                title = "Algorithmic Complexity",
                items = listOf(
                    "Performance: Time vs Space Complexity",
                    "Calculation Efficiency measurement",
                    "Asymptotic Notation: Big-O, Big-θ, Big-Ω",
                    "Common Runtimes: Constant to Factorial"
                ),
                preferenceManager = preferenceManager,
                prefix = "dsa_p2"
            )

            // 3. Basic Data Structures
            DsaRoadmapCard(
                number = "3",
                title = "Basic Data Structures",
                items = listOf(
                    "Linear Structures: Arrays, Linked Lists, Stacks, Queues",
                    "Hash-Based Structures: Hash Tables"
                ),
                preferenceManager = preferenceManager,
                prefix = "dsa_p3"
            )

            // 4. Sorting & Searching Algorithms
            DsaRoadmapCard(
                number = "4",
                title = "Sorting & Searching Algorithms",
                items = listOf(
                    "Sorting: Bubble, Insertion, Selection, Merge, Quick, Heap",
                    "Searching: Linear Search, Binary Search"
                ),
                preferenceManager = preferenceManager,
                prefix = "dsa_p4"
            )

            // 5. Tree & Graph Data Structures
            DsaRoadmapCard(
                number = "5",
                title = "Tree & Graph Data Structures",
                items = listOf(
                    "Tree structures: BST, AVL, B-Trees. Traversal methods.",
                    "Graph structures: BFS, DFS, Dijkstra, Bellman-Ford, A*"
                ),
                preferenceManager = preferenceManager,
                prefix = "dsa_p5"
            )

            // 6. Advanced & Complex Data Structures
            DsaRoadmapCard(
                number = "6",
                title = "Advanced & Complex Data Structures",
                items = listOf(
                    "Efficient Indexing: 2-3 Trees, Red-Black Trees, Tries",
                    "Specialized: Disjoint Set, Suffix Trees, Skip Lists"
                ),
                preferenceManager = preferenceManager,
                prefix = "dsa_p6"
            )

            // 7. Problem Solving Techniques
            DsaRoadmapCard(
                number = "7",
                title = "Problem Solving Techniques",
                items = listOf(
                    "Core Strategies: Brute Force, Divide & Conquer, Greedy",
                    "Advanced Paradigms: Dynamic Programming, Backtracking",
                    "Common Patterns: Two Pointers, Sliding Window, Cyclic Sort"
                ),
                preferenceManager = preferenceManager,
                prefix = "dsa_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DsaRoadmapCard(
    number: String,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Surface(
                    modifier = Modifier.size(24.dp),
                    shape = CircleShape,
                    color = if (cardChecked) DsaPrimary else Color.Transparent,
                    border = androidx.compose.foundation.BorderStroke(2.dp, if (cardChecked) DsaPrimary else Color.LightGray)
                ) {
                    Checkbox(
                        checked = cardChecked,
                        onCheckedChange = { isChecked ->
                            cardChecked = isChecked
                            preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Transparent,
                            uncheckedColor = Color.Transparent,
                            checkmarkColor = Color.White
                        )
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$number. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DsaDark
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { item ->
                DsaCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun DsaCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(20.dp),
            shape = CircleShape,
            color = if (checked) DsaPrimary else Color.Transparent,
            border = androidx.compose.foundation.BorderStroke(1.dp, if (checked) DsaPrimary else Color.LightGray)
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
                    checkmarkColor = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else DsaDark,
            lineHeight = 20.sp
        )
    }
}
