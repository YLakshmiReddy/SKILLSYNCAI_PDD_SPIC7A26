package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val DSOBgStart = Color(0xFF1A237E)
val DSOBgEnd = Color(0xFF3949AB)
val DSOPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevSecOpsScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = Color.White, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DSOBgStart)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(DSOBgStart, DSOBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "DevSecOps\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the integration of security into the modern DevOps lifecycle with our stratified learning path.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White.copy(alpha = 0.8f),
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase I - Fundamentals
            DSORoadmapCard(
                phase = "I",
                title = "Fundamentals",
                items = listOf(
                    "Introduction: DevOps vs. DevSecOps",
                    "PROGRAMMING & SCRIPTS" to listOf("Python, Go, PHP, Rust, Node.js", "Bash, PowerShell", "YAML, JSON, Ruby, Toml")
                ),
                preferenceManager = preferenceManager,
                prefix = "dso_p1"
            )

            // Phase II - Security Foundations
            DSORoadmapCard(
                phase = "II",
                title = "Security Foundations",
                items = listOf(
                    "CIA Triad, Authn, AuthZ",
                    "Web Security: OWASP Top 10",
                    "Encryption: Symm & Asymm",
                    "NETWORKING BASICS" to listOf("DNS, HTTP, TLS, Firewalls, Wireshark, Nmap, TCP/UDP")
                ),
                preferenceManager = preferenceManager,
                prefix = "dso_p2"
            )

            // Phase III - Application & Identity
            DSORoadmapCard(
                phase = "III",
                title = "Application & Identity",
                content = {
                    // Illustration placeholder
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        color = Color.Black.copy(alpha = 0.2f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.AutoAwesome, null, tint = Color.White.copy(alpha = 0.3f), modifier = Modifier.size(48.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    DSOSubSection("SECURE CODING", listOf("SAST Prevention", "DAST Prevention", "Input Validation"), preferenceManager, "dso_p3_code")
                    Spacer(modifier = Modifier.height(12.dp))
                    DSOSubSection("IDENTITY & SSO", listOf("Least Privilege", "RBAC"), preferenceManager, "dso_p3_id")
                }
            )

            // Phase IV - Threat & Infra
            DSORoadmapCard(
                phase = "IV",
                title = "Threat & Infra",
                content = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        DSOGridItem("Hacking", listOf("STRIDE, PASTA"), Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(12.dp))
                        DSOGridItem("Cloud", listOf("CSPM, KPP, CWPP"), Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        DSOGridItem("Artifacts", listOf("Cyclone, DX"), Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(12.dp))
                        DSOGridItem("Container", listOf("SCAN, Admission"), Modifier.weight(1f))
                    }
                }
            )

            // Phase V - Secure Architecture
            DSORoadmapCard(
                phase = "V",
                title = "Secure Architecture",
                items = listOf(
                    "Zero Trust: Never trust, always verify.",
                    "Secure Network Zoning: Isolation from untrusted.",
                    "Supply Chain Security: Software bill of materials (SBOM)"
                ),
                preferenceManager = preferenceManager,
                prefix = "dso_p5"
            )

            // Phase VI - Enterprise & Governance
            DSORoadmapCard(
                phase = "VI",
                title = "Enterprise & Governance",
                content = {
                    DSOSubSection("ADVANCED STRATEGY", listOf("GRC, Risk, VULN Policy, Env-Wide Lifecycle"), preferenceManager, "dso_p6_adv")
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("INCIDENT RESPONSE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Row(modifier = Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        DSOTag("Lifecycle")
                        DSOTag("Post-mortem")
                        DSOTag("Real-time")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("REPORTING & SSO", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Surface(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Log Analysis", modifier = Modifier.padding(12.dp), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DSORoadmapCard(
    phase: String,
    title: String,
    items: List<Any>? = null,
    content: (@Composable () -> Unit)? = null,
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
            Text("PHASE $phase", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))
            
            if (items != null && preferenceManager != null && prefix != null) {
                items.forEach { item ->
                    if (item is String) {
                        DSOCheckboxItem(item, title, prefix, preferenceManager)
                    } else if (item is Pair<*, *>) {
                        DSOSubSection(item.first as String, item.second as List<String>, preferenceManager, prefix)
                    }
                }
            }
            
            content?.invoke()
        }
    }
}

@Composable
fun DSOSubSection(title: String, items: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Black, color = Color.Gray, letterSpacing = 1.sp)
    Spacer(modifier = Modifier.height(8.dp))
    items.forEach { item ->
        DSOCheckboxItem(item, title, prefix, preferenceManager)
    }
}

@Composable
fun DSOCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
                preferenceManager.getSharedPrefs().edit().apply {
                    putBoolean(key, isChecked)
                    apply()
                }
            },
            modifier = Modifier.size(24.dp),
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF1A237E))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item,
            fontSize = 13.sp,
            color = if (checked) Color.Gray else Color.Black,
            lineHeight = 18.sp
        )
    }
}

@Composable
fun DSOGridItem(label: String, items: List<String>, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = Color(0xFFF8FAFC),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            items.forEach {
                Text(it, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}

@Composable
fun DSOTag(text: String) {
    Surface(
        color = Color(0xFFE8EAF6),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = text, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 10.sp, color = Color(0xFF1A237E), fontWeight = FontWeight.Bold)
    }
}
