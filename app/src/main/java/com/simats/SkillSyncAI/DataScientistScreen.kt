package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
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

val DSBgStart = Color(0xFF5C6BC0)
val DSBgEnd = Color(0xFF1A237E)
val DSPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScientistScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = DSPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DSPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = DSPrimary)
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
                .background(Brush.verticalGradient(listOf(Color(0xFFE8EAF6), Color.White)))
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        brush = Brush.verticalGradient(listOf(DSBgStart, DSBgEnd)),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(
                        text = "AI & Data\nScientist Path.",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            lineHeight = 38.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "A structured, atmospheric journey through the foundational layers of artificial intelligence and data science.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Mathematics
            DSRoadmapCard(
                title = "Mathematics",
                subtitle = "Foundational Theory",
                icon = Icons.Default.Functions,
                items = listOf("Calculus Focus (Gradients)", "Linear Algebra & Tensors", "Optimization Algorithms"),
                preferenceManager = preferenceManager,
                prefix = "ds_p1"
            )

            // 2. Statistics
            DSRoadmapCard(
                title = "Statistics",
                subtitle = "Core Data Logic",
                icon = Icons.Default.BarChart,
                items = listOf(
                    "Basic Stats & Distributions",
                    "Testing & Sampling",
                    "Experimentation (A/B)",
                    "Bayesian Inference",
                    "Performance Metrics Deep-dive"
                ),
                preferenceManager = preferenceManager,
                prefix = "ds_p2"
            )

            // 3. Econometrics
            DSRoadmapCard(
                title = "Econometrics",
                subtitle = "Causal Inference",
                icon = Icons.Default.QueryStats,
                items = listOf("Foundations & Regressions", "Modeling & Time Series", "Causal Analysis"),
                preferenceManager = preferenceManager,
                prefix = "ds_p3"
            )

            // 4. Coding
            DSRoadmapCard(
                title = "Coding",
                subtitle = "Engineering Core",
                icon = Icons.Default.Code,
                items = listOf(
                    "Python for Data Science",
                    "R Language Basics",
                    "DSA Foundations",
                    "Advanced Database Skills (SQL/NoSQL)"
                ),
                preferenceManager = preferenceManager,
                prefix = "ds_p4"
            )

            // 5. EDA & Feature Engineering
            DSRoadmapCard(
                title = "EDA & Feature Engineering",
                subtitle = "Data Understanding",
                icon = Icons.Default.Search,
                items = listOf("Data Exploration Logic", "Feature Selection Techniques", "Handling Missing Data", "Tools & Visualization Libraries"),
                preferenceManager = preferenceManager,
                prefix = "ds_p5"
            )

            // 6. Machine Learning
            DSRoadmapCard(
                title = "Machine Learning",
                subtitle = "Predictive Power",
                icon = Icons.Default.Psychology,
                items = listOf(
                    "Classic ML Algorithms",
                    "Supervised vs Unsupervised",
                    "Ensemble Methods (XGBoost, LightGBM)",
                    "Deep Statistical Theory",
                    "Model Optimization"
                ),
                preferenceManager = preferenceManager,
                prefix = "ds_p6"
            )

            // 7. Deep Learning
            DSRoadmapCard(
                title = "Deep Learning",
                subtitle = "Neural Frontiers",
                icon = Icons.Default.Hub,
                items = listOf(
                    "Advanced Architectures (CNN/RNN)",
                    "Transformers & Attention Mechanisms",
                    "Computer Vision Basics",
                    "Research & Academic Resources"
                ),
                preferenceManager = preferenceManager,
                prefix = "ds_p7"
            )

            // 8. MLOps & Deployment
            DSRoadmapCard(
                title = "MLOps & Deployment",
                subtitle = "Production Ready",
                icon = Icons.Default.SettingsSuggest,
                items = listOf("Model Productionalization", "Model Monitoring", "Scalable Deployment (Kubernetes)"),
                preferenceManager = preferenceManager,
                prefix = "ds_p8"
            )

            // 9. Data Storytelling & Communication
            DSRoadmapCard(
                title = "Storytelling & Communication",
                subtitle = "Impact Delivery",
                icon = Icons.Default.HistoryEdu,
                items = listOf("Business Metric Translation", "Insight Visualization", "Executive Presentation", "Ethical AI Communication"),
                preferenceManager = preferenceManager,
                prefix = "ds_p9"
            )

            // 10. Generative AI & LLMs
            DSRoadmapCard(
                title = "Generative AI & LLMs",
                subtitle = "Modern Horizons",
                icon = Icons.Default.AutoAwesome,
                items = listOf("Prompt Engineering", "Fine-tuning Models", "RAG Architectures", "AI Agent Design"),
                preferenceManager = preferenceManager,
                prefix = "ds_p10"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DSRoadmapCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = DSPrimary, modifier = Modifier.size(24.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                    Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                DSCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun DSCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${title}_$item"
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                checked = isChecked
                preferenceManager.getSharedPrefs().edit().putBoolean(key, isChecked).apply()
            },
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = DSPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else Color(0xFF334155),
            lineHeight = 20.sp
        )
    }
}
