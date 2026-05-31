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

val MLOpsBgStart = Color(0xFFF1F4FF)
val MLOpsBgEnd = Color(0xFFFFFFFF)
val MLOpsPrimary = Color(0xFF1A237E)
val MLOpsDarkCardBg = Color(0xFF1A1C4E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MLOpsScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = MLOpsPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MLOpsPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MLOpsPrimary)
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
                .background(Brush.verticalGradient(listOf(MLOpsBgStart, MLOpsBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "MLOps Roadmap",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MLOpsPrimary
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Detailed path to creating reliable systems for deploying and maintaining models.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01 - Introduction & Principles (Dark Card)
            MLOpsDarkPhaseCard(
                phase = "01",
                title = "Introduction & Principles",
                icon = Icons.Default.CheckCircle,
                items = listOf(
                    "What is MLOps?" to "Automation & Unification of ML systems",
                    "MLOps Principles" to "Automation, Versioning, Testing, Monitoring",
                    "Key Components" to "CI/CD, Orchestration, Tracking, Serving"
                ),
                preferenceManager = preferenceManager,
                prefix = "mlops_p1"
            )

            // Phase 02 - Foundations
            MLOpsPhaseCard(
                phase = "02",
                title = "Foundations",
                icon = Icons.Default.Code,
                content = {
                    MLOpsSubSection("Programming", listOf("Python", "Bash", "SQL", "Go"), preferenceManager, "mlops_p2_prog")
                    Spacer(modifier = Modifier.height(16.dp))
                    MLOpsItem("Version Control", "Git, GitHub, DVC (Data Version Control)", "mlops_p2_vc", preferenceManager)
                    MLOpsItem("ML Fundamentals", "Theory, Scikit-learn, TensorFlow, PyTorch", "mlops_p2_ml", preferenceManager)
                }
            )

            // Phase 03 - Infrastructure & Automation
            MLOpsPhaseCard(
                phase = "03",
                title = "Infrastructure & Automation",
                icon = Icons.Default.CloudQueue,
                items = listOf(
                    "CI/CD Tools" to "GitHub Actions, GitLab, CML",
                    "Cloud Computing" to "AWS, Azure, GCP, SageMaker",
                    "IaC & Containers" to "Terraform, Ansible, Docker, Kubernetes"
                ),
                preferenceManager = preferenceManager,
                prefix = "mlops_p3"
            )

            // Phase 04 - Data Engineering
            MLOpsPhaseCard(
                phase = "04",
                title = "Data Engineering",
                icon = Icons.Default.Storage,
                items = listOf(
                    "Architectures" to "Pipelines, Lakes, Warehouses",
                    "Big Data Tools" to "Spark, Kafka, Flink"
                ),
                preferenceManager = preferenceManager,
                prefix = "mlops_p4"
            )

            // Phase 05 - Model Deployment & Serving
            MLOpsPhaseCard(
                phase = "05",
                title = "Model Deployment & Serving",
                icon = Icons.Default.Launch,
                items = listOf(
                    "Serving Frameworks" to "BentoML, TFServing, TorchServe",
                    "Model Formats" to "ONNX, PMML, Pickle",
                    "Batch vs Real-time" to "Scheduling & Inference latency"
                ),
                preferenceManager = preferenceManager,
                prefix = "mlops_p5"
            )

            // Phase 06 - Orchestration & Pipelines
            MLOpsPhaseCard(
                phase = "06",
                title = "Orchestration & Pipelines",
                icon = Icons.Default.AccountTree,
                items = listOf(
                    "Workflow Management" to "Airflow, KubeFlow, Prefect",
                    "Feature Stores" to "Feast, Hopsworks",
                    "Experiment Tracking" to "MLflow, Weights & Biases"
                ),
                preferenceManager = preferenceManager,
                prefix = "mlops_p6"
            )

            // Phase 07 - Monitoring & Observability
            MLOpsPhaseCard(
                phase = "07",
                title = "Monitoring & Observability",
                icon = Icons.Default.MonitorHeart,
                items = listOf(
                    "Model Drift Detection" to "Concept & Data Drift",
                    "Metrics & Dashboards" to "Prometheus, Grafana, Evidently",
                    "Logging & Tracing" to "ELK Stack, Jaeger"
                ),
                preferenceManager = preferenceManager,
                prefix = "mlops_p7"
            )

            // Phase 08 - Advanced & Specialized ML
            MLOpsPhaseCard(
                phase = "08",
                title = "Advanced & Specialized ML",
                icon = Icons.Default.Psychology,
                items = listOf(
                    "Edge AI" to "TFLite, PyTorch Mobile, NVIDIA Jetson",
                    "Explainable AI (XAI)" to "LIME, SHAP, Captum",
                    "Security & Compliance" to "Adversarial attacks, GDPR, Model Governance"
                ),
                preferenceManager = preferenceManager,
                prefix = "mlops_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun MLOpsDarkPhaseCard(
    phase: String,
    title: String,
    icon: ImageVector,
    items: List<Pair<String, String>>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = MLOpsDarkCardBg),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("PHASE $phase", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White.copy(alpha = 0.6f))
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                }
                Icon(icon, null, tint = Color.White, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { (name, desc) ->
                MLOpsItem(name, desc, "${prefix}_$name", preferenceManager, isDark = true)
            }
        }
    }
}

@Composable
fun MLOpsPhaseCard(
    phase: String,
    title: String,
    icon: ImageVector,
    items: List<Pair<String, String>>? = null,
    content: @Composable (() -> Unit)? = null,
    preferenceManager: PreferenceManager? = null,
    prefix: String? = null
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
                    Text("PHASE $phase", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                }
                Icon(icon, null, tint = MLOpsPrimary.copy(alpha = 0.3f), modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            
            if (items != null && preferenceManager != null && prefix != null) {
                items.forEach { (name, desc) ->
                    MLOpsItem(name, desc, "${prefix}_$name", preferenceManager)
                }
            }
            
            content?.invoke()
        }
    }
}

@Composable
fun MLOpsSubSection(title: String, tags: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(prefix, false)) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked,
                onCheckedChange = { 
                    checked = it
                    preferenceManager.getSharedPrefs().edit().apply {
                        putBoolean(prefix, it)
                        apply()
                    }
                },
                colors = CheckboxDefaults.colors(checkedColor = MLOpsPrimary)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = if (checked) Color.Gray else Color.Black)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.padding(start = 40.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            tags.forEach { tag ->
                Surface(
                    color = Color(0xFFF1F3F4),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = tag.uppercase(),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun MLOpsItem(name: String, desc: String, key: String, preferenceManager: PreferenceManager, isDark: Boolean = false) {
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { 
                checked = it
                preferenceManager.getSharedPrefs().edit().apply {
                    putBoolean(key, it)
                    apply()
                }
            },
            colors = CheckboxDefaults.colors(
                checkedColor = if (isDark) Color.White else MLOpsPrimary,
                checkmarkColor = if (isDark) MLOpsDarkCardBg else Color.White,
                uncheckedColor = if (isDark) Color.White.copy(alpha = 0.4f) else Color.Gray
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) Color.White.copy(alpha = if (checked) 0.5f else 1f) else (if (checked) Color.Gray else Color.Black)
            )
            Text(
                text = desc,
                fontSize = 12.sp,
                color = if (isDark) Color.White.copy(alpha = 0.6f) else Color.Gray,
                lineHeight = 16.sp
            )
        }
    }
}
