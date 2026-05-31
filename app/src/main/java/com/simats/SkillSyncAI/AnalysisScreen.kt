package com.simats.SkillSyncAI

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

// Color Palette
val BgLight = Color(0xFFF7F9FC)
val BrandBlue = Color(0xFF1E88E5)
val BrandGreen = Color(0xFF4CAF50)
val TextGray = Color(0xFF757575)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisScreen(
    onDashboardClick: () -> Unit,
    onRoadmapClick: () -> Unit,
    onProfileClick: () -> Unit,
    onViewResultsClick: () -> Unit
) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    val currentScore = preferenceManager.getCurrentScore()
    val previousScore = preferenceManager.getPreviousScore()
    
    val matchDetailsJson = preferenceManager.getMatchDetailsJson()
    val domainScores = remember(matchDetailsJson) {
        if (matchDetailsJson.isNotBlank()) {
            try {
                val type = object : TypeToken<Map<String, Int>>() {}.type
                Gson().fromJson<Map<String, Int>>(matchDetailsJson, type)
            } catch (e: Exception) {
                emptyMap()
            }
        } else {
            emptyMap()
        }
    }

    val displayedScores = remember(domainScores) {
        if (domainScores.isNotEmpty()) {
            domainScores.entries.sortedByDescending { it.value }.take(5)
        } else {
            listOf(
                java.util.AbstractMap.SimpleEntry("Critical Thinking", 65),
                java.util.AbstractMap.SimpleEntry("Technical Agility", 80)
            )
        }
    }
    
    val improvement = currentScore - previousScore
    val improvementText = if (improvement >= 0) "+$improvement% Increase" else "$improvement% Decrease"
    val footerStatus = if (improvement >= 0) "IMPROVED" else "DECLINED"

    Scaffold(
        containerColor = BgLight,
        topBar = {
            TopAppBar(
                title = { Text("AI Analysis", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = { 
            AppBottomNavigation(
                currentScreen = "Analysis",
                onHomeClick = onDashboardClick,
                onAnalysisClick = { },
                onRoadmapClick = onRoadmapClick,
                onProfileClick = onProfileClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Compare Score",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1C1E)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- TOP SCORE CARDS ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ScoreOverviewCard(
                    label = "Previous Score",
                    score = previousScore,
                    footer = "Baseline Assessment",
                    isCurrent = false,
                    modifier = Modifier.weight(1f)
                )
                ScoreOverviewCard(
                    label = "Current Score",
                    score = currentScore,
                    footer = footerStatus,
                    isCurrent = true,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- IMPROVEMENT BANNER ---
            Card(
                modifier = Modifier.fillMaxWidth().clickable { onViewResultsClick() },
                colors = CardDefaults.cardColors(containerColor = BrandBlue),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Overall Improvement", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                            Text(improvementText, color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                        }
                        Box(
                            modifier = Modifier.size(48.dp).background(Color.White.copy(alpha = 0.2f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.AutoMirrored.Filled.TrendingUp, null, tint = Color.White)
                        }
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp), color = Color.White.copy(alpha = 0.2f))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BannerMetric("TOP SKILL", "AI Prompting", Modifier.weight(1f))
                        BannerMetric("VELOCITY", "Above Average", Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- BREAKDOWN SECTION ---
            Text(
                text = "Breakdown by Category",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            displayedScores.forEach { entry ->
                val icon = if (entry.key.contains("Thinking", ignoreCase = true) || entry.key.contains("Agility", ignoreCase = true) || entry.key.contains("Science", ignoreCase = true)) {
                    Icons.Default.Psychology
                } else {
                    Icons.Default.Bolt
                }
                val iconBg = if (icon == Icons.Default.Psychology) Color(0xFFE8EAF6) else Color(0xFFE3F2FD)
                
                val categoryImp = if (improvement >= 0) {
                    if (domainScores.isNotEmpty()) (entry.value * 0.1f).toInt() else if (entry.key == "Critical Thinking") 15 else 8
                } else {
                    0
                }
                
                CategoryRow(
                    icon = icon,
                    title = entry.key,
                    baseScore = entry.value,
                    improvement = categoryImp,
                    iconBg = iconBg
                )
            }
        }
    }
}

// --- HELPER FUNCTION FOR API UPLOAD ---
fun handleFileUpload(
    uri: Uri, 
    context: android.content.Context, 
    preferenceManager: PreferenceManager,
    onComplete: (Boolean, String) -> Unit
) {
    val fileName = getFileNameFromUri(context, uri) ?: "resume.pdf"
    val file = File(context.cacheDir, fileName)
    
    try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream?.use { input -> outputStream.use { output -> input.copyTo(output) } }
    } catch (e: Exception) {
        onComplete(false, "Failed to read file: ${e.message}")
        return
    }

    val requestFile = file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitClient.apiService.uploadResume(body)
            withContext(Dispatchers.Main) {
                file.delete() // clean up cache
                if (response.isSuccessful) {
                    val result = response.body()
                    val score = result?.match_score ?: 0
                    preferenceManager.saveMatchScore(score)
                    
                    result?.suggested_path?.let { path ->
                        preferenceManager.saveProfileData(
                            preferenceManager.getUserName(),
                            preferenceManager.getUserEmail(),
                            preferenceManager.getUserPhone(),
                            path,
                            preferenceManager.getUserImageUri()
                        )
                    }
                    preferenceManager.saveAnalysisResults(
                        result?.pros ?: "",
                        result?.cons ?: ""
                    )
                    val gson = Gson()
                    val matchDetailsJson = gson.toJson(result?.match_details ?: emptyMap<String, Int>())
                    preferenceManager.saveMatchDetails(matchDetailsJson)
                    
                    onComplete(true, "Success: Match found for ${result?.suggested_path}")
                } else {
                    onComplete(false, "Server Error: ${response.code()}")
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                file.delete() // clean up cache
                onComplete(false, "Connection Error: Check if server is running")
            }
        }
    }
}

// --- KEEP YOUR EXISTING COMPONENTS BELOW (ScoreOverviewCard, ImprovementBanner, etc.) ---
@Composable
fun ScoreOverviewCard(label: String, score: Int, footer: String, isCurrent: Boolean, modifier: Modifier) {
    Card(
        modifier = modifier.height(240.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, color = if (isCurrent) BrandBlue else TextGray, fontWeight = FontWeight.SemiBold)

            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(110.dp)) {
                CircularProgressIndicator(
                    progress = { score / 100f },
                    modifier = Modifier.fillMaxSize(),
                    color = if (isCurrent) BrandBlue else Color(0xFFCFD8DC),
                    strokeWidth = 10.dp,
                    trackColor = Color(0xFFECEFF1),
                    strokeCap = StrokeCap.Round
                )
                Text("$score%", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            }

            if (isCurrent) {
                Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(8.dp)) {
                    Text(
                        footer, color = BrandGreen,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp, fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Text(footer, color = Color.LightGray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ImprovementBanner(onViewResultsClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onViewResultsClick() },
        colors = CardDefaults.cardColors(containerColor = BrandBlue),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Overall Improvement", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                    Text("+13% Increase", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier.size(48.dp).background(Color.White.copy(alpha = 0.2f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.AutoMirrored.Filled.TrendingUp, null, tint = Color.White)
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp), color = Color.White.copy(alpha = 0.2f))
            Row(modifier = Modifier.fillMaxWidth()) {
                BannerMetric("TOP SKILL", "AI Prompting", Modifier.weight(1f))
                BannerMetric("VELOCITY", "Above Average", Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun BannerMetric(label: String, value: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(label, fontSize = 11.sp, color = Color.White.copy(alpha = 0.7f), fontWeight = FontWeight.Bold)
        Text(value, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CategoryRow(icon: ImageVector, title: String, baseScore: Int, improvement: Int, iconBg: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(modifier = Modifier.size(40.dp), color = iconBg, shape = RoundedCornerShape(8.dp)) {
                Icon(icon, null, tint = BrandBlue, modifier = Modifier.padding(8.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, modifier = Modifier.weight(1f), fontWeight = FontWeight.Medium)
            Text("$baseScore%", color = TextGray, fontSize = 14.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text("+$improvement%", color = BrandGreen, fontWeight = FontWeight.Bold)
        }
    }
}
