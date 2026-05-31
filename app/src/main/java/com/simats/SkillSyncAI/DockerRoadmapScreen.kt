package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsBoat
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

val DockerBgStart = Color(0xFFF0F7FF)
val DockerBgEnd = Color(0xFFFFFFFF)
val DockerPrimary = Color(0xFF2496ED) // Docker Blue
val DockerDark = Color(0xFF066DA5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DockerRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.DirectionsBoat, null, tint = DockerPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("DockerDev", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DockerPrimary)
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
                .background(Brush.verticalGradient(listOf(DockerBgStart, DockerBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Docker\nDeveloper\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "From basic containerization to cloud-native orchestration. Master the industry standard for modern application deployment.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Stats Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFF)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "08", fontSize = 28.sp, fontWeight = FontWeight.Black, color = DockerPrimary)
                    Text(text = "PHASES TOTAL", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.LightGray)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            DockerRoadmapCard(
                number = "1",
                title = "Introduction to Containers",
                items = listOf("What are Containers?", "Why do we need Containers?", "Virtualization Levels", "Standards"),
                preferenceManager = preferenceManager,
                prefix = "dkr_p1"
            )

            // Phase 2
            DockerRoadmapCard(
                number = "2",
                title = "Underlying Technologies",
                items = listOf("Linux Internals", "  - namespaces", "  - cgroups", "  - Union Filesystems"),
                preferenceManager = preferenceManager,
                prefix = "dkr_p2"
            )

            // Phase 3
            DockerRoadmapCard(
                number = "3",
                title = "Installation & Basics",
                items = listOf("Setup (Desktop & Engine)", "Using 3rd Party Images", "Exploring common images"),
                preferenceManager = preferenceManager,
                prefix = "dkr_p3"
            )

            // Phase 4
            DockerRoadmapCard(
                number = "4",
                title = "Data Persistence",
                items = listOf("Ephemeral Filesystem", "Volume Mounts", "Bind Mounts"),
                preferenceManager = preferenceManager,
                prefix = "dkr_p4"
            )

            // Phase 5
            DockerRoadmapCard(
                number = "5",
                title = "Building & Managing",
                items = listOf("Dockerfile Optimization", "Container Registries", "Image Versioning"),
                preferenceManager = preferenceManager,
                prefix = "dkr_p5"
            )

            // Phase 6
            DockerRoadmapCard(
                number = "6",
                title = "Execution & Security",
                items = listOf("Docker Compose", "Runtime Security", "Vulnerability Scanning"),
                preferenceManager = preferenceManager,
                prefix = "dkr_p6"
            )

            // Phase 7
            DockerRoadmapCard(
                number = "7",
                title = "Networking & Developer Experience",
                items = listOf("Managing via Docker CLI", "Hot Reloading Workflows", "Bridge & Host Networks", "Overlay Networking"),
                preferenceManager = preferenceManager,
                prefix = "dkr_p7"
            )

            // Phase 8
            DockerRoadmapCard(
                number = "8",
                title = "Deploying Containers",
                items = listOf("Kubernetes Basics", "Docker Swarm", "PaaS & Nomad"),
                preferenceManager = preferenceManager,
                prefix = "dkr_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DockerRoadmapCard(
    number: String,
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$number. $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                
                val cardKey = "${prefix}_main"
                var cardChecked by remember { mutableStateOf(preferenceManager.getSharedPrefs().getBoolean(cardKey, false)) }
                
                Checkbox(
                    checked = cardChecked,
                    onCheckedChange = { isChecked ->
                        cardChecked = isChecked
                        preferenceManager.getSharedPrefs().edit().putBoolean(cardKey, isChecked).apply()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = DockerPrimary)
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))

            items.forEach { item ->
                DockerCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun DockerCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = DockerPrimary)
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
