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

val CppBgStart = Color(0xFFF8F9FF)
val CppBgEnd = Color(0xFFFFFFFF)
val CppPrimary = Color(0xFF004482) // C++ Blue
val CppDark = Color(0xFF1A1A1A)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CppRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Code, null, tint = CppPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = CppDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = CppDark)
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
                .background(Brush.verticalGradient(listOf(CppBgStart, CppBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "C++ Developer\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = CppDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the world's most powerful programming language with this comprehensive 7 phase curriculum. Track your progress from foundations to professional ecosystem tools.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            CppRoadmapCard(
                phase = "1",
                title = "Introduction to the Language",
                items = listOf(
                    "Foundational Concepts" to listOf("History of C++", "C++ vs C / C++ Flavors"),
                    "Environment Setup" to listOf("Install a Compiler (GCC/Clang/MSVC)", "IDE/Editor Setup (VS Code, CLion, Visual Studio)", "First 'Hello World'")
                ),
                preferenceManager = preferenceManager,
                prefix = "cpp_p1"
            )

            // Phase 2
            CppRoadmapCard(
                phase = "2",
                title = "Basics of Programming",
                items = listOf(
                    "Basic Operations" to listOf("Variables & Types (Primitives)", "Arithmetic & Logical Operators", "Input/Output (cin, cout/format)"),
                    "Flow Control" to listOf("Conditional Statements", "Loops (for, while, do-while)", "Switch statements")
                ),
                preferenceManager = preferenceManager,
                prefix = "cpp_p2"
            )

            // Phase 3
            CppRoadmapCard(
                phase = "3",
                title = "Data Types & Memory Management",
                items = listOf(
                    "Type System & Memory" to listOf("Static vs Dynamic Typing", "The Stack & Heap", "Sizeof & Type properties"),
                    "Pointers & References" to listOf("Raw Pointers & Address-of operator", "Smart Pointers (unique, shared, weak)", "Object Lifetime Management")
                ),
                preferenceManager = preferenceManager,
                prefix = "cpp_p3"
            )

            // Phase 4
            CppRoadmapCard(
                phase = "4",
                title = "Structuring a Codebase",
                items = listOf(
                    "Organization" to listOf("Header Files (.h/.hpp)", "Implementation (.cpp)", "Namespaces"),
                    "OOP" to listOf("Classes & Methods (Access Modifiers)", "Encapsulation, Inheritance, Polymorphism", "Static and Const")
                ),
                preferenceManager = preferenceManager,
                prefix = "cpp_p4"
            )

            // Phase 5
            CppRoadmapCard(
                phase = "5",
                title = "Advanced Language Concepts",
                items = listOf(
                    "Templates & Generics" to listOf("Function & Class templates", "Type deduction/sfinae/concepts", "Variadic Templates"),
                    "Safety & Scaling" to listOf("Exception Handling (try/catch)", "RAII Principle", "Run-time Type Identification (RTTI)")
                ),
                preferenceManager = preferenceManager,
                prefix = "cpp_p5"
            )

            // Phase 6
            CppRoadmapCard(
                phase = "6",
                title = "Standard Library (STL) & Idioms",
                items = listOf(
                    "STL Mastery" to listOf("Containers (vectors, lists, maps, etc)", "Iterators & Algorithms", "C++ Components (Threads, Atomic)"),
                    "Core Idioms" to listOf("Rule of Three/Five/Zero (Memory Management)", "Move Semantics (L-Values, R-Values)", "PIMPL Principle")
                ),
                preferenceManager = preferenceManager,
                prefix = "cpp_p6"
            )

            // Phase 7
            CppRoadmapCard(
                phase = "7",
                title = "Professional Tools and Ecosystem",
                items = listOf(
                    "Build Systems" to listOf("Build Tools (G++ / MSVC / Clang)", "CMake / Ninja / MS Build Tools", "CI/CD Workflows for C++ CI (GHA)"),
                    "Ecosystem" to listOf("Open Source Frameworks", "Profiling Tools (Valgrind)", "Package Management (vcpkg, Conan)")
                ),
                preferenceManager = preferenceManager,
                prefix = "cpp_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CppRoadmapCard(
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
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Phase $phase: $title",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = CppPrimary
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { (subTitle, subItems) ->
                Text(
                    text = subTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = CppDark,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                subItems.forEach { item ->
                    CppCheckboxItem(item, subTitle, prefix, preferenceManager)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun CppCheckboxItem(item: String, subTitle: String, prefix: String, preferenceManager: PreferenceManager) {
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
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = CppPrimary)
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
