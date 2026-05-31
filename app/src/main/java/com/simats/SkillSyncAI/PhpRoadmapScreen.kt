package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Code
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

val PhpBgStart = Color(0xFFF1F4FF)
val PhpBgEnd = Color(0xFFFFFFFF)
val PhpPrimary = Color(0xFF4F5B93) // PHP Blue/Indigo
val PhpDark = Color(0xFF232F3E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhpRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Code, null, tint = PhpPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("PHP ROADMAP", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PhpPrimary)
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
                .background(Brush.verticalGradient(listOf(PhpBgStart, PhpBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            // Header Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = PhpPrimary),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Mastering the\nModern PHP\nStack",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            lineHeight = 38.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "A stratified journey through PHP development, from foundational syntax to high-performance enterprise architecture. Use the checklists below to track your progress through each module.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(alpha = 0.8f),
                            lineHeight = 20.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            PhpRoadmapCard(
                phase = "01",
                title = "Introduction & Environment",
                items = listOf(
                    "Foundational Knowledge" to "PHP ecosystem, historical context, evolution of the engine.",
                    "Development Environment" to "Local server setup (XAMPP/MAMP), Docker, CLI configuration."
                ),
                preferenceManager = preferenceManager,
                prefix = "php_p1"
            )

            // Phase 2
            PhpRoadmapCard(
                phase = "02",
                title = "Learn the Fundamentals",
                items = listOf(
                    "Basic Syntax" to "Variables, Data Types, Operators.",
                    "Control Flow" to "Conditionals, Loops, match (PHP 8).",
                    "Functions" to "Standard and Anonymous functions, Type hinting.",
                    "Error Handling" to "Try-catch, Exceptions, Error levels."
                ),
                preferenceManager = preferenceManager,
                prefix = "php_p2"
            )

            // Phase 3
            PhpRoadmapCard(
                phase = "03",
                title = "Files and HTTP Handling",
                items = listOf(
                    "File Operations" to "Read/Write, permissions, file uploads.",
                    "Request Handling" to "GET/POST, Superglobals (\$_SERVER, \$_SESSION).",
                    "State Management" to "Cookies and secure Sessions."
                ),
                preferenceManager = preferenceManager,
                prefix = "php_p3"
            )

            // Phase 4
            PhpRoadmapCard(
                phase = "04",
                title = "Working with Databases & Security",
                items = listOf(
                    "Database Operations" to "PDO & MySQLi, basic SQL integrations.",
                    "Security Basics" to "SQL injection prevention, XSS & CSRF protection, Password hashing."
                ),
                preferenceManager = preferenceManager,
                prefix = "php_p4"
            )

            // Phase 5
            PhpRoadmapCard(
                phase = "05",
                title = "Object-Oriented (OOP)",
                items = listOf(
                    "Classes & Interfaces" to "Properties, methods, visibility.",
                    "Inheritance" to "Parent-child relationships, abstract classes.",
                    "Advanced Traits" to "Traits, Magic Methods, Overloading."
                ),
                preferenceManager = preferenceManager,
                prefix = "php_p5"
            )

            // Phase 6
            PhpRoadmapCard(
                phase = "06",
                title = "Frameworks & Tools",
                items = listOf(
                    "Composer" to "Dependency management.",
                    "Laravel / Symfony" to "MVC Architecture and routing.",
                    "PHPUnit" to "Unit and Feature testing."
                ),
                preferenceManager = preferenceManager,
                prefix = "php_p6"
            )

            // Phase 7
            PhpRoadmapCard(
                phase = "07",
                title = "Performance & Ops",
                items = listOf(
                    "Cache Analysis" to "OPcache and Object Caching.",
                    "Server Config" to "PHP-FPM, Nginx, Caching proxies.",
                    "Stateless & Scaling" to "Serverless and distributed systems."
                ),
                preferenceManager = preferenceManager,
                prefix = "php_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PhpRoadmapCard(
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
                    color = Color(0xFFE0E7FF),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(phase, color = PhpPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { (subTitle, desc) ->
                val key = "${prefix}_${title}_$subTitle"
                var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFF).copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { isChecked ->
                                checked = isChecked
                                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
                            },
                            colors = CheckboxDefaults.colors(checkedColor = PhpPrimary)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = subTitle, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = if (checked) Color.Gray else Color.Black)
                            Text(text = desc, fontSize = 11.sp, color = Color.Gray, lineHeight = 14.sp)
                        }
                    }
                }
            }
        }
    }
}
