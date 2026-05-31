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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val DevOpsBgStart = Color(0xFFF3F6FF)
val DevOpsBgEnd = Color(0xFFFFFFFF)
val DevOpsPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevOpsScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Terminal, null, tint = DevOpsPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("DevOps Flow", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DevOpsPrimary)
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
                .background(Brush.verticalGradient(listOf(DevOpsBgStart, DevOpsBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "DevOps\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = DevOpsPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Your path to mastering modern infrastructure and automation. Strategically architected for engineers who demand depth.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Fundamentals
            DevOpsRoadmapCard(
                title = "Fundamentals",
                description = "The core pillars of the DevOps engineer's toolkit.",
                content = {
                    SubSection("PROGRAMMING & OS", listOf("Python, Go, JS, Rust", "Unix/Linux, Windows"), preferenceManager, "do_fund_os")
                    Spacer(modifier = Modifier.height(16.dp))
                    SubSection("TERMINAL & TOOLS", listOf("Bash, PowerShell, Vim", "Networking, Monitoring Tools"), preferenceManager, "do_fund_tools")
                }
            )

            // 2. VCS & Network
            DevOpsRoadmapCard(
                title = "VCS & Network",
                content = {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        SkillTag("Git")
                        SkillTag("GitHub")
                        SkillTag("Nginx")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    SubSection("", listOf("DNS, HTTP, SSL/TLS", "SSH & OSI Model", "Apache & Caddy"), preferenceManager, "do_vcs")
                }
            )

            // 3. IaC & Deployment
            DevOpsRoadmapCard(
                title = "IaC & Deployment",
                description = "Mastering the art of immutable infrastructure and seamless continuous delivery pipelines.",
                content = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            SubSection("CONTAINERS", listOf("Docker", "LXC", "Podman"), preferenceManager, "do_iac_cont")
                            Spacer(modifier = Modifier.height(16.dp))
                            SubSection("PROVISIONING", listOf("Terraform", "CloudFormation", "Pulumi", "Ansible"), preferenceManager, "do_iac_prov")
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            SubSection("CLOUD", listOf("AWS / GCP", "Azure", "DigitalOcean"), preferenceManager, "do_iac_cloud")
                            Spacer(modifier = Modifier.height(16.dp))
                            SubSection("CI/CD", listOf("Jenkins", "GitHub Actions", "GitLab CI", "CircleCI"), preferenceManager, "do_iac_cicd")
                        }
                    }
                }
            )

            // 4. Advanced Orchestration
            DevOpsRoadmapCard(
                title = "Advanced Orchestration",
                content = {
                    Surface(
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("CONTAINER PLATFORMS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = DevOpsPrimary, modifier = Modifier.weight(1f))
                                Icon(Icons.Default.AutoAwesome, null, tint = DevOpsPrimary, modifier = Modifier.size(16.dp))
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.weight(1f)) {
                                    ProgressItem("Kubernetes", "do_adv_k8s", preferenceManager)
                                    ProgressItem("Service Mesh (Linkerd)", "do_adv_mesh", preferenceManager)
                                    ProgressItem("Helm Charts", "do_adv_helm", preferenceManager)
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    ProgressItem("Docker Swarm", "do_adv_swarm", preferenceManager)
                                    ProgressItem("Istio / Consul", "do_adv_istio", preferenceManager)
                                    ProgressItem("ArgoCD", "do_adv_argo", preferenceManager)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        BottomActionItem("GITOPS", Icons.Default.Storage)
                        BottomActionItem("SERVERLESS", Icons.Default.CloudQueue)
                        BottomActionItem("K8S ADMIN", Icons.Default.Terminal)
                    }
                }
            )

            // 5. Cloud & Virtualization (New)
            DevOpsRoadmapCard(
                title = "Cloud & Virtualization",
                description = "Modern cloud paradigms and compute resources.",
                content = {
                    SubSection("CLOUD SERVICES", listOf("IAM & Security", "VPC & Networking", "Compute (EC2, Lambda)", "Storage (S3, EBS)"), preferenceManager, "do_cloud_serv")
                    Spacer(modifier = Modifier.height(16.dp))
                    SubSection("VIRTUALIZATION", listOf("Hypervisors (KVM, ESXi)", "Vagrant", "VirtualBox"), preferenceManager, "do_virt_tools")
                }
            )

            // 6. Observability & Security
            DevOpsRoadmapCard(
                title = "Observability & Security",
                content = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.MonitorHeart, null, tint = DevOpsPrimary, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Monitoring", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            ProgressItem("Prometheus", "do_obs_prom", preferenceManager)
                            ProgressItem("Grafana", "do_obs_graf", preferenceManager)
                            ProgressItem("Datadog", "do_obs_data", preferenceManager)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Storage, null, tint = DevOpsPrimary, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Logging", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            ProgressItem("ELK Stack", "do_obs_elk", preferenceManager)
                            ProgressItem("Loki", "do_obs_loki", preferenceManager)
                            ProgressItem("CloudWatch", "do_obs_cloud", preferenceManager)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(16.dp))
                    SubSection("SECURITY & COMPLIANCE", listOf("HashiCorp Vault", "SonarQube", "Snyk", "Aqua Security", "OpenTelemetry"), preferenceManager, "do_sec_comp")
                }
            )

            // 7. SRE & Incident Management (New)
            DevOpsRoadmapCard(
                title = "SRE & Incident Management",
                description = "Ensuring reliability and rapid response.",
                content = {
                    SubSection("RELIABILITY", listOf("SLIs, SLOs, SLAs", "Error Budgets", "Chaos Engineering"), preferenceManager, "do_sre_rel")
                    Spacer(modifier = Modifier.height(16.dp))
                    SubSection("INCIDENT RESPONSE", listOf("PagerDuty", "Incident Lifecycle", "Post-mortems"), preferenceManager, "do_sre_inc")
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DevOpsRoadmapCard(title: String, description: String? = null, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Removed the Checkbox from the main heading row
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
            
            if (description != null) {
                Text(text = description, fontSize = 13.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            content()
        }
    }
}

@Composable
fun SubSection(title: String, items: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    if (title.isNotEmpty()) {
        Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Black, color = DevOpsPrimary, letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(8.dp))
    }
    items.forEach { item ->
        ProgressItem(item, "${prefix}_$item", preferenceManager)
    }
}

@Composable
fun ProgressItem(text: String, key: String, preferenceManager: PreferenceManager, modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(key, false)) }
    Row(
        modifier = modifier.padding(vertical = 2.dp),
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
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = DevOpsPrimary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 13.sp, color = if (checked) Color.Gray else Color.DarkGray)
    }
}

@Composable
fun SkillTag(text: String) {
    Surface(
        color = Color(0xFFE8EAF6),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = text, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 11.sp, color = DevOpsPrimary, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BottomActionItem(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            color = Color(0xFFF1F5F9),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = Color.Gray, modifier = Modifier.size(20.dp))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
    }
}
