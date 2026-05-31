package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Psychology
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

val AIEngineerBgStart = Color(0xFFF1F4FF)
val AIEngineerBgEnd = Color(0xFFFFFFFF)
val AIEngineerPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIEngineerScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = AIEngineerPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
                .background(Brush.verticalGradient(listOf(AIEngineerBgStart, AIEngineerBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "AI Engineer\nMastery Path",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = AIEngineerPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Navigate the stratified landscape of modern intelligence. From core LLM mechanics to advanced autonomous agent architectures.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Introduction
            AIEngineerRoadmapCard(
                phase = "01",
                title = "Introduction",
                icon = Icons.Default.Info,
                items = listOf(
                    "Basics of AI Engineering",
                    "Roles & Responsibilities",
                    "AI Agent basics",
                    "AI/ML Engineer Roadmap"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_intro"
            )

            // 2. Working with LLMs
            AIEngineerRoadmapCard(
                phase = "02",
                title = "Working with LLMs",
                icon = Icons.Default.Psychology,
                items = listOf(
                    "LLM Core Concepts",
                    "Sampling Parameters",
                    "Zero & Few-Shot",
                    "CoT & Chain-of-Thought"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_llm"
            )

            // 3. AI Models & Ecosystem
            AIEngineerRoadmapCard(
                phase = "03",
                title = "AI Models & Ecosystem",
                icon = Icons.Default.Hub,
                items = listOf(
                    "Proprietary Models (GPT-4, Claude)",
                    "Open-Source Models (Llama, Mistral)",
                    "Hugging Face Hub",
                    "Model Evaluation Metrics"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_models"
            )

            // 4. Embeddings & Vector Databases
            AIEngineerRoadmapCard(
                phase = "04",
                title = "Embeddings & Vector Databases",
                icon = Icons.Default.AutoAwesome,
                items = listOf(
                    "Introducing Vectors",
                    "Vector Storage basics",
                    "Pinecone & Milvus",
                    "ChromaDB & Weaviate"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_vector"
            )

            // 5. RAGs & AI Agents
            AIEngineerRoadmapCard(
                phase = "05",
                title = "RAGs & AI Agents",
                icon = Icons.Default.Hub,
                items = listOf(
                    "LangChain & LlamaIndex",
                    "Retrieval Augmented Generation",
                    "Autonomous Agent Workflows",
                    "Tool-Calling capabilities"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_rag"
            )

            // 6. Integration & Ethics
            AIEngineerRoadmapCard(
                phase = "06",
                title = "Integration & Ethics",
                icon = Icons.Default.Info,
                items = listOf(
                    "API Deployment",
                    "Scalable Serving",
                    "Bias & Fairness",
                    "Privacy & Data Policy"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_ethics"
            )

            // 7. Tools & Applications
            AIEngineerRoadmapCard(
                phase = "07",
                title = "Tools & Applications",
                icon = Icons.Default.AutoAwesome,
                items = listOf(
                    "Gradio & Streamlit",
                    "Docker for ML Models",
                    "Model Serving (vLLM, TGI)",
                    "Monitoring & Observability"
                ),
                preferenceManager = preferenceManager,
                prefix = "ai_tools"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AIEngineerRoadmapCard(
    phase: String,
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
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = AIEngineerPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "PHASE $phase",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = AIEngineerPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                AIEngineerCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun AIEngineerCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
                preferenceManager.getSharedPrefs().edit().apply {
                    putBoolean(key, isChecked)
                    apply()
                }
            },
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = AIEngineerPrimary)
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
