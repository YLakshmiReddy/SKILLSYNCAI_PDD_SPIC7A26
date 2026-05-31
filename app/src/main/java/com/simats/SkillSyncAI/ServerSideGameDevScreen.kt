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

val SSGDBgStart = Color(0xFFF1F4FF)
val SSGDBgEnd = Color(0xFFFFFFFF)
val SSGDPrimary = Color(0xFF1A237E)
val SSGDDarkCardBg = Color(0xFF1A1C4E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerSideGameDevScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = SSGDPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = SSGDPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = SSGDPrimary)
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
                .background(Brush.verticalGradient(listOf(SSGDBgStart, SSGDBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            // Header Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                colors = CardDefaults.cardColors(containerColor = SSGDDarkCardBg),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Surface(
                        color = Color.White.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "GUIDE FOR 2024",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 10.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Server-Side Game\nDeveloper Mastery",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            lineHeight = 38.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Your definitive path to mastering the complex backend systems that power multiplayer games, distributed architecture, and data synchronization.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(alpha = 0.8f),
                            lineHeight = 22.sp
                        )
                    )
                }
            }

            // 1. Network Protocols
            SSGDRoadmapCard(
                phase = "01",
                title = "Network Protocols",
                icon = Icons.Default.Hub,
                items = listOf("Reliable UDP", "User Datagram Protocol", "Transmission Control Protocol", "WebSocket vs HTTP/2", "Sockets & Networking", "MTU and IP Fragmentation"),
                preferenceManager = preferenceManager,
                prefix = "ssgd_net"
            )

            // 2. Programming
            SSGDRoadmapCard(
                phase = "02",
                title = "Programming",
                icon = Icons.Default.Code,
                items = listOf("C++ / C# / Rust", "Java / Go / Node.js", "Netty / Akka / Vert.x", "Asp.Net Core / gRPC"),
                preferenceManager = preferenceManager,
                prefix = "ssgd_prog"
            )

            // 3. Techniques & Concurrency
            SSGDRoadmapCard(
                phase = "03",
                title = "Techniques & Concurrency",
                icon = Icons.Default.SyncAlt,
                items = listOf("Entity Interpolation & Extrapolation", "Lag Compensation", "Client-Side Prediction", "Multithreading & Actor Model", "Lock-free Programming", "Async/Await Patterns"),
                preferenceManager = preferenceManager,
                prefix = "ssgd_concur"
            )

            // 4. Data & Messaging
            SSGDRoadmapCard(
                phase = "04",
                title = "Data & Messaging",
                icon = Icons.Default.Storage,
                items = listOf("PostgreSQL / Redis", "MongoDB / DynamoDB", "Kafka / RabbitMQ", "Protocol Buffers / FlatBuffers"),
                preferenceManager = preferenceManager,
                prefix = "ssgd_data"
            )

            // 5. Security & Ops
            SSGDRoadmapCard(
                phase = "05",
                title = "Security & Ops",
                icon = Icons.Default.Security,
                items = listOf("Anti-Cheat & Encryption", "DDoS Protection", "CI/CD for Game Servers", "Docker & Kubernetes"),
                preferenceManager = preferenceManager,
                prefix = "ssgd_sec"
            )

            // 6. AI in Backend
            SSGDRoadmapCard(
                phase = "06",
                title = "AI in Backend",
                icon = Icons.Default.AutoAwesome,
                items = listOf("Matchmaking Algorithms", "Fraud & Toxicity Detection", "Automated Testing Bots"),
                preferenceManager = preferenceManager,
                prefix = "ssgd_ai"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SSGDRoadmapCard(
    phase: String,
    title: String,
    icon: ImageVector,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = SSGDPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "PHASE $phase",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = SSGDPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                SSGDCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun SSGDCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = SSGDPrimary)
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
