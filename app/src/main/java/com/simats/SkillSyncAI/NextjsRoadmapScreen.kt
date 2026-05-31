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

val NextBgStart = Color(0xFFF8F9FF)
val NextBgEnd = Color(0xFFFFFFFF)
val NextPrimary = Color(0xFF000000) // Next.js Black
val NextBlue = Color(0xFF1E3A8A) // Accent Blue
val NextDark = Color(0xFF111827)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextjsRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Menu, null, tint = NextBlue, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("NEXT.JS ROADMAP", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NextBlue)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = NextDark)
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
                .background(Brush.verticalGradient(listOf(NextBgStart, NextBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            // Header
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9).copy(alpha = 0.5f)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Mastering the\nModern Web",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = NextBlue,
                            lineHeight = 38.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Follow this curated curriculum to transition from React fundamentals to advanced Next.js architecture.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray,
                            lineHeight = 22.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 01. Introduction & Prerequisites
            NextRoadmapCard(
                phase = "01",
                title = "Introduction & Prerequisites",
                items = listOf(
                    "JavaScript Basics" to "ES6+ syntax, async programming, DOM manipulation",
                    "Why Frontend Frameworks?" to "State management, component-based architecture",
                    "React Fundamentals" to "Declarative UI, Virtual DOM",
                    "React Frameworks" to "Next, Gatsby, Remix",
                    "Web Patterns" to "SPA vs SSR"
                ),
                preferenceManager = preferenceManager,
                prefix = "nxt_p1"
            )

            // 02. Getting Started
            NextRoadmapCard(
                phase = "02",
                title = "Getting Started",
                items = listOf(
                    "create-next-app" to "Scaffolding projects with recommended defaults",
                    "SSR & CSR" to "Server-side and Client-side rendering differences",
                    "SSG & ISR" to "Static Site Generation and Incremental Static Regeneration"
                ),
                preferenceManager = preferenceManager,
                prefix = "nxt_p2"
            )

            // 03. Routing (The App Router)
            NextRoadmapCard(
                phase = "03",
                title = "Routing (The App Router)",
                items = listOf(
                    "Next.js Routing Basics" to "Pages Router vs App Router",
                    "Terminology" to "Segments, Paths, Layouts",
                    "File-based Routing" to "index.js, layout.js, nested routes",
                    "Advanced Routing" to "Parallel routes, Intercepting routes",
                    "Routing Patterns" to "Loading and Error states"
                ),
                preferenceManager = preferenceManager,
                prefix = "nxt_p3"
            )

            // 04. Working with Data
            NextRoadmapCard(
                phase = "04",
                title = "Working with Data",
                items = listOf(
                    "Fetching & Mutating" to "Client-side vs Server-side paradigms",
                    "Server Actions" to "Handing mutations on server directly",
                    "Caching Data" to "Memorization, Revalidation strategies",
                    "Structuring Routes" to "API Endpoints, Middleware logic"
                ),
                preferenceManager = preferenceManager,
                prefix = "nxt_p4"
            )

            // 05. Optimizations & Styling
            NextRoadmapCard(
                phase = "05",
                title = "Optimizations & Styling",
                items = listOf(
                    "Writing CSS" to "CSS Modules, Tailwind CSS, CSS-in-JS",
                    "Built-in Optimizations" to "Image, Script, Font, Metadata API",
                    "Performance" to "Lazy loading, Code splitting, Package bundling",
                    "Runtimes" to "Node.js vs Edge runtimes"
                ),
                preferenceManager = preferenceManager,
                prefix = "nxt_p5"
            )

            // 06. Deployment & Testing
            NextRoadmapCard(
                phase = "06",
                title = "Deployment & Testing",
                items = listOf(
                    "Configuring for Production" to "Environment variables, Custom Server",
                    "Testing Frameworks" to "Vitest, Jest, Playwright, Cypress",
                    "Deployment Options" to "Vercel, Self-hosting (Docker/Node.js)"
                ),
                preferenceManager = preferenceManager,
                prefix = "nxt_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun NextRoadmapCard(
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
                    color = NextBlue,
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
                    color = NextDark
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { (subTitle, desc) ->
                NextSubItem(subTitle, desc, title, prefix, preferenceManager)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun NextSubItem(subTitle: String, desc: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$subTitle"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(modifier = Modifier.fillMaxWidth()) {
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
            },
            modifier = Modifier.size(24.dp).padding(top = 2.dp),
            colors = CheckboxDefaults.colors(checkedColor = NextBlue)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = subTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (checked) Color.Gray else NextDark
            )
            Text(
                text = desc,
                fontSize = 11.sp,
                color = Color.Gray,
                lineHeight = 14.sp
            )
        }
    }
}
