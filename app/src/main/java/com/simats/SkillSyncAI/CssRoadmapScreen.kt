package com.simats.SkillSyncAI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush as GBrush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val CssBgStart = Color(0xFFF9FAFB)
val CssBgEnd = Color(0xFFFFFFFF)
val CssPrimary = Color(0xFF2563EB) // Modern Blue
val CssSecondary = Color(0xFF1E40AF)
val CssDark = Color(0xFF111827)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CssRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Brush, null, tint = CssPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("CSS Roadmap", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = CssDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = CssDark)
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
                .background(GBrush.verticalGradient(listOf(CssBgStart, CssBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Master the Art of\nStyling",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = CssDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A structured path from foundational selectors to advanced fluid layouts and modern CSS logic.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            CssRoadmapCard(
                phase = "01",
                title = "Introduction & Syntax Basics",
                items = listOf(
                    "Foundational Knowledge" to listOf("Frontend Dev Role", "Cascading Order", "Specificity"),
                    "Style Pipeline" to listOf("External, Internal, Inline", "@import"),
                    "Basic Syntax" to listOf("Rulesets", "Selectors", "Combinators")
                ),
                preferenceManager = preferenceManager,
                prefix = "css_p1"
            )

            // Phase 02
            CssRoadmapCard(
                phase = "02",
                title = "Text, Background & Box Model",
                items = listOf(
                    "Typography" to listOf("Web Fonts", "Text Styling (Weight, Line-height)", "Text Alignment"),
                    "Box Model" to listOf("Dimensions", "Padding & Margin", "Borders & Shadows", "Box-sizing"),
                    "Backgrounds & Color" to listOf("Solid Colors", "Gradients", "Background Images")
                ),
                preferenceManager = preferenceManager,
                prefix = "css_p2"
            )

            // Phase 03 - Core
            CssRoadmapCard(
                phase = "03",
                title = "Layout & Positioning",
                isCore = true,
                items = listOf(
                    "Positioning" to listOf("Relative, Absolute, Fixed, Sticky", "Z-index & Layering"),
                    "Modern Layout" to listOf("Flexbox (Flex Container & Items)", "CSS Grid (Grid Tracks & Areas)"),
                    "Legacy Layout" to listOf("Floats & Clears", "Display: inline-block")
                ),
                preferenceManager = preferenceManager,
                prefix = "css_p3"
            )

            // Phase 04
            CssRoadmapCard(
                phase = "04",
                title = "Selectors & Filters",
                items = listOf(
                    "Advanced Selection" to listOf("Pseudo-classes (:hover, :nth-child)", "Pseudo-elements (::before, ::after)", "Attribute Selectors"),
                    "Visual Effects" to listOf("Opacity", "CSS Filters (Blur, Contrast)", "Mix-blend-mode")
                ),
                preferenceManager = preferenceManager,
                prefix = "css_p4"
            )

            // Phase 05
            CssRoadmapCard(
                phase = "05",
                title = "Responsiveness",
                items = listOf(
                    "Queries" to listOf("Media Queries", "Container Queries"),
                    "CSS Logic" to listOf("Custom Properties (Variables)", "CSS Functions (calc, clamp, min, max)")
                ),
                preferenceManager = preferenceManager,
                prefix = "css_p5"
            )

            // Phase 06
            CssRoadmapCard(
                phase = "06",
                title = "Animations & Transitions",
                items = listOf(
                    "Transitions" to listOf("Property, Duration, Timing-function", "Implicit Animations"),
                    "Keyframes" to listOf("@keyframes syntax", "Animation Iteration & Direction", "Transforms (Translate, Rotate, Scale)")
                ),
                preferenceManager = preferenceManager,
                prefix = "css_p6"
            )

            // Phase 07
            CssRoadmapCard(
                phase = "07",
                title = "Methodologies & Best Practices",
                items = listOf(
                    "Organization" to listOf("BEM (Block Element Modifier)", "SASS/SCSS Pre-processors", "CSS Modules"),
                    "Performance" to listOf("Critical CSS", "Minification", "Layout Thrashing Prevention"),
                    "Modern Tooling" to listOf("PostCSS", "Tailwind CSS", "Stylelint")
                ),
                preferenceManager = preferenceManager,
                prefix = "css_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun CssRoadmapCard(
    phase: String,
    title: String,
    items: List<Pair<String, List<String>>>,
    preferenceManager: PreferenceManager,
    prefix: String,
    isCore: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = if (isCore) Color(0xFFF0F7FF) else Color.White),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isCore) 4.dp else 2.dp),
        border = if (isCore) BorderStroke(1.dp, CssPrimary.copy(alpha = 0.2f)) else null
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = if (isCore) CssSecondary else Color(0xFFF3F4F6),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = if (isCore) "CORE" else "PHASE $phase",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isCore) Color.White else Color.Gray
                    )
                }
                if (isCore) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Info, null, tint = CssPrimary, modifier = Modifier.size(14.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = CssDark
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { (subTitle, subItems) ->
                Text(
                    text = subTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isCore) CssSecondary else Color.Black,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                subItems.forEach { item ->
                    CssCheckboxItem(item, subTitle, prefix, preferenceManager)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun CssCheckboxItem(item: String, subTitle: String, prefix: String, preferenceManager: PreferenceManager) {
    val key = "${prefix}_${subTitle}_$item"
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
            colors = CheckboxDefaults.colors(checkedColor = CssPrimary)
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
