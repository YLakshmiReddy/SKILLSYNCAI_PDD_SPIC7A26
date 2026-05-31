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

val ReactBgStart = Color(0xFFF0F9FF)
val ReactBgEnd = Color(0xFFFFFFFF)
val ReactPrimary = Color(0xFF61DAFB) // React Blue
val ReactDark = Color(0xFF23272F)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReactRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Code, null, tint = ReactPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("React Architect", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ReactDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = ReactDark)
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
                .background(Brush.verticalGradient(listOf(ReactBgStart, ReactBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Master the\nModern React\nEcosystem",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = ReactDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the structural foundation of the modern web through this 7-phase atmospheric learning path.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Foundations & Setup
            ReactRoadmapCard(
                phase = "1",
                title = "Foundations & Setup",
                items = listOf("JSX basics", "Components & Props", "Rendering logic", "CRA vs Vite vs Next.js", "Folder Structure"),
                preferenceManager = preferenceManager,
                prefix = "re_p1"
            )

            // 2. Hooks & Logic
            ReactRoadmapCard(
                phase = "2",
                title = "Hooks & Logic",
                items = listOf("State: useState", "Side Effects: useEffect", "Ref: useRef", "Custom Hooks", "Rules of Hooks"),
                preferenceManager = preferenceManager,
                prefix = "re_p2"
            )

            // 3. Navigation
            ReactRoadmapCard(
                phase = "3",
                title = "Navigation",
                items = listOf("React Router basics", "Nested Routes", "Protected Routes", "Dynamic Routing"),
                preferenceManager = preferenceManager,
                prefix = "re_p3"
            )

            // 4. State & Styles
            ReactRoadmapCard(
                phase = "4",
                title = "State & Styles",
                items = listOf("Global State: Context API", "Redux Toolkit / Zustand", "Tailwind CSS", "CSS Modules"),
                preferenceManager = preferenceManager,
                prefix = "re_p4"
            )

            // 5. Data & UI
            ReactRoadmapCard(
                phase = "5",
                title = "Data & UI",
                items = listOf("Fetch / Axios", "React Query / SWR", "UI Libraries: MUI, Shadcn", "Formik / React Hook Form"),
                preferenceManager = preferenceManager,
                prefix = "re_p5"
            )

            // 6. Maintenance
            ReactRoadmapCard(
                phase = "6",
                title = "Maintenance",
                items = listOf("Unit Testing (Jest)", "Component Testing (RTL)", "Error Boundaries", "Profiling & Performance"),
                preferenceManager = preferenceManager,
                prefix = "re_p6"
            )

            // 7. Advanced Frameworks
            ReactRoadmapCard(
                phase = "7",
                title = "Advanced Frameworks",
                items = listOf("Next.js (App Router)", "SSR & SSG", "Server Components", "Vercel Deployment"),
                preferenceManager = preferenceManager,
                prefix = "re_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ReactRoadmapCard(
    phase: String,
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = ReactPrimary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(phase, color = ReactPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = ReactDark
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                ReactCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun ReactCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            modifier = Modifier.size(20.dp),
            colors = CheckboxDefaults.colors(checkedColor = ReactPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else ReactDark,
            modifier = Modifier.weight(1f)
        )
    }
}
