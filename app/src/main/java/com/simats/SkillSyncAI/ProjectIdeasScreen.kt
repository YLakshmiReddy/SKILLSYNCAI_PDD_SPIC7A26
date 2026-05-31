package com.simats.SkillSyncAI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProjectIdeasScreen(
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val primaryBlue = Color(0xFF1D4ED8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = primaryBlue,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "SkillSync AI",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Project Ideas",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A)
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Apply your knowledge with AI-suggested projects that challenge your current skill level and build your portfolio.",
                color = Color.Gray,
                lineHeight = 22.sp,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Easy Projects Section
            CategoryHeader("EASY PROJECTS", "10 PROJECTS")
            HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp), color = Color.LightGray.copy(alpha = 0.5f))
            
            SimpleProjectItem("Portfolio Website", "Personal Portfolio")
            SimpleProjectItem("Todo List App", "Task Management")
            SimpleProjectItem("Weather Dashboard", "API Integration")
            SimpleProjectItem("Unit Converter", "Basic Logic")
            SimpleProjectItem("Simple Blog", "Content Management")
            SimpleProjectItem("Recipe Book", "Data Display")
            SimpleProjectItem("Calculater App", "Arithmetic Logic")
            SimpleProjectItem("Notes App", "Data Storage")
            SimpleProjectItem("Tribute Page", "Static HTML/CSS")
            SimpleProjectItem("Random Quote Machine", "API Fetching")

            Spacer(modifier = Modifier.height(40.dp))

            // Medium Projects Section
            CategoryHeader("MEDIUM PROJECTS", "12 PROJECTS")
            HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp), color = Color.LightGray.copy(alpha = 0.5f))

            MediumProjectItem(
                title = "AI Content Generator",
                skills = listOf("OpenAI API", "Prompt Eng.", "Next.js")
            )
            MediumProjectItem(
                title = "Crypto Price Tracker",
                skills = listOf("CoinGecko API", "Charts", "Real-time")
            )
            MediumProjectItem(
                title = "E-commerce Store Front",
                skills = listOf("React", "Stripe SDK", "Auth")
            )
            MediumProjectItem(
                title = "Movie Database App",
                skills = listOf("TMDB API", "Filtering", "Pagination")
            )
            MediumProjectItem(
                title = "Expense Tracker",
                skills = listOf("LocalStorage", "Charts", "Redux")
            )
            MediumProjectItem(
                title = "Job Board UI",
                skills = listOf("Tailwind CSS", "Search", "Forms")
            )
            MediumProjectItem(
                title = "Fitness Tracker",
                skills = listOf("Data Persistence", "Goals", "Icons")
            )
            MediumProjectItem(
                title = "Quiz App",
                skills = listOf("Logic", "Timer", "Results UI")
            )
            MediumProjectItem(
                title = "Markdown Previewer",
                skills = listOf("Text Parsing", "UI Sync", "React")
            )
            MediumProjectItem(
                title = "Pomodoro Timer",
                skills = listOf("Timing Logic", "Notifications", "Settings")
            )
            MediumProjectItem(
                title = "Chat Application UI",
                skills = listOf("Layouts", "Animations", "State")
            )
            MediumProjectItem(
                title = "URL Shortener",
                skills = listOf("API", "Forms", "Clipboard")
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Difficult Projects Section
            CategoryHeader("DIFFICULT PROJECTS", "8 PROJECTS")
            HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp), color = Color.LightGray.copy(alpha = 0.5f))

            DifficultProjectCard(
                title = "AI Engineer / Autonomous Agents",
                description = "Build a multi-agent system that can solve complex tasks using LLMs and tool-calling capabilities.",
                tasks = listOf("Agentic Workflow design", "LLM tool integration", "Auto-debugging layer")
            )
            
            DifficultProjectCard(
                title = "SaaS Platform Dashboard",
                description = "A complete multi-tenant dashboard with subscription management, analytics, and team collaboration.",
                tasks = listOf("Multi-tenancy setup", "Billing integration", "Analytics pipeline")
            )

            DifficultProjectCard(
                title = "Blockchain Wallet",
                description = "Secure cryptocurrency wallet with transaction signing, private key management, and explorer integration.",
                tasks = listOf("Key encryption", "Transaction logic", "Network sync")
            )

            DifficultProjectCard(
                title = "Real-time Analytics Engine",
                description = "Process millions of events per second and visualize insights in real-time with sub-second latency.",
                tasks = listOf("Data ingestion", "Stream processing", "Real-time dashboard")
            )

            DifficultProjectCard(
                title = "Recommendation Engine",
                description = "Build a collaborative filtering engine that suggests items based on user behavior and similarity.",
                tasks = listOf("Data collection", "Algorithm implementation", "API delivery")
            )

            DifficultProjectCard(
                title = "IoT Management Suite",
                description = "Manage thousands of connected devices, monitor telemetry, and trigger automated responses.",
                tasks = listOf("Device protocols", "Edge computing", "Alert system")
            )
            
            DifficultProjectCard(
                title = "Search Engine Indexer",
                description = "Build a distributed crawler and indexer that can process and rank web pages for search queries.",
                tasks = listOf("Web crawling", "Indexing logic", "Ranking algorithm")
            )
            
            DifficultProjectCard(
                title = "Voice Controlled Assistant",
                description = "Develop a system that interprets voice commands to control smart home devices or execute scripts.",
                tasks = listOf("Speech recognition", "NLP intent parsing", "Hardware/API integration")
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun CategoryHeader(title: String, count: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Black,
            fontSize = 18.sp,
            color = Color(0xFF0F172A)
        )
        Text(
            text = count,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D4ED8)
        )
    }
}

@Composable
fun SimpleProjectItem(name: String, tag: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(32.dp),
            color = Color(0xFFF1F5F9),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                Icons.Default.Terminal,
                contentDescription = null,
                modifier = Modifier.padding(6.dp),
                tint = Color(0xFF1D4ED8)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(tag, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun MediumProjectItem(title: String, skills: List<String>) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1D4ED8))
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.forEach { skill ->
                Surface(
                    color = Color.White,
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = skill,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 10.sp,
                        color = Color.DarkGray,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun DifficultProjectCard(title: String, description: String, tasks: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Surface(
                modifier = Modifier.size(36.dp),
                color = Color(0xFFDBEAFE),
                shape = CircleShape
            ) {
                Icon(
                    Icons.Default.RocketLaunch,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = Color(0xFF1D4ED8)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(title, fontWeight = FontWeight.Black, fontSize = 20.sp, color = Color(0xFF0F172A))
            Spacer(modifier = Modifier.height(8.dp))
            Text(description, fontSize = 14.sp, color = Color.Gray, lineHeight = 20.sp)
            
            Spacer(modifier = Modifier.height(20.dp))
            
            tasks.forEach { task ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    color = Color(0xFFF8FAFC),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(task, modifier = Modifier.weight(1f), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}
