package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Code
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

val PythonBgStart = Color(0xFFF0F4FF)
val PythonBgEnd = Color(0xFFFFFFFF)
val PythonPrimary = Color(0xFF3776AB) // Python Blue
val PythonYellow = Color(0xFFFFD43B) // Python Yellow
val PythonDark = Color(0xFF2B5B84)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PythonRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Code, null, tint = PythonPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PythonDark)
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
                .background(Brush.verticalGradient(listOf(PythonBgStart, PythonBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Python\nDeveloper",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A stratified journey from core syntax to high performance asynchronous systems. Master the language that powers AI, Data Science, and broader Web.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Learn the Basics
            PythonRoadmapCard(
                phase = "1",
                title = "Learn the Basics",
                sections = listOf(
                    "FUNDAMENTAL SYNTAX" to listOf("Syntax", "Variables", "Data Types", "Loops", "Error Handling"),
                    "CORE COLLECTIONS" to listOf("Lists", "Tuples", "Sets", "Dictionaries"),
                    "ERROR TYPES (BASIC)" to listOf("Exceptions", "Try/Except")
                ),
                preferenceManager = preferenceManager,
                prefix = "py_p1"
            )

            // 2. Data Structures
            PythonRoadmapCard(
                phase = "2",
                title = "Data Structures",
                sections = listOf(
                    "CORE CONCEPTS" to listOf("Arrays", "Linked Lists", "Stacks", "Queues"),
                    "" to listOf("Binary Search Trees", "Heaps", "Sorting Algorithms")
                ),
                preferenceManager = preferenceManager,
                prefix = "py_p2"
            )

            // 3. Advanced Python Concepts
            PythonRoadmapCard(
                phase = "3",
                title = "Advanced Python Concepts",
                sections = listOf(
                    "Language Features" to listOf("Lambdas", "Decorators", "Iterators", "Yield", "Generators"),
                    "Functional Logic Tools" to listOf("List Comprehensions", "Generator Expressions", "Context Managers")
                ),
                preferenceManager = preferenceManager,
                prefix = "py_p3"
            )

            // 4. OOP
            PythonRoadmapCard(
                phase = "4",
                title = "OOP",
                sections = listOf(
                    "CLASSES" to listOf("Properties & methods"),
                    "INHERITANCE" to listOf("Parent / child structure"),
                    "PRINCIPLES OF OOP" to listOf("Clean Privacy"),
                    "DUI WRAPPING" to listOf("Unique abstractions")
                ),
                preferenceManager = preferenceManager,
                prefix = "py_p4"
            )

            // 5. Ecosystem
            PythonRoadmapCard(
                phase = "5",
                title = "Ecosystem",
                sections = listOf(
                    "PACKAGE MANAGEMENT" to listOf("Pip", "Poetry", "Conda", "venv"),
                    "ENVIRONMENT" to listOf("pyenv", "pipenv"),
                    "STANDARDS" to listOf("PEP 8", "Black", "mypy")
                ),
                preferenceManager = preferenceManager,
                prefix = "py_p5"
            )

            // 6. Concurrency
            PythonRoadmapCard(
                phase = "6",
                title = "Concurrency",
                items = listOf("MULTITHREADED THREADING", "ASYNCIO (NON-BLOCKING I/O)", "MULTIPROCESSING"),
                preferenceManager = preferenceManager,
                prefix = "py_p6",
                isConcurrency = true
            )

            // 7. Frameworks & Testing
            PythonRoadmapCard(
                phase = "7",
                title = "Frameworks & Testing",
                sections = listOf(
                    "CHOOSE YOUR PATH" to listOf("Django", "FastAPI"),
                    "TESTING & DOCUMENTATION" to listOf("Pytest & Unittest", "Sphinx & MkDocs")
                ),
                preferenceManager = preferenceManager,
                prefix = "py_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PythonRoadmapCard(
    phase: String,
    title: String,
    sections: List<Pair<String, List<String>>> = emptyList(),
    items: List<String> = emptyList(),
    preferenceManager: PreferenceManager,
    prefix: String,
    isConcurrency: Boolean = false
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
                Surface(
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(phase, color = PythonPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "$phase. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            if (isConcurrency) {
                items.forEach { item ->
                    PythonDetailedCheckbox(item, title, prefix, preferenceManager)
                }
            } else if (sections.isNotEmpty()) {
                sections.forEach { (sectionTitle, sectionItems) ->
                    if (sectionTitle.isNotEmpty()) {
                        Text(
                            text = sectionTitle,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = PythonPrimary,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    sectionItems.forEach { item ->
                        PythonCheckboxItem(item, sectionTitle, prefix, preferenceManager)
                    }
                }
            } else {
                items.forEach { item ->
                    PythonCheckboxItem(item, "", prefix, preferenceManager)
                }
            }
        }
    }
}

@Composable
fun PythonCheckboxItem(item: String, section: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${section}_$item"
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
            colors = CheckboxDefaults.colors(checkedColor = PythonPrimary)
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

@Composable
fun PythonDetailedCheckbox(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFF)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    checked = isChecked
                    preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
                },
                colors = CheckboxDefaults.colors(checkedColor = PythonPrimary)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = item,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (checked) Color.Gray else Color.Black,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
