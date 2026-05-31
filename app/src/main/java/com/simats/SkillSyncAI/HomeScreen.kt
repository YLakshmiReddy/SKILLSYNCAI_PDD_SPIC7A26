package com.simats.SkillSyncAI

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onAnalysisClick: () -> Unit,
    onRoadmapClick: () -> Unit,
    onProfileClick: () -> Unit,
    onUploadResumeClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val primaryBlue = Color(0xFF1D4ED8)
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    val userRole = preferenceManager.getUserRole()
    val matchScore = preferenceManager.getCurrentScore()
    
    val prosString = preferenceManager.getAnalysisPros()
    val consString = preferenceManager.getAnalysisCons()

    val skillsFound = remember(prosString) {
        if (prosString.isNotBlank()) {
            prosString.split(".")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .take(3)
        } else {
            listOf("Python", "UX Design", "Project Management")
        }
    }

    val skillsLacking = remember(consString) {
        if (consString.isNotBlank()) {
            consString.split(Regex("[,.]"))
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .take(3)
        } else {
            listOf("Cloud Architecture", "React", "Agile Methodologies")
        }
    }
    
    val scoreStatus = when {
        matchScore >= 80 -> "EXCELLENT"
        matchScore >= 60 -> "GOOD"
        matchScore >= 40 -> "AVERAGE"
        matchScore > 0 -> "BEGINNER"
        else -> "NO DATA"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AppBottomNavigation(
                currentScreen = "Home",
                onHomeClick = { },
                onAnalysisClick = onAnalysisClick,
                onRoadmapClick = onRoadmapClick,
                onProfileClick = onProfileClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFFFFFFFF)
                        )
                    )
                )
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color(0xFF2196F3).copy(alpha = 0.08f),
                    radius = 400f,
                    center = center.copy(x = 0f, y = size.height * 0.1f)
                )
                drawCircle(
                    color = Color(0xFF2196F3).copy(alpha = 0.05f),
                    radius = 600f,
                    center = center.copy(x = size.width, y = size.height * 0.8f)
                )
            }
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White.copy(alpha = 0.9f),
                    shadowElevation = 2.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .statusBarsPadding(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Surface(
                                modifier = Modifier.size(38.dp),
                                color = primaryBlue,
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_skillsync_logo),
                                    contentDescription = "Logo",
                                    tint = Color.White,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                            
                            Spacer(modifier = Modifier.width(12.dp))
                            
                            Text(
                                text = "SkillSync Ai",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = primaryBlue,
                                letterSpacing = (-0.5).sp
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = 12.dp, shape = RoundedCornerShape(24.dp))
                            .clip(RoundedCornerShape(24.dp)),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "RESUME MATCH SCORE",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF94A3B8),
                                letterSpacing = 1.2.sp
                            )
                            
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            Box(contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(
                                    progress = { matchScore / 100f },
                                    modifier = Modifier.size(160.dp),
                                    color = if (matchScore > 0) primaryBlue else Color.LightGray,
                                    strokeWidth = 14.dp,
                                    trackColor = Color(0xFFE2E8F0),
                                    strokeCap = StrokeCap.Round
                                )
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "$matchScore%",
                                        fontSize = 44.sp,
                                        fontWeight = FontWeight.Black,
                                        color = Color(0xFF0F172A)
                                    )
                                    Text(
                                        text = scoreStatus,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (matchScore > 0) primaryBlue else Color.Gray
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            Text(
                                text = "Your skills strongly align with current $userRole market demands.",
                                fontSize = 14.sp,
                                color = Color(0xFF64748B),
                                textAlign = TextAlign.Center,
                                lineHeight = 22.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFDCFCE7)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = Color(0xFF16A34A),
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Top 3 Skills Found",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A)
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(20.dp))
                            
                            skillsFound.forEachIndexed { index, skill ->
                                val level = when (index) {
                                    0 -> "Expert"
                                    1 -> "Expert"
                                    else -> "Advanced"
                                }
                                val color = when (index) {
                                    2 -> primaryBlue
                                    else -> Color(0xFF16A34A)
                                }
                                SkillItemRow(name = skill, level = level, color = color)
                                if (index < skillsFound.size - 1) {
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color(0xFFF1F5F9))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { onRoadmapClick() },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFFEF3C7)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Warning,
                                        contentDescription = null,
                                        tint = Color(0xFFD97706),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Top 3 Skills Lacking",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A)
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(20.dp))
                            
                            skillsLacking.forEachIndexed { index, skill ->
                                LackingSkillItemRow(name = skill, onLearnClick = onRoadmapClick)
                                if (index < skillsLacking.size - 1) {
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color(0xFFF1F5F9))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = onUploadResumeClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryBlue)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(painter = painterResource(id = R.drawable.ic_upload), contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "Upload New Resume", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    
                    Text(
                        text = "PDF, DOCX up to 10MB supported",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
fun AppBottomNavigation(
    currentScreen: String,
    onHomeClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    onRoadmapClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val primaryBlue = Color(0xFF1D4ED8)
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.navigationBarsPadding()
    ) {
        NavigationBarItem(
            icon = { 
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home", 
                    modifier = Modifier.size(24.dp)
                ) 
            },
            label = { Text("HOME", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = currentScreen == "Home",
            onClick = onHomeClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color(0xFFDBEAFE),
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            )
        )
        NavigationBarItem(
            icon = { 
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Analysis", 
                    modifier = Modifier.size(24.dp)
                ) 
            },
            label = { Text("ANALYSIS", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = currentScreen == "Analysis",
            onClick = onAnalysisClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color(0xFFDBEAFE),
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            )
        )
        NavigationBarItem(
            icon = { 
                Icon(
                    imageVector = Icons.Default.Timeline, 
                    contentDescription = "Roadmap", 
                    modifier = Modifier.size(24.dp)
                ) 
            },
            label = { Text("ROADMAP", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = currentScreen == "Roadmap",
            onClick = onRoadmapClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color(0xFFDBEAFE),
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            )
        )
        NavigationBarItem(
            icon = { 
                Icon(
                    imageVector = Icons.Default.Person, 
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                ) 
            },
            label = { Text("PROFILE", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
            selected = currentScreen == "Profile",
            onClick = onProfileClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color(0xFFDBEAFE),
                unselectedIconColor = Color(0xFF64748B),
                unselectedTextColor = Color(0xFF64748B)
            )
        )
    }
}

@Composable
fun SkillItemRow(name: String, level: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF334155))
        Surface(
            color = color.copy(alpha = 0.1f),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = level,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

@Composable
fun LackingSkillItemRow(name: String, onLearnClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF334155))
        TextButton(
            onClick = onLearnClick,
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "LEARN",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B82F6)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color(0xFF3B82F6)
            )
        }
    }
}
