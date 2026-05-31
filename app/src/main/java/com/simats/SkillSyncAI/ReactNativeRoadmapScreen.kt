package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PhoneIphone
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

val RN_BgStart = Color(0xFFF9FAFF)
val RN_BgEnd = Color(0xFFFFFFFF)
val RN_Primary = Color(0xFF61DAFB) // React Native Blue
val RN_Dark = Color(0xFF1E1B4B)
val RN_Accent = Color(0xFF0D1117)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReactNativeRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.PhoneIphone, null, tint = RN_Primary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("SkillSync AI", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = RN_Dark)
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
                .background(Brush.verticalGradient(listOf(RN_BgStart, RN_BgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "React Native\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master high-performance native apps for iOS and Android",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(modifier = Modifier.width(100.dp), thickness = 3.dp, color = RN_Dark)
            Text(
                text = "7 CRITICAL PHASES",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp),
                color = RN_Dark
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            RNRoadmapCard(
                phase = "01",
                title = "Introduction and Prerequisites",
                items = listOf("Javascript Basics (ES6+, async)", "CSS Basics (Flexbox)", "React Fundamentals (JSX, State, Props)", "Platform Overview (Bridge, Native)"),
                preferenceManager = preferenceManager,
                prefix = "rn_p1"
            )

            // Phase 02
            RNRoadmapCard(
                phase = "02",
                title = "Environment Setup",
                items = listOf("RN CLI & Metro Bundler", "Expo Ecosystem (Expo Go)", "create-expo-app & Snack"),
                preferenceManager = preferenceManager,
                prefix = "rn_p2"
            )

            // Phase 03
            RNRoadmapCard(
                phase = "03",
                title = "Core Components & Styling",
                items = listOf("Native UI Blocks (View, Text)", "Safe Areas & Layouts", "Lists (FlatList, SectionList)", "StyleSheet & Flexbox"),
                preferenceManager = preferenceManager,
                prefix = "rn_p3"
            )

            // Phase 04 - Highlighted
            RNRoadmapCard(
                phase = "04",
                title = "Interactions and Navigation",
                items = listOf("User Input (Touchables, Pressable)", "Gesture Handling (Native GH)", "Routing (React Navigation)", "Native Features (Notifications)"),
                preferenceManager = preferenceManager,
                prefix = "rn_p4",
                isHighlighted = true
            )

            // Phase 05
            RNRoadmapCard(
                phase = "05",
                title = "State & Storage",
                items = listOf("Context, Redux, Zustand", "Async Storage & Secure Store", "Expo SQLite Persistence"),
                preferenceManager = preferenceManager,
                prefix = "rn_p5"
            )

            // Phase 06
            RNRoadmapCard(
                phase = "06",
                title = "Advanced Concepts and Performance",
                items = listOf("Bridging and Native Modules", "60 FPS & RAM Bundles", "Profiling & Optimizations", "Animated API & Reanimated"),
                preferenceManager = preferenceManager,
                prefix = "rn_p6"
            )

            // Phase 07
            RNRoadmapCard(
                phase = "07",
                title = "QA and Deployment",
                items = listOf("Jest, RNTL & Detox", "Fast Refresh & LogBox", "App Store & Play Store"),
                preferenceManager = preferenceManager,
                prefix = "rn_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun RNRoadmapCard(
    phase: String,
    title: String,
    items: List<String>,
    preferenceManager: PreferenceManager,
    prefix: String,
    isHighlighted: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) Color(0xFF1E293B) else Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isHighlighted) 8.dp else 1.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = if (isHighlighted) Color.White else Color.Black
                    )
                }
                Text(
                    text = phase,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    color = if (isHighlighted) Color.White.copy(alpha = 0.1f) else Color.LightGray.copy(alpha = 0.3f)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                RNCheckboxItem(item, title, prefix, preferenceManager, isHighlighted)
            }
        }
    }
}

@Composable
fun RNCheckboxItem(
    item: String, 
    title: String, 
    prefix: String, 
    preferenceManager: PreferenceManager,
    isHighlighted: Boolean
) {
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
            colors = CheckboxDefaults.colors(
                checkedColor = if (isHighlighted) RN_Primary else RN_Dark,
                uncheckedColor = if (isHighlighted) Color.White.copy(alpha = 0.4f) else Color.LightGray,
                checkmarkColor = if (isHighlighted) RN_Dark else Color.White
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (isHighlighted) Color.White.copy(alpha = 0.8f) else if (checked) Color.Gray else Color.Black,
            lineHeight = 20.sp
        )
    }
}
