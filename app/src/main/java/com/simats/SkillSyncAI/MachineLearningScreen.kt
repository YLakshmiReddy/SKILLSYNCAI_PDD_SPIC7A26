package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
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

val MLBgStart = Color(0xFFF1F4FF)
val MLBgEnd = Color(0xFFFFFFFF)
val MLPrimary = Color(0xFF1A237E)
val MLDarkCardBg = Color(0xFF1A1C4E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MachineLearningScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = MLPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MLPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MLPrimary)
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
                .background(Brush.verticalGradient(listOf(MLBgStart, MLBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "ML Roadmap",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MLPrimary
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Master the art of Machine Learning through a structured path.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Introduction
            MLRoadmapCard(
                phase = "1",
                title = "Introduction",
                items = listOf(
                    "Role Understanding",
                    "What is an ML Engineer?",
                    "ML Engineer vs. AI Engineer",
                    "Skills and Responsibilities"
                ),
                preferenceManager = preferenceManager,
                prefix = "ml_p1"
            )

            // 2. Mathematical Foundations
            MLRoadmapCard(
                phase = "2",
                title = "Mathematical Foundations",
                items = listOf(
                    "Linear Algebra (Matrices, Tensors, SVD)",
                    "Calculus (Derivatives, Chain Rule, Gradients)",
                    "Probability & Statistics (Bayes, Distributions)",
                    "Discrete Mathematics"
                ),
                preferenceManager = preferenceManager,
                prefix = "ml_p2"
            )

            // 3. Programming Fundamentals (Python)
            MLRoadmapCard(
                phase = "3",
                title = "Programming Fundamentals (Python)",
                items = listOf(
                    "Basic Syntax, Data Types, Loops, OOP",
                    "Essential Libraries (NumPy, Pandas)",
                    "Visualization (Matplotlib, Seaborn)"
                ),
                preferenceManager = preferenceManager,
                prefix = "ml_p3"
            )

            // 4. Data Collection & Cleaning
            MLRoadmapCard(
                phase = "4",
                title = "Data Collection & Cleaning",
                items = listOf(
                    "Data Sources (SQL, No-SQL, APIs)",
                    "Data Formats (CSV, JSON, Parquet)",
                    "Preprocessing (Cleaning, Scaling, Dimensionality Reduction)"
                ),
                preferenceManager = preferenceManager,
                prefix = "ml_p4"
            )

            // 5. Machine Learning Core (Dark Card)
            MLDarkRoadmapCard(
                phase = "5",
                title = "Machine Learning Core",
                items = listOf(
                    "ML Basics (Supervised, Unsupervised, RL)",
                    "Scikit-learn Workflow",
                    "Supervised (KNN, SVM, Random Forest, Regression)",
                    "Unsupervised (Clustering, PCA, Autoencoders)",
                    "Reinforcement Learning (Q-Learning)"
                ),
                preferenceManager = preferenceManager,
                prefix = "ml_p5"
            )

            // 6. Model Evaluation & Validation (Dark Card)
            MLDarkRoadmapCard(
                phase = "6",
                title = "Model Evaluation & Validation",
                items = listOf(
                    "Metrics (Accuracy, F1-Score, ROC-AUC, Confusion Matrix)",
                    "Validation (K-Fold, LOOCV)"
                ),
                preferenceManager = preferenceManager,
                prefix = "ml_p6"
            )

            // 7. Deep Learning
            MLRoadmapCard(
                phase = "7",
                title = "Deep Learning",
                items = listOf(
                    "NN Basics (Perceptron, Backpropagation)",
                    "Libraries (TensorFlow, PyTorch)",
                    "Architectures (CNN, RNN, LSTM, Attention, Transformers)"
                ),
                preferenceManager = preferenceManager,
                prefix = "ml_p7"
            )

            // 8. Advanced Concepts in ML
            MLRoadmapCard(
                phase = "8",
                title = "Advanced Concepts in ML",
                items = listOf(
                    "Generative Models (GANs)",
                    "NLP (Tokenization, Embeddings)",
                    "Explainable AI (XAI)"
                ),
                preferenceManager = preferenceManager,
                prefix = "ml_p8"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun MLRoadmapCard(
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
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "$phase. $title",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MLPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            items.forEach { item ->
                MLCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun MLDarkRoadmapCard(
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
        colors = CardDefaults.cardColors(containerColor = MLDarkCardBg),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "$phase. $title",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                MLCheckboxItem(item, title, prefix, preferenceManager, isDark = true)
            }
        }
    }
}

@Composable
fun MLCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager, isDark: Boolean = false) {
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
            colors = CheckboxDefaults.colors(
                checkedColor = if (isDark) Color.White else MLPrimary,
                checkmarkColor = if (isDark) MLDarkCardBg else Color.White,
                uncheckedColor = if (isDark) Color.White.copy(alpha = 0.4f) else Color.Gray
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (isDark) Color.White.copy(alpha = if (checked) 0.5f else 0.9f) else (if (checked) Color.Gray else Color.Black),
            lineHeight = 20.sp
        )
    }
}
