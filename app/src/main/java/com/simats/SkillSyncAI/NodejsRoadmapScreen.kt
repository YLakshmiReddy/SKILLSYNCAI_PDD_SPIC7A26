package com.simats.SkillSyncAI

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val NodeBgStart = Color(0xFFF7FDF9)
val NodeBgEnd = Color(0xFFFFFFFF)
val NodePrimary = Color(0xFF339933) // Node.js Green
val NodeDark = Color(0xFF215732)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NodejsRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Terminal, null, tint = NodePrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Node.js Architect", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = NodeDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = NodeDark)
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
                .background(Brush.verticalGradient(listOf(NodeBgStart, NodeBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Mastering the\nServer-Side",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = NodeDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "From basic runtime environment to advanced operations. Track your progress through the 6 core phases of Node.js mastery.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Introduction & Environment
            NodeRoadmapCard(
                phase = "1",
                title = "Introduction & Environment",
                items = listOf(
                    "The Basics" to "What is Node.js? History, Why use it, and Node.js vs. Browser differences.",
                    "Execution" to "Running Node.js code and using npx.",
                    "Package Management (npm)" to "Installing packages, Updating, running scripts, and Workspaces.",
                    "Semantic Versioning (SemVer)" to "",
                    "Modules" to "CommonJS (require) vs. ESM (import). Creating, importing modules, and (global)."
                ),
                preferenceManager = preferenceManager,
                prefix = "node_p1"
            )

            // 2. Asynchronous Programming
            NodeRoadmapCard(
                phase = "2",
                title = "Asynchronous Programming",
                items = listOf(
                    "The Runtime" to "The Event Loop (non-blocking I/O), Event Emitter (custom events).",
                    "Writing Async Code" to "Timer (setTimeout, process.nextTick), Flow Control (Promises, async/await).",
                    "Error Handling" to "System vs. User errors, Assertion, and Uncaught Exceptions.",
                    "Debugging" to "Monitoring callstacks/stack traces and using the debugger."
                ),
                preferenceManager = preferenceManager,
                prefix = "node_p2"
            )

            // 3. System & File Interactions
            NodeRoadmapCard(
                phase = "3",
                title = "System & File Interactions",
                items = listOf(
                    "Working with Files" to "Built-in modules (fs, path, process.cwd()), glob, fs-extra, chokidar.",
                    "Command Line Apps" to "Input (inquirer, prompt-ui), Output (chalk, figlet, cli-progress), Args (commander).",
                    "Environment Variables" to "Handling .env with dotenv."
                ),
                preferenceManager = preferenceManager,
                prefix = "node_p3"
            )

            // 4. Building & Consuming APIs
            NodeRoadmapCard(
                phase = "4",
                title = "Building & Consuming APIs",
                items = listOf(
                    "Web Frameworks" to "Express.js (Standard), Modern (Fastify, NestJS, Hono).",
                    "Communication" to "Making Calls (axios, fetch, got), Authentication (JWT, Passport.js).",
                    "Development Workflow" to "Monitoring (nodemon, --watch), Template Engines (EJS, Pug)."
                ),
                preferenceManager = preferenceManager,
                prefix = "node_p4"
            )

            // 5. Databases & Logic
            NodeRoadmapCard(
                phase = "5",
                title = "Databases & Logic",
                items = listOf(
                    "Relational (SQL)" to "ORM/Query Builders (Prisma, Drizzle, TypeORM, Knex).",
                    "NoSQL" to "Mongoose for MongoDB, Redis clients.",
                    "Advanced Logic" to "Threads (ChildProcess, Worker Threads), Streams."
                ),
                preferenceManager = preferenceManager,
                prefix = "node_p5"
            )

            // 6. Operations & Maintenance
            NodeRoadmapCard(
                phase = "6",
                title = "Operations & Maintenance",
                items = listOf(
                    "Testing" to "Unit/Integration (node:test, Vitest), E2E (Cypress).",
                    "Logging & Monitoring" to "Winston, Morgan.",
                    "Debugging & Process Management" to "Memory leaks, GC, node --inspect, pm2."
                ),
                preferenceManager = preferenceManager,
                prefix = "node_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun NodeRoadmapCard(
    phase: String,
    title: String,
    items: List<Pair<String, String>>,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFF43853D), // Node Primary
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(phase, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = NodeDark
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { (subTitle, desc) ->
                NodeSubItem(subTitle, desc, title, prefix, preferenceManager)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun NodeSubItem(subTitle: String, desc: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$subTitle"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.size(12.dp).padding(top = 8.dp)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2f
                )
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
            }
        }
        
        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = subTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (checked) Color.Gray else NodeDark,
                    modifier = Modifier.weight(1f)
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        checked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
                    },
                    modifier = Modifier.size(24.dp),
                    colors = CheckboxDefaults.colors(checkedColor = NodePrimary)
                )
            }
            if (desc.isNotEmpty()) {
                Text(
                    text = desc,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    lineHeight = 14.sp
                )
            }
        }
    }
}
