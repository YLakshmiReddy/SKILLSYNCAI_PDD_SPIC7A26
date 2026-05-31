package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Hub
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

val K8sBgStart = Color(0xFFF1F4FF)
val K8sBgEnd = Color(0xFFFFFFFF)
val K8sPrimary = Color(0xFF326CE5) // Kubernetes Blue
val K8sDark = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KubernetesRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Hub, null, tint = K8sPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = K8sDark)
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
                .background(Brush.verticalGradient(listOf(K8sBgStart, K8sBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Kubernetes\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = K8sDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the container orchestration ecosystem from core concepts to advanced cluster operations.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Introduction & Setup
            K8sRoadmapCard(
                number = "1",
                title = "Introduction & Setup",
                items = listOf(
                    "Core Concepts" to listOf("Overview of Orchestration", "Kubernetes Terminology", "Container Alternatives"),
                    "Setting up Kubernetes" to listOf("Local Clusters (minikube/kind)", "Managed Providers (GKE/EKS/AKS)", "Initial Deployment")
                ),
                preferenceManager = preferenceManager,
                prefix = "k8s_p1"
            )

            // 2. Running Applications
            K8sRoadmapCard(
                number = "2",
                title = "Running Applications",
                items = listOf(
                    "Workload Resources" to listOf("Pods Lifecycle", "ReplicaSets & Deployments", "StatefulSets", "Jobs & CronJobs")
                ),
                preferenceManager = preferenceManager,
                prefix = "k8s_p2"
            )

            // 3. Networking & Config
            K8sRoadmapCard(
                number = "3",
                title = "Networking & Config",
                items = listOf(
                    "Services & Networking" to listOf("Internal Communication", "External Access & Ingress", "Load Balancing"),
                    "Configuration Mgmt" to listOf("ConfigMaps", "Secrets Management")
                ),
                preferenceManager = preferenceManager,
                prefix = "k8s_p3"
            )

            // 4. Resource & Storage
            K8sRoadmapCard(
                number = "4",
                title = "Resource & Storage",
                items = listOf(
                    "Resource Management" to listOf("CPU/Memory Requests & Limits", "Resource Quotas", "Cost Optimization"),
                    "Storage and Volumes" to listOf("CSI Drivers", "PersistentVolumes (PV/PVC)")
                ),
                preferenceManager = preferenceManager,
                prefix = "k8s_p4"
            )

            // 5. Security & Observability
            K8sRoadmapCard(
                number = "5",
                title = "Security & Observability",
                items = listOf(
                    "Security" to listOf("Role-Based Access Control (RBAC)", "Network Policies", "Pod Security Standards"),
                    "Monitoring & Logging" to listOf("Prometheus/Grafana Stack", "Liveness/Readiness Health Checks")
                ),
                preferenceManager = preferenceManager,
                prefix = "k8s_p5"
            )

            // 6. Advanced Operations
            K8sRoadmapCard(
                number = "6",
                title = "Advanced Operations",
                items = listOf(
                    "Scheduling & Autoscaling" to listOf("HPA/VPA/Cluster Autoscaler", "Taints, Tolerations, Affinities"),
                    "Cluster Operations" to listOf("GitOps & Deployment Tooling", "Custom Resource Definitions (CRDs)", "Helm & Kustomize")
                ),
                preferenceManager = preferenceManager,
                prefix = "k8s_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun K8sRoadmapCard(
    number: String,
    title: String,
    items: List<Pair<String, List<String>>>,
    preferenceManager: PreferenceManager,
    prefix: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = K8sPrimary)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "$number. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = K8sDark
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { (subTitle, subItems) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val subKey = "${prefix}_${subTitle}_check"
                    var subChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(subKey, false)) }
                    Checkbox(
                        checked = subChecked,
                        onCheckedChange = {
                            subChecked = it
                            preferenceManager.getSharedPrefs().edit().putBoolean(subKey, it).apply()
                        },
                        colors = CheckboxDefaults.colors(checkedColor = K8sPrimary)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = subTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                }
                
                Column(modifier = Modifier.padding(start = 44.dp, top = 8.dp, bottom = 12.dp)) {
                    subItems.forEach { item ->
                        Text(
                            text = "• $item",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
