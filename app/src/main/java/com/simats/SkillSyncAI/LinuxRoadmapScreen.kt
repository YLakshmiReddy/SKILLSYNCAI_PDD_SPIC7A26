package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Terminal
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

val LinuxBgStart = Color(0xFFF1F5F9)
val LinuxBgEnd = Color(0xFFFFFFFF)
val LinuxPrimary = Color(0xFF334155) // Slate 700
val LinuxSecondary = Color(0xFF64748B) // Slate 500
val LinuxDark = Color(0xFF0F172A) // Slate 900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinuxRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Terminal, null, tint = LinuxPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Linux Developer", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = LinuxDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = LinuxDark)
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
                .background(Brush.verticalGradient(listOf(LinuxBgStart, LinuxBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Linux Developer\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = LinuxDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the open-source operating system. From command-line foundations to advanced system administration and kernel concepts.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = LinuxSecondary,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            LinuxRoadmapCard(
                phase = "01",
                title = "Navigation & Editing",
                items = listOf(
                    "Directory Navigation (cd, ls, pwd)",
                    "Vim/Nano Basics",
                    "Standard Streams & Redirection",
                    "Command History & Shortcuts"
                ),
                preferenceManager = preferenceManager,
                prefix = "lnx_p1"
            )

            // Phase 2
            LinuxRoadmapCard(
                phase = "02",
                title = "Shell & Other Basics",
                items = listOf(
                    "Environment Variables (PATH, HOME)",
                    "Man Pages & Documentation",
                    "User & Group Permissions (chmod, chown)",
                    "Shell Configuration (.bashrc, .zshrc)"
                ),
                preferenceManager = preferenceManager,
                prefix = "lnx_p2"
            )

            // Phase 3
            LinuxRoadmapCard(
                phase = "03",
                title = "Files & Text",
                items = listOf(
                    "Grep, Sed, and Awk Basics",
                    "Find and Locate Commands",
                    "Archiving (tar, zip, gzip)",
                    "Diff and Patch utilities"
                ),
                preferenceManager = preferenceManager,
                prefix = "lnx_p3"
            )

            // Phase 4
            LinuxRoadmapCard(
                phase = "04",
                title = "System Management",
                items = listOf(
                    "Process Management (top, kill, ps)",
                    "Systemd & Service Management",
                    "Log Monitoring (journalctl, tail -f)",
                    "Package Management (apt, dnf, pacman)"
                ),
                preferenceManager = preferenceManager,
                prefix = "lnx_p4"
            )

            // Phase 5
            LinuxRoadmapCard(
                phase = "05",
                title = "Infrastructure & Storage",
                items = listOf(
                    "Mounting Filesystems (fstab)",
                    "LVM (Logical Volume Management)",
                    "Disk Usage Analysis (df, du)",
                    "Swap Management & Partitioning"
                ),
                preferenceManager = preferenceManager,
                prefix = "lnx_p5"
            )

            // Phase 6
            LinuxRoadmapCard(
                phase = "06",
                title = "Networking",
                items = listOf(
                    "SSH & Remote Access (Key Auth)",
                    "Network Configuration (ip, nmcli)",
                    "DNS & Port Scanning (dig, nmap)",
                    "Firewall Management (UFW, iptables)"
                ),
                preferenceManager = preferenceManager,
                prefix = "lnx_p6"
            )

            // Phase 7
            LinuxRoadmapCard(
                phase = "07",
                title = "Containers & Dev",
                items = listOf(
                    "Shell Scripting (Bash Syntax)",
                    "Docker Fundamentals & CLI",
                    "Container Orchestration (Intro)",
                    "CI/CD Pipeline Integration"
                ),
                preferenceManager = preferenceManager,
                prefix = "lnx_p7"
            )

            // Phase 8
            LinuxRoadmapCard(
                phase = "08",
                title = "Advanced Kernel & Security",
                items = listOf(
                    "Kernel Modules (lsmod, modprobe)",
                    "SELinux & AppArmor",
                    "System Hardening Basics",
                    "Performance Tuning"
                ),
                preferenceManager = preferenceManager,
                prefix = "lnx_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun LinuxRoadmapCard(
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
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color(0xFFE2E8F0),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "PHASE $phase",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = LinuxPrimary
                    )
                }
                
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = LinuxPrimary)
                )
            }
            
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = LinuxDark
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                LinuxCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun LinuxCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = LinuxPrimary)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else LinuxDark,
            lineHeight = 20.sp
        )
    }
}
