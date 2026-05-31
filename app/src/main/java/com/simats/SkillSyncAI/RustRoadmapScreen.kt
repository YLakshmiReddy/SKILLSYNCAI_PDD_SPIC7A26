package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
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

val RustBgStart = Color(0xFFF6F7FF)
val RustBgEnd = Color(0xFFFFFFFF)
val RustPrimary = Color(0xFFDEA584) // Rust Orange/Brown
val RustSecondary = Color(0xFF535C91) // Deep Purple/Blue
val RustDark = Color(0xFF070F2B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RustRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Build, null, tint = RustSecondary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Rust Architect", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = RustDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = RustDark)
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
                .background(Brush.verticalGradient(listOf(RustBgStart, RustBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Master the Iron\nLanguage",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = RustSecondary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A structured, 8-phase journey from binary basics to high-performance systems engineering.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            RustRoadmapCard(
                phase = "01",
                title = "Introduction and Environment",
                items = listOf("The \"Why\" of Rust", "Environment Setup"),
                preferenceManager = preferenceManager,
                prefix = "rs_p1"
            )

            // Phase 2
            RustRoadmapCard(
                phase = "02",
                title = "Language Basics",
                items = listOf("Syntax and Semantics", "Functions and Methods", "Pattern Matching"),
                preferenceManager = preferenceManager,
                prefix = "rs_p2"
            )

            // Phase 3
            RustRoadmapCard(
                phase = "03",
                title = "The Ownership System",
                items = listOf("Memory Safety", "Borrowing and References"),
                preferenceManager = preferenceManager,
                prefix = "rs_p3"
            )

            // Phase 4
            RustRoadmapCard(
                phase = "04",
                title = "Constructs and Error Handling",
                items = listOf("Advanced Types", "Error Handling"),
                preferenceManager = preferenceManager,
                prefix = "rs_p4"
            )

            // Phase 5
            RustRoadmapCard(
                phase = "05",
                title = "Modules, Crates, and Testing",
                items = listOf("Ecosystem Management", "The Testing Suite"),
                preferenceManager = preferenceManager,
                prefix = "rs_p5"
            )

            // Phase 6
            RustRoadmapCard(
                phase = "06",
                title = "Advanced Language Features",
                items = listOf("Traits and Generics", "Lifetimes", "Macros and Metaprogramming"),
                preferenceManager = preferenceManager,
                prefix = "rs_p6"
            )

            // Phase 7
            RustRoadmapCard(
                phase = "07",
                title = "Concurrency and Parallelism",
                items = listOf("Safe Concurrency", "Asynchronous Programming"),
                preferenceManager = preferenceManager,
                prefix = "rs_p7"
            )

            // Phase 8
            RustRoadmapCard(
                phase = "08",
                title = "Ecosystem and Domain Specialization",
                items = listOf("Web and Networking", "Serialization and Data", "Specialized Fields"),
                preferenceManager = preferenceManager,
                prefix = "rs_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun RustRoadmapCard(
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PHASE $phase",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = RustSecondary
                )
                HorizontalDivider(modifier = Modifier.width(20.dp), thickness = 1.dp, color = Color.LightGray)
            }
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = RustDark
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                RustCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun RustCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = RustSecondary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else RustDark,
            modifier = Modifier.weight(1f)
        )
    }
}
