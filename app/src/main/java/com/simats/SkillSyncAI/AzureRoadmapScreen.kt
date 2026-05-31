package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cloud
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

val AzureBgStart = Color(0xFFF1F4FF)
val AzureBgEnd = Color(0xFFFFFFFF)
val AzurePrimary = Color(0xFF0078D4) // Azure Blue
val AzureDarkBlue = Color(0xFF005A9E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AzureRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Cloud, null, tint = AzurePrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AzureDarkBlue)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = AzureDarkBlue)
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
                .background(Brush.verticalGradient(listOf(AzureBgStart, AzureBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Surface(
                color = AzurePrimary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Text(
                    text = "CLOUD LEARNING PATH",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzurePrimary
                )
            }

            Text(
                text = "Microsoft Azure\nDeveloper\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = AzureDarkBlue,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Become a cloud expert with Microsoft Azure. Track your progress across core services, identity, security, and enterprise solutions.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            AzureRoadmapCard(
                phase = "01",
                title = "Azure Fundamentals",
                description = "Core cloud concepts and global infrastructure.",
                items = listOf("Cloud Computing (IaaS, PaaS, SaaS)", "Azure Regions & Availability Zones", "Azure Portal & Resource Manager", "Subscriptions & Resource Groups"),
                preferenceManager = preferenceManager,
                prefix = "az_p1"
            )

            // Phase 02
            AzureRoadmapCard(
                phase = "02",
                title = "Compute & Networking",
                description = "Hosting apps and secure connectivity.",
                items = listOf("Virtual Machines (VMs)", "Azure App Service", "Virtual Networks (VNet) & Subnets", "Azure DNS & Load Balancer", "VPN Gateway & ExpressRoute"),
                preferenceManager = preferenceManager,
                prefix = "az_p2"
            )

            // Phase 03
            AzureRoadmapCard(
                phase = "03",
                title = "Identity & Security",
                description = "Managing users and protecting resources.",
                items = listOf("Microsoft Entra ID (Azure AD)", "RBAC & Conditional Access", "Azure Key Vault", "Microsoft Defender for Cloud", "Network Security Groups (NSG)"),
                preferenceManager = preferenceManager,
                prefix = "az_p3"
            )

            // Phase 04
            AzureRoadmapCard(
                phase = "04",
                title = "Data & Storage",
                description = "Scalable data solutions.",
                items = listOf("Azure Blob Storage", "Azure SQL Database", "Cosmos DB (NoSQL)", "Azure Files & Disk Storage"),
                preferenceManager = preferenceManager,
                prefix = "az_p4"
            )

            // Phase 05
            AzureRoadmapCard(
                phase = "05",
                title = "Containers & Serverless",
                description = "Modern application architectures.",
                items = listOf("Azure Kubernetes Service (AKS)", "Azure Container Instances (ACI)", "Azure Functions", "Logic Apps & Event Grid"),
                preferenceManager = preferenceManager,
                prefix = "az_p5"
            )

            // Phase 06
            AzureRoadmapCard(
                phase = "06",
                title = "Governance & Operations",
                description = "Cost management and observability.",
                items = listOf("Azure Monitor & Log Analytics", "Azure Policy & Blueprints", "Cost Management & Billing", "Azure Advisor"),
                preferenceManager = preferenceManager,
                prefix = "az_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AzureRoadmapCard(
    phase: String,
    title: String,
    description: String,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String
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
            Text(
                text = "Phase $phase",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = AzureDarkBlue
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { item ->
                AzureCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun AzureCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = AzurePrimary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else Color.Black,
            lineHeight = 20.sp
        )
    }
}
