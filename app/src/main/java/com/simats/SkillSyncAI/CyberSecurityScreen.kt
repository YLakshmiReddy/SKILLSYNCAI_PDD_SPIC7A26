package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
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

val CyberBgStart = Color(0xFFF1F4FF)
val CyberBgEnd = Color(0xFFFFFFFF)
val CyberPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CyberSecurityScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = CyberPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = CyberPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = CyberPrimary)
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
                .background(Brush.verticalGradient(listOf(CyberBgStart, CyberBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Cyber Security\nMastery Roadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = CyberPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Follow the 7-phase sequence to build your expertise in digital defense. Track your progress across every module.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Fundamental IT Skills
            CyberRoadmapCard(
                phase = "1",
                title = "Fundamental IT Skills",
                icon = Icons.Default.Settings,
                items = listOf("Hardware & Connections", "Operating Systems", "Virtualization Basics", "Troubleshooting"),
                preferenceManager = preferenceManager,
                prefix = "cs_p1"
            )

            // 2. Networking Knowledge
            CyberRoadmapCard(
                phase = "2",
                title = "Networking Knowledge",
                icon = Icons.Default.Public,
                items = listOf("OSI & TCP/IP Models", "Protocols (DNS, HTTP, etc.)", "IP Addressing & Subnetting", "Secure Network Architecture"),
                preferenceManager = preferenceManager,
                prefix = "cs_p2"
            )

            // 3. Security Skills and Knowledge
            CyberRoadmapCard(
                phase = "3",
                title = "Security Skills and Knowledge",
                icon = Icons.Default.Lock,
                items = listOf("Cryptography & Encryption", "Defensive Strategies", "Hardening Techniques", "Offensive Basics"),
                preferenceManager = preferenceManager,
                prefix = "cs_p3"
            )

            // 4. Incident Response & Threat Intel
            CyberRoadmapCard(
                phase = "4",
                title = "Incident Response & Threat Intel",
                icon = Icons.Default.Radar,
                items = listOf("Incident Lifecycle", "Forensic Analysis", "Threat Intelligence", "Log Management"),
                preferenceManager = preferenceManager,
                prefix = "cs_p4"
            )

            // 5. Common Attacks & Standards
            CyberRoadmapCard(
                phase = "5",
                title = "Common Attacks & Standards",
                icon = Icons.Default.BugReport,
                items = listOf("Web App Attacks (OWASP)", "Network Exploit Vectors", "Compliance (GDPR, PCI)", "Security Frameworks"),
                preferenceManager = preferenceManager,
                prefix = "cs_p5"
            )

            // 6. Cloud Skills & Programming
            CyberRoadmapCard(
                phase = "6",
                title = "Cloud Skills & Programming",
                icon = Icons.Default.Cloud,
                items = listOf("Cloud Fundamentals (AWS/Azure)", "Shared Responsibility Model", "Python for Security", "Scripting & Automation"),
                preferenceManager = preferenceManager,
                prefix = "cs_p6"
            )

            // 7. Professional Growth
            CyberRoadmapCard(
                phase = "7",
                title = "Professional Growth",
                icon = Icons.Default.Star,
                items = listOf("Certifications (Sec+, CEH, CISSP)", "Capture The Flag (CTF) Labs", "Networking & Community", "Portfolio & Labs"),
                preferenceManager = preferenceManager,
                prefix = "cs_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CyberRoadmapCard(
    phase: String,
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = CyberPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$phase. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = CyberPrimary
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                CyberCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun CyberCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = CyberPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else Color.Black,
            lineHeight = 20.sp
        )
    }
}
