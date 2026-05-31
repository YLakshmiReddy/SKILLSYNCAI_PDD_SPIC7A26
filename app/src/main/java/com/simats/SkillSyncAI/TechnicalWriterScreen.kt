package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Share
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

val TWBgStart = Color(0xFFF1F4FF)
val TWBgEnd = Color(0xFFFFFFFF)
val TWPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicalWriterScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = TWPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TWPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TWPrimary)
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
                .background(Brush.verticalGradient(listOf(TWBgStart, TWBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Master the\nArt of\nTechnical Clarity.",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = TWPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "This roadmap details the journey from being a writer to a professional technical writer. Learn how to communicate complex technical concepts with ease.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Introduction & Required Skills
            TWRoadmapCard(
                phaseNum = "01",
                title = "Introduction & Required Skills",
                icon = Icons.Default.Info,
                content = {
                    TWSubSection("Understanding the Role", listOf("What is a Technical Writer?", "Tools of the trade"), preferenceManager, "tw_p1_role")
                    Spacer(modifier = Modifier.height(16.dp))
                    TWSubSection("Core Skills", listOf("Writing Proficiency", "Language Proficiency", "Technical Knowledge"), preferenceManager, "tw_p1_skills")
                }
            )

            // 2. Tooling & Setup
            TWRoadmapCard(
                phaseNum = "02",
                title = "Tooling & Setup",
                icon = Icons.Default.Edit,
                content = {
                    TWSubSection("Writing Tools", listOf("MS Word, Google Docs", "Notepad++", "Vim / Emacs"), preferenceManager, "tw_p2_tools")
                    Spacer(modifier = Modifier.height(16.dp))
                    TWSubSection("Markup", listOf("Markdown", "ReStructuredText", "LaTex"), preferenceManager, "tw_p2_markup")
                }
            )

            // 3. Best Practices & Content Research
            TWRoadmapCard(
                phaseNum = "03",
                title = "Best Practices & Content Research",
                icon = Icons.Default.MenuBook,
                content = {
                    TWSubSection("Writing Fundamentals", listOf("Readability", "Sentence Structure", "Vocabulary"), preferenceManager, "tw_p3_fund")
                    Spacer(modifier = Modifier.height(16.dp))
                    TWSubSection("Content Research", listOf("SME (Subject Matter Experts)", "Product Testing"), preferenceManager, "tw_p3_res")
                }
            )

            // 4. Types of Technical Content
            TWRoadmapCard(
                phaseNum = "04",
                title = "Types of Technical Content",
                icon = Icons.Default.Description,
                content = {
                    TWSubSection("Product Documentation", listOf("User Guides", "API Documentation", "Release Notes"), preferenceManager, "tw_p4_prod")
                    Spacer(modifier = Modifier.height(16.dp))
                    TWSubSection("Internal Documentation", listOf("Knowledge Base", "Wikis"), preferenceManager, "tw_p4_int")
                }
            )

            // 5. Technical Content Strategy
            TWRoadmapCard(
                phaseNum = "05",
                title = "Technical Content Strategy",
                icon = Icons.Default.History,
                items = listOf(
                    "Planning & Outlining",
                    "Style Guides"
                ),
                preferenceManager = preferenceManager,
                prefix = "tw_p5"
            )

            // 6. Analysis, Optimization & Distribution
            TWRoadmapCard(
                phaseNum = "06",
                title = "Analysis, Optimization & Distribution",
                icon = Icons.Default.Share,
                content = {
                    TWSubSection("Analysis", listOf("Content Audit", "User Analytics"), preferenceManager, "tw_p6_ana")
                    Spacer(modifier = Modifier.height(16.dp))
                    TWSubSection("Content Optimization", listOf("SEO Basics", "Localisation"), preferenceManager, "tw_p6_opt")
                    Spacer(modifier = Modifier.height(16.dp))
                    TWSubSection("Content Distribution", listOf("GitHub / GitLab", "Social Media"), preferenceManager, "tw_p6_dist")
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun TWRoadmapCard(
    phaseNum: String,
    title: String,
    icon: ImageVector,
    items: List<String>? = null,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = TWPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Text(
                    text = "PHASE $phaseNum",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TWPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (items != null && preferenceManager != null && prefix != null) {
                items.forEach { item ->
                    TWCheckboxItem(item, title, prefix, preferenceManager)
                }
            }
            
            content?.invoke()
        }
    }
}

@Composable
fun TWSubSection(title: String, items: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Black, color = Color.Gray, letterSpacing = 1.sp)
    Spacer(modifier = Modifier.height(8.dp))
    items.forEach { item ->
        TWCheckboxItem(item, title, prefix, preferenceManager)
    }
}

@Composable
fun TWCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = TWPrimary)
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
