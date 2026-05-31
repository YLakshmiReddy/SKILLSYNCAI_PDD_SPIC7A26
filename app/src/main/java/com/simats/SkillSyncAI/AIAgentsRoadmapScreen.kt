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

val AIAgentsBgStart = Color(0xFFF1F4FF)
val AIAgentsBgEnd = Color(0xFFFFFFFF)
val AIAgentsPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIAgentsRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = AIAgentsPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AIAgentsPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = AIAgentsPrimary)
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
                .background(Brush.verticalGradient(listOf(AIAgentsBgStart, AIAgentsBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "AI Agents Learning\nRoadmap",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = AIAgentsPrimary,
                    lineHeight = 32.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "A structured 6-phase curriculum designed to take you from foundational AI principles to building, deploying, and securing sophisticated multi-agent systems.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            AIAgentsPhaseCard(
                phaseNum = "01",
                title = "LLM Fundamentals",
                items = listOf(
                    "Backend Dev (Python/Node.js)",
                    "CLI, Terminal & REST APIs",
                    "Transformer Architecture Basics",
                    "Tokenization & Context Windows",
                    "Generation Controls (Temperature, Top-p)",
                    "Model Families (Open vs Closed)"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_agents_p1"
            )

            // Phase 02
            AIAgentsPhaseCard(
                phaseNum = "02",
                title = "AI Agents 101",
                items = listOf(
                    "Defining Agentic Workflows",
                    "The Agent Loop (Perception/Action)",
                    "Reasoning Frameworks (CoT, ToT)",
                    "Advanced Prompt Engineering"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_agents_p2"
            )

            // Phase 03
            AIAgentsPhaseCard(
                phaseNum = "03",
                title = "Tools and Actions",
                items = listOf(
                    "Tool Definition Schemas",
                    "Model Context Protocol (MCP)",
                    "Function Calling & JSON outputs",
                    "Web Search & Code Execution",
                    "Database & File System Access"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_agents_p3"
            )

            // Phase 04
            AIAgentsPhaseCard(
                phaseNum = "04",
                title = "Memory & Architectures",
                items = listOf(
                    "Short-Term vs Long-Term Memory",
                    "RAG & Vector Databases",
                    "Summarization & Compression",
                    "Router & Planner-Executor Patterns",
                    "Multi-Agent System Design"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_agents_p4"
            )

            // Phase 05
            AIAgentsPhaseCard(
                phaseNum = "05",
                title = "Build, Test & Observe",
                items = listOf(
                    "LangChain, LangGraph & CrewAI",
                    "Data Integration (LlamaIndex)",
                    "Metrics: Success, Cost, Latency",
                    "Human in the Loop Workflows",
                    "LangSmith & Helicone Integration"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_agents_p5"
            )

            // Phase 06
            AIAgentsPhaseCard(
                phaseNum = "06",
                title = "Security & Ethics",
                items = listOf(
                    "Prompt Injection & Jailbreaks",
                    "Agentic Sandboxing & Isolation",
                    "PII Reduction & Privacy Compliance",
                    "Responsible Disclosure Protocols",
                    "Future Directions: AI Alignment"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_agents_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AIAgentsPhaseCard(
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "PHASE $phaseNum",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = AIAgentsPrimary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                AIAgentsCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun AIAgentsCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = AIAgentsPrimary)
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
