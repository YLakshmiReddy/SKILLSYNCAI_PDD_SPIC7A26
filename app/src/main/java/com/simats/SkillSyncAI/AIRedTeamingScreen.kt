package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Security
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

val RedTeamBgStart = Color(0xFFF1F4FF)
val RedTeamBgEnd = Color(0xFFFFFFFF)
val RedTeamPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIRedTeamingScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Security, null, tint = RedTeamPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("RedTeam AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = RedTeamPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = RedTeamPrimary)
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
                .background(Brush.verticalGradient(listOf(RedTeamBgStart, RedTeamBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "The Red Teaming\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = RedTeamPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Strategic mastery of AI security, vulnerabilities, and adversarial defense protocols.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            RedTeamPhaseCard(
                phaseNum = "1",
                title = "Introduction & Foundational Knowledge",
                items = listOf(
                    "AI Security Fundamentals",
                    "Why Red Team AI?",
                    "The Role of Red Teams",
                    "Ethical Considerations",
                    "AI / ML Fundamentals (Paradigms, Architecture, Generative AI, Prompt Engineering)"
                ),
                preferenceManager = preferenceManager,
                prefix = "art_p1"
            )

            // Phase 02
            RedTeamPhaseCard(
                phaseNum = "2",
                title = "System Security & Vulnerabilities",
                items = listOf(
                    "Cybersecurity Principles (CIA Triad, Threat Modeling, Risk Management)",
                    "Prompt Hacking (Jailbreak, Prompt Injection, Safety Filter Bypasses)",
                    "Model Vulnerabilities (Weight Stealing, Model Extraction)"
                ),
                preferenceManager = preferenceManager,
                prefix = "art_p2"
            )

            // Phase 03
            RedTeamPhaseCard(
                phaseNum = "3",
                title = "System Security & Manipulation",
                items = listOf(
                    "Code and Infrastructure Security",
                    "Model Manipulation (Data Poisoning, Adversarial Examples, Model Inversion)"
                ),
                preferenceManager = preferenceManager,
                prefix = "art_p3"
            )

            // Phase 04
            RedTeamPhaseCard(
                phaseNum = "4",
                title = "Defensive Strategies & Testing",
                items = listOf(
                    "Defense Strategies (Adversarial Training, Robust Design)",
                    "Testing Methodologies (Black/White/Grey Box)"
                ),
                preferenceManager = preferenceManager,
                prefix = "art_p4"
            )

            // Phase 05
            RedTeamPhaseCard(
                phaseNum = "5",
                title = "Tools & Frameworks",
                items = listOf(
                    "The Toolbox (Testing Platforms, Monitoring)",
                    "Professional Engagement (CTFs, Community)",
                    "Certifications & Education"
                ),
                preferenceManager = preferenceManager,
                prefix = "art_p5"
            )

            // Phase 06
            RedTeamPhaseCard(
                phaseNum = "6",
                title = "Real-world Applications",
                items = listOf(
                    "Practical Implementation (LLM Testing, Agentic AI)",
                    "Looking Ahead (Emerging Threats)"
                ),
                preferenceManager = preferenceManager,
                prefix = "art_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun RedTeamPhaseCard(
    phaseNum: String,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Surface(
                    color = RedTeamPrimary,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = phaseNum, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = RedTeamPrimary,
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { item ->
                RedTeamCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun RedTeamCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().apply {
                    putBoolean(key, isChecked)
                    apply()
                }
            },
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = RedTeamPrimary)
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
