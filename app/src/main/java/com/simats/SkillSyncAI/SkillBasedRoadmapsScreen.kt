package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Theme Colors
val SkillBgStart = Color(0xFFF8F9FF)
val SkillBgEnd = Color(0xFFFFFFFF)
val SkillPrimary = Color(0xFF1A237E)
val SkillIconBg = Color(0xFFE8EAF6)
val SkillChevron = Color(0xFFBDBDBD)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllSkillsScreen(onBackClick: () -> Unit, onSkillClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(SkillBgStart, SkillBgEnd)))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Top Navigation Bar
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "Skill Based Roadmaps",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = SkillPrimary,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = SkillPrimary)
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "All Skills",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            )
            Text(
                text = "Explore deep-dive learning paths for individual technologies.",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            // Skill List Section - Expanded with requested skills
            val skills = listOf(
                "AI Agents" to Icons.Default.SmartToy,
                "AI Red Teaming" to Icons.Default.Security,
                "API Design" to Icons.Default.Api,
                "Angular" to Icons.Default.ChangeHistory,
                "ASP.NET Core" to Icons.Default.Layers,
                "AWS" to Icons.Default.Cloud,
                "Azure" to Icons.Default.Window,
                "C++" to Icons.Default.Memory,
                "Cloudflare" to Icons.Default.Cloud,
                "Code Review" to Icons.Default.RateReview,
                "Computer Science" to Icons.Default.Terminal,
                "CSS" to Icons.Default.Css,
                "Data Structures and Algorithms" to Icons.Default.Functions,
                "Design System" to Icons.Default.Palette,
                "Docker" to Icons.Default.SettingsInputComponent,
                "Flutter" to Icons.Default.Smartphone,
                "GCP" to Icons.Default.CloudCircle,
                "Git" to Icons.Default.History,
                "Go" to Icons.Default.DirectionsRun,
                "GraphQL" to Icons.Default.Schema,
                "HTML" to Icons.Default.Html,
                "Java" to Icons.Default.Coffee,
                "JavaScript" to Icons.Default.Code,
                "Kotlin" to Icons.Default.Android,
                "Kubernetes" to Icons.Default.Hub,
                "Linux" to Icons.Default.Computer,
                "MongoDB" to Icons.Default.Dns,
                "Next.js" to Icons.Default.Javascript,
                "Node.js" to Icons.Default.Polymer,
                "NoSQL" to Icons.Default.CloudQueue,
                "PHP" to Icons.Default.Code,
                "Prompt Engineering" to Icons.Default.Psychology,
                "Python" to Icons.Default.AccountTree,
                "React" to Icons.Default.Javascript,
                "React Native" to Icons.Default.MobileFriendly,
                "Redis" to Icons.Default.Bolt,
                "Rust" to Icons.Default.Build,
                "Software Design and Architecture" to Icons.Default.AccountTree,
                "Spring Boot" to Icons.Default.Coffee,
                "SQL" to Icons.Default.Storage,
                "System Design" to Icons.Default.Architecture,
                "Terraform" to Icons.Default.Layers,
                "TypeScript" to Icons.Default.TypeSpecimen,
                "Vue.js" to Icons.Default.DeveloperMode
            ).sortedBy { it.first }

            skills.forEach { (name, icon) ->
                SkillItemCard(name, icon, onSkillClick)
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SkillItemCard(name: String, icon: ImageVector, onSkillClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onSkillClick(name) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Container
            Surface(
                modifier = Modifier.size(36.dp),
                color = SkillIconBg,
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = SkillPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Skill Name
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                ),
                modifier = Modifier.weight(1f)
            )

            // Chevron
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = SkillChevron,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
