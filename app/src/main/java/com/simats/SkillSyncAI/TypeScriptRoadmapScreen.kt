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

val TS_BgStart = Color(0xFFF0F7FF)
val TS_BgEnd = Color(0xFFFFFFFF)
val TS_Primary = Color(0xFF3178C6) // TypeScript Blue
val TS_Dark = Color(0xFF1E293B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeScriptRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Code, null, tint = TS_Primary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("TS.ARCHITECT", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TS_Primary)
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
                .background(Brush.verticalGradient(listOf(TS_BgStart, TS_BgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "TypeScript\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the world's most popular superset of JavaScript through a structured, step-by-step path from core concepts to advanced meta-programming.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            TSRoadmapCard(
                number = "1",
                title = "Introduction to TypeScript",
                icon = Icons.Default.Info,
                items = listOf(
                    "Core Concepts: TS vs JS, Interoperability",
                    "Running TS: Tools (tsc, ts-node, Playground)",
                    "Installation & Config: tsconfig.json, Options"
                ),
                preferenceManager = preferenceManager,
                prefix = "ts_p1"
            )

            // Phase 2
            TSRoadmapCard(
                number = "2",
                title = "The Type System",
                icon = Icons.Default.Schema,
                items = listOf(
                    "Primitives (Boolean, number, etc.)",
                    "Object Types: Interface, Class, Tuple",
                    "Top (any, unknown) & Bottom (never) Types",
                    "Type Logic: Inference & Compatibility",
                    "Assertions: as (type), non-null, satisfies"
                ),
                preferenceManager = preferenceManager,
                prefix = "ts_p2"
            )

            // Phase 3
            TSRoadmapCard(
                number = "3",
                title = "Combining & Narrowing",
                icon = Icons.Default.MergeType,
                items = listOf(
                    "Combining: Union, Intersection, Aliases, keyof",
                    "Narrowing: instanceof, typeof, Predicates"
                ),
                preferenceManager = preferenceManager,
                prefix = "ts_p3"
            )

            // Phase 4
            TSRoadmapCard(
                number = "4",
                title = "Functions, Interfaces & Classes",
                icon = Icons.Default.Layers,
                items = listOf(
                    "Functions: Typing params, Return, Overloading",
                    "Interfaces: Extending, Hybrid Types, vs Types",
                    "Classes: Access Modifiers, Abstract, Constructors"
                ),
                preferenceManager = preferenceManager,
                prefix = "ts_p4"
            )

            // Phase 5
            TSRoadmapCard(
                number = "5",
                title = "Generics & Utility Types",
                icon = Icons.Default.AutoAwesome,
                items = listOf(
                    "Generics: Reusable logic, Generic Constraints",
                    "Utilities: Partial, Pick, Omit, Readonly",
                    "Meta Helpers: Parameters, ReturnType"
                ),
                preferenceManager = preferenceManager,
                prefix = "ts_p5"
            )

            // Phase 6
            TSRoadmapCard(
                number = "6",
                title = "Advanced Types",
                icon = Icons.Default.AddCircleOutline,
                items = listOf(
                    "Mapped & Conditional Types",
                    "Template Literals & Recursive Types",
                    "Decorators: Meta-programming basics"
                ),
                preferenceManager = preferenceManager,
                prefix = "ts_p6"
            )

            // Phase 7
            TSRoadmapCard(
                number = "7",
                title = "Modules & Ecosystem",
                icon = Icons.Default.Extension,
                items = listOf(
                    "Modules: Namespaces & Global Augmentation",
                    "Ecosystem: ESLint, Prettier, Build Tools"
                ),
                preferenceManager = preferenceManager,
                prefix = "ts_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun TSRoadmapCard(
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
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    Text(
                        text = "$number. $title",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = TS_Dark
                    )
                }
                Icon(icon, null, tint = TS_Primary.copy(alpha = 0.6f), modifier = Modifier.size(20.dp))
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                TSCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun TSCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = TS_Primary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 13.sp,
            color = if (checked) Color.Gray else TS_Dark,
            lineHeight = 18.sp
        )
    }
}
