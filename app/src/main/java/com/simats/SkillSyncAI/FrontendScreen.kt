package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

val FrontendBgStart = Color(0xFFF1F4FF)
val FrontendBgEnd = Color(0xFFFFFFFF)
val FrontendPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrontendScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    
    // State to track checked items, loaded from SharedPreferences via PreferenceManager if extended, 
    // or handled locally here for simplicity. 
    // For persistence, we'll use preferenceManager to save/load.
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Frontend Roadmap", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Brush.verticalGradient(listOf(FrontendBgStart, FrontendBgEnd)))
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "FrontEnd",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = FrontendPrimary
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "The front end is the user interface of a website or app, where visitors to the site interact with the product.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            RoadmapCard(
                title = "Internet",
                items = listOf(
                    "How does the internet work?",
                    "What is HTTP?",
                    "Browsers and how they work",
                    "DNS and how it works",
                    "What is Domain Name?",
                    "What is hosting?"
                ),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "HTML",
                items = listOf(
                    "Learn the basics",
                    "Writing Semantic HTML",
                    "Forms and Validations",
                    "Conventions and Best Practices",
                    "SEO Basics"
                ),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "CSS",
                items = listOf(
                    "Learn the basics",
                    "Making Layouts",
                    "Flexbox",
                    "CSS Grid",
                    "Responsive Design"
                ),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "JavaScript",
                items = listOf(
                    "Syntax and Basic Construct",
                    "DOM Manipulation",
                    "Fetch API / Ajax",
                    "ES6+ and modular JS",
                    "Hoisting, Closure, Prototype"
                ),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "Version Control Systems",
                items = listOf("Basic Usage of Git", "GitHub / GitLab"),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "Web Security",
                items = listOf("HTTPS", "CORS", "Content Security Policy", "OWASP Risks"),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "Package Managers",
                items = listOf("npm", "yarn"),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "Pick a Framework",
                items = listOf("React.js", "Vue.js", "Angular", "Svelte"),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "CSS Frameworks",
                items = listOf("Tailwind CSS", "Bootstrap", "Material UI"),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "Testing",
                items = listOf("Unit Testing", "Integration Testing", "E2E Testing"),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "Type Checkers",
                items = listOf("TypeScript"),
                preferenceManager = preferenceManager
            )

            RoadmapCard(
                title = "SSR & SSG",
                items = listOf("Next.js", "Nuxt.js", "Gatsby"),
                preferenceManager = preferenceManager
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun RoadmapCard(title: String, items: List<String>, preferenceManager: PreferenceManager) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = FrontendPrimary
            )
            Spacer(modifier = Modifier.height(12.dp))
            items.forEach { item ->
                val key = "frontend_${title}_$item"
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
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFF1D4ED8))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item,
                        fontSize = 14.sp,
                        color = if (checked) Color.Gray else Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
