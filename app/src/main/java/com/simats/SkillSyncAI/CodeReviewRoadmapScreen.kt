package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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

val CRBgStart = Color(0xFFF9FAFF)
val CRBgEnd = Color(0xFFFFFFFF)
val CRPrimary = Color(0xFF1E3A8A) // Dark Blue
val CRDark = Color(0xFF111827)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeReviewRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Menu, null, tint = CRPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Code Review Pyramid", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = CRPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = CRDark)
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
                .background(Brush.verticalGradient(listOf(CRBgStart, CRBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "The Quality\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = CRPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the code review process from foundational semantics to visual consistency. Follow the hierarchy for maximum impact.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Code Styles (The Peak)
            CRRoadmapCard(
                title = "Code Styles (The Peak)",
                items = listOf("Formatting", "Naming Conventions", "Review Strategy"),
                preferenceManager = preferenceManager,
                prefix = "cr_peak"
            )

            // 2. Tests
            CRRoadmapCard(
                title = "Tests",
                items = listOf("Coverage", "Test Quality", "Regression"),
                preferenceManager = preferenceManager,
                prefix = "cr_tests"
            )

            // 3. Documentation
            CRRoadmapCard(
                title = "Documentation",
                items = listOf("Readability", "API Documentation", "Impact Analysis"),
                preferenceManager = preferenceManager,
                prefix = "cr_doc"
            )

            // 4. Implementation Semantics
            CRRoadmapCard(
                title = "Implementation Semantics",
                items = listOf("Logic Errors", "Performance", "Security"),
                preferenceManager = preferenceManager,
                prefix = "cr_imp"
            )

            // 5. API Semantics (The Foundation)
            CRRoadmapCard(
                title = "API Semantics (The Foundation)",
                items = listOf("Breaking Changes", "Consistency", "Clarity"),
                preferenceManager = preferenceManager,
                prefix = "cr_found"
            )

            // 6. Automation & Tooling
            CRRoadmapCard(
                title = "Automation & Tooling",
                items = listOf("Linters & Static Analysis", "CI/CD Integration", "Pre-commit Hooks", "Automated Security Scanning"),
                preferenceManager = preferenceManager,
                prefix = "cr_auto"
            )

            // 7. Soft Skills & Communication
            CRRoadmapCard(
                title = "Soft Skills & Communication",
                items = listOf("Constructive Feedback", "Empathy & Tone", "Timeliness & Respect", "Collaborative Problem Solving"),
                preferenceManager = preferenceManager,
                prefix = "cr_soft"
            )

            // 8. Review Process & Workflow
            CRRoadmapCard(
                title = "Review Process & Workflow",
                items = listOf("Manageable PR Sizes", "Review Iteration Cycles", "Approval & Merger Criteria", "Knowledge Sharing Protocols"),
                preferenceManager = preferenceManager,
                prefix = "cr_process"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CRRoadmapCard(
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
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = CRPrimary)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = CRPrimary
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                CRCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun CRCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(24.dp),
            color = if (checked) CRPrimary else Color(0xFFF3F4F6),
            shape = RoundedCornerShape(4.dp)
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
            color = if (checked) Color.Gray else CRDark,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
