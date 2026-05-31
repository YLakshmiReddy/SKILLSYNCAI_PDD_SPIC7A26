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

val TerraformBgStart = Color(0xFFF9FAFF)
val TerraformBgEnd = Color(0xFFFFFFFF)
val TerraformPrimary = Color(0xFF7B42BC) // Terraform Purple
val TerraformDark = Color(0xFF1E1B4B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerraformRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CloudQueue, null, tint = TerraformPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Terraform IaC", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TerraformDark)
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
                .background(Brush.verticalGradient(listOf(TerraformBgStart, TerraformBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Surface(
                color = TerraformPrimary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "INFRASTRUCTURE AS CODE",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TerraformPrimary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Master\nInfrastructure\nAs Code",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the art of describing infrastructure as code and manage complex cloud architectures.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Intro to IaC
            TerraformCard(
                phase = "01",
                title = "Introduction to IaC",
                icon = Icons.Default.Layers,
                items = listOf("Core Concepts: IaC vs CaC", "State vs. Stateless", "Declarative vs Imperative"),
                preferenceManager = preferenceManager,
                prefix = "tf_p1"
            )

            // 2. HCL Basics
            TerraformCard(
                phase = "02",
                title = "HCL Syntax",
                icon = Icons.Default.Code,
                items = listOf("Syntax & Structures", "Resources & Data", "Providers & Provisioners", "Expressions"),
                preferenceManager = preferenceManager,
                prefix = "tf_p2"
            )

            // 3. Variables & State
            TerraformCard(
                phase = "03",
                title = "Variables & State",
                icon = Icons.Default.Storage,
                items = listOf("Variables (Input, Output)", "Local Management", "State Locking", "State with Backend Providers"),
                preferenceManager = preferenceManager,
                prefix = "tf_p3"
            )

            // 4. Deployment Lifecycle
            TerraformCard(
                phase = "04",
                title = "Deployment Lifecycle",
                icon = Icons.Default.Refresh,
                items = listOf("Init & Workspace", "Plan & Execution", "Refresh & Validation"),
                preferenceManager = preferenceManager,
                prefix = "tf_p4"
            )

            // 5. Scaling & Reusability
            TerraformCard(
                phase = "05",
                title = "Scaling & Reusability",
                icon = Icons.Default.StarOutline,
                items = listOf("Effective Modules", "Workspaces", "Loops (count, for_each)"),
                preferenceManager = preferenceManager,
                prefix = "tf_p5"
            )

            // 6. Testing & Security
            TerraformCard(
                phase = "06",
                title = "Testing & Security",
                icon = Icons.Default.Security,
                items = listOf("CI/CD Pipelines", "Testing Frameworks", "Securing your Code"),
                preferenceManager = preferenceManager,
                prefix = "tf_p6",
                isAlert = true
            )

            // 7. Enterprise Ops
            TerraformCard(
                phase = "07",
                title = "Enterprise Operations",
                icon = Icons.Default.Business,
                items = listOf("HCP Cloud Features", "Audit & Governance", "Sentinel Policy", "Azure RBAC / IAM Policy"),
                preferenceManager = preferenceManager,
                prefix = "tf_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun TerraformCard(
    phase: String,
    title: String,
    icon: ImageVector,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String,
    isAlert: Boolean = false
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Surface(
                    color = if (isAlert) Color.Red.copy(alpha = 0.1f) else Color(0xFFF3F4F9),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = if (isAlert) Color.Red else TerraformPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = TerraformPrimary)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "PHASE $phase",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (isAlert) Color.Red else TerraformPrimary
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TerraformDark
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                TerraformCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun TerraformCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = TerraformPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else TerraformDark,
            lineHeight = 20.sp
        )
    }
}
