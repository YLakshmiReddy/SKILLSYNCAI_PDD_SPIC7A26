package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
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

val QABgStart = Color(0xFFF1F4FF)
val QABgEnd = Color(0xFFFFFFFF)
val QAPrimary = Color(0xFF1A237E)
val QAAccent = Color(0xFFE8EAF6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QAEngineerScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = QAPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = QAPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = QAPrimary)
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
                .background(Brush.verticalGradient(listOf(QABgStart, QABgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "QA Engineer\nEvolutionary\nPath",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = QAPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the spectrum of Quality Assurance from foundational core concepts to advanced automated infrastructure and CI/CD monitoring.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            QARoadmapCard(
                phase = "01",
                title = "Learn the Fundamentals",
                preferenceManager = preferenceManager,
                prefix = "qa_p1",
                content = {
                    QASubSection("CORE CONCEPTS", listOf("What is QA?", "QA Mindset", "Testing Approaches", "Software Quality Metrics", "Risk-Based Testing"), preferenceManager, "qa_p1_core")
                    Spacer(modifier = Modifier.height(16.dp))
                    QASubSection("MANAGEMENT TOOLS", listOf("Jira, TestRail, Zephyr", "Confluence & Documentation"), preferenceManager, "qa_p1_mgmt")
                }
            )

            // Phase 02
            QARoadmapCard(
                phase = "02",
                title = "SDLC & Methodologies",
                preferenceManager = preferenceManager,
                prefix = "qa_p2",
                content = {
                    QASubSection("DELIVERY MODELS", listOf("Waterfall & V-Model", "Agile & Scrum", "DevOps & SRE Basics"), preferenceManager, "qa_p2_del")
                    Spacer(modifier = Modifier.height(16.dp))
                    QASubSection("METHODOLOGIES", listOf("BDD (Cucumber, Gherkin)", "TDD & Unit Testing Foundations", "Root Cause Analysis (RCA)"), preferenceManager, "qa_p2_meth")
                }
            )

            // Phase 03
            QARoadmapCard(
                phase = "03",
                title = "Manual Testing",
                preferenceManager = preferenceManager,
                prefix = "qa_p3",
                content = {
                    QASubSection("SKILLS & TECHNIQUES", listOf("Test Planning & Design", "Test Cases & Suites", "Verification & Validation", "Exploratory Testing"), preferenceManager, "qa_p3_skills")
                    Spacer(modifier = Modifier.height(16.dp))
                    QASubSection("TESTING TYPES", listOf("Functional (Smoke, Regression)", "Non-functional (Usability, Localisation)"), preferenceManager, "qa_p3_types")
                }
            )

            // Phase 04
            QARoadmapCard(
                phase = "04",
                title = "Automated Testing",
                preferenceManager = preferenceManager,
                prefix = "qa_p4",
                content = {
                    QASubSection("FRONTEND & BACKEND", listOf("Selenium (Java/Python)", "Playwright & Cypress", "Backend & REST API Automation", "Postman & Newman"), preferenceManager, "qa_p4_fb")
                    Spacer(modifier = Modifier.height(16.dp))
                    QASubSection("PLATFORMS & SPECIALTY", listOf("Mobile (Appium, Espresso, XCUITest)", "Headless Browser Testing", "Parallel Execution"), preferenceManager, "qa_p4_spec")
                }
            )

            // Phase 05
            QARoadmapCard(
                phase = "05",
                title = "Specialized Testing",
                preferenceManager = preferenceManager,
                prefix = "qa_p5",
                content = {
                    QASubSection("NON-FUNCTIONAL AUTO", listOf("Performance (JMeter, k6)", "Security Testing (OWASP ZAP)", "Accessibility (AXE, WCAG)"), preferenceManager, "qa_p5_nf")
                    Spacer(modifier = Modifier.height(16.dp))
                    QASubSection("TESTING DATA", listOf("Data Masking & Privacy", "Test Data Generation", "Database Testing (SQL)"), preferenceManager, "qa_p5_data")
                }
            )

            // Final Phase
            QARoadmapCard(
                phase = "FINAL",
                title = "Monitoring, Logs & CI/CD",
                preferenceManager = preferenceManager,
                prefix = "qa_final",
                content = {
                    QASubSection("OBSERVABILITY", listOf("New Relic & Datadog", "Log Management (ELK, Splunk)", "Error Tracking (Sentry)"), preferenceManager, "qa_final_obs")
                    Spacer(modifier = Modifier.height(16.dp))
                    QASubSection("DEVOPS FLOW", listOf("Git & Branching Strategies", "Jenkins & GitHub Actions", "Cloud Infrastructure (AWS/Azure)"), preferenceManager, "qa_final_flow")
                    Spacer(modifier = Modifier.height(16.dp))
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("RESULT INSIGHTS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                            QACheckboxItem("Advanced AI Reporting", "qa_final_report", preferenceManager)
                            QACheckboxItem("Quality Gate Governance", "qa_final_gate", preferenceManager)
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun QARoadmapCard(
    phase: String,
    title: String,
    preferenceManager: PreferenceManager,
    prefix: String,
    content: @Composable () -> Unit
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black, modifier = Modifier.weight(1f))
                Surface(
                    color = QAPrimary,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (phase == "FINAL") "FINAL PHASE" else "PHASE $phase",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            content()
        }
    }
}

@Composable
fun QASubSection(title: String, items: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Black, color = Color.Gray, letterSpacing = 1.sp)
    Spacer(modifier = Modifier.height(8.dp))
    items.forEach { item ->
        QACheckboxItem(item, "${prefix}_$item", preferenceManager)
    }
}

@Composable
fun QACheckboxItem(item: String, key: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = QAPrimary)
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
