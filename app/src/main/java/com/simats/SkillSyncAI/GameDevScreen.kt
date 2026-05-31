package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Speed
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

val GameDevBgStart = Color(0xFFF3F6FF)
val GameDevBgEnd = Color(0xFFFFFFFF)
val GameDevPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDevScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = GameDevPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = GameDevPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = GameDevPrimary)
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
                .background(Brush.verticalGradient(listOf(GameDevBgStart, GameDevBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Game\nDeveloper\nRoadmap 2024",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = GameDevPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the core engineering principles of modern game development, from low-level mathematics to high-performance AI systems.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Game Mathematics
            GameDevRoadmapCard(
                title = "Game Mathematics",
                description = "Foundation of all 3D transformations and spatial reasoning.",
                icon = Icons.Default.Calculate,
                items = listOf(
                    "Vector and Matrix operations",
                    "Linear & Affine Transformations",
                    "Quaternions & Euler Angles",
                    "Spline, Hermite, Bezier Curves"
                ),
                preferenceManager = preferenceManager,
                prefix = "gd_math"
            )

            // 2. Core Development Skills
            GameDevRoadmapCard(
                title = "Core Development Skills",
                description = "Languages and engines that power the industry.",
                icon = Icons.Default.Code,
                content = {
                    GameSubSection("LANGUAGES", listOf("C++ / C# / Rust", "Python / Lua / GDScript"), preferenceManager, "gd_lang")
                    Spacer(modifier = Modifier.height(16.dp))
                    GameSubSection("ENGINES", listOf("Unity / Unreal Engine", "Godot / Bevy"), preferenceManager, "gd_engine")
                    Spacer(modifier = Modifier.height(16.dp))
                    GameSubSection("ARCHITECTURE", listOf("ECS (Entity Component System)", "Native Engine Dev"), preferenceManager, "gd_arch")
                }
            )

            // 3. Game Physics
            GameDevRoadmapCard(
                title = "Game Physics",
                description = "Simulating movement, collision, and real-world forces.",
                icon = Icons.Default.Speed,
                content = {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        GameSkillTag("SAT")
                        GameSkillTag("GJK / EPA")
                        GameSkillTag("BVH")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    GameSubSection("", listOf("Rigid Body Dynamics", "Fluid vs Soft vs Particle", "Continuous Collision Detection", "Buoyancy & Friction Models"), preferenceManager, "gd_phys")
                }
            )

            // 4. Computer Graphics
            GameDevRoadmapCard(
                title = "Computer Graphics",
                description = "From rasterization to real-time ray tracing.",
                icon = Icons.Default.Computer,
                content = {
                    GameSubSection("RENDERING PIPELINES", listOf("Optimization & Draw Calls", "Shaders & Rendering Equation", "Lighting & Shadow Mapping"), preferenceManager, "gd_gfx")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        GameSkillTag("DirectX")
                        GameSkillTag("Vulkan")
                        GameSkillTag("Metal")
                        GameSkillTag("OpenGL")
                    }
                }
            )

            // 5. Game AI
            GameDevRoadmapCard(
                title = "Game AI",
                description = "Autonomous behavior and intelligent decision making.",
                icon = Icons.Default.Psychology,
                items = listOf(
                    "State Machines & Behavior Trees",
                    "GOAP & Fuzzy Logic",
                    "Pathfinding & Steering (A*)",
                    "Reinforcement Learning",
                    "Neural Networks for Games"
                ),
                preferenceManager = preferenceManager,
                prefix = "gd_ai"
            )

            // 6. Advanced Rendering
            GameDevRoadmapCard(
                title = "Advanced Rendering",
                description = "Pushing the boundaries of visual fidelity.",
                icon = Icons.Default.AutoAwesome,
                items = listOf(
                    "Physically-Based Rendering (PBR)",
                    "Conservation of Energy & Volumetrics",
                    "DLSS / FidelityFX Upscaling",
                    "Translucency & Micro-facets"
                ),
                preferenceManager = preferenceManager,
                prefix = "gd_adv_gfx"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun GameDevRoadmapCard(
    title: String,
    description: String? = null,
    icon: ImageVector? = null,
    items: List<String>? = null,
    content: @Composable (() -> Unit)? = null,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    Surface(
                        color = Color(0xFFE8EAF6),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(32.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(icon, null, tint = GameDevPrimary, modifier = Modifier.size(18.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
            }
            if (description != null) {
                Text(text = description, fontSize = 13.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            
            if (items != null && preferenceManager != null && prefix != null) {
                items.forEach { item ->
                    GameCheckboxItem(item, title, prefix, preferenceManager)
                }
            }
            
            content?.invoke()
        }
    }
}

@Composable
fun GameSubSection(title: String, items: List<String>, preferenceManager: PreferenceManager, prefix: String) {
    if (title.isNotEmpty()) {
        Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Black, color = GameDevPrimary, letterSpacing = 1.sp)
        Spacer(modifier = Modifier.height(8.dp))
    }
    items.forEach { item ->
        GameCheckboxItem(item, title, prefix, preferenceManager)
    }
}

@Composable
fun GameCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = GameDevPrimary)
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

@Composable
fun GameSkillTag(text: String) {
    Surface(
        color = Color(0xFFE8EAF6),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = text, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 10.sp, color = GameDevPrimary, fontWeight = FontWeight.Bold)
    }
}
