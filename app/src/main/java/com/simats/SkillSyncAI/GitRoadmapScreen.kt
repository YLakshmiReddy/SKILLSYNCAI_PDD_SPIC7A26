package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.History
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

val GitBgStart = Color(0xFFFFF5F2)
val GitBgEnd = Color(0xFFFFFFFF)
val GitPrimary = Color(0xFFF05032) // Git Orange
val GitDark = Color(0xFF3E2C00)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.History, null, tint = GitPrimary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Git Version Control", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = GitPrimary)
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
                .background(Brush.verticalGradient(listOf(GitBgStart, GitBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Git & GitHub\nMastery\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the world's most popular version control system. Track your progress from basic commits to complex branching strategies.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1: Git Basics
            GitRoadmapCard(
                phase = "01",
                title = "Git Foundations",
                items = listOf(
                    "What is Version Control?",
                    "Git Installation & Config",
                    "Initializing a Repo (git init)",
                    "Staging & Committing (add, commit)",
                    "Viewing History (log, status)"
                ),
                preferenceManager = preferenceManager,
                prefix = "git_p1"
            )

            // Phase 2: Branching & Merging
            GitRoadmapCard(
                phase = "02",
                title = "Branching & Merging",
                items = listOf(
                    "Creating Branches (branch, checkout)",
                    "Merging Branches (merge)",
                    "Resolving Merge Conflicts",
                    "Fast-forward vs Recursive Merge",
                    "Git Stash & Pop"
                ),
                preferenceManager = preferenceManager,
                prefix = "git_p2"
            )

            // Phase 3: Remote Repositories
            GitRoadmapCard(
                phase = "03",
                title = "Remote Collaboration",
                items = listOf(
                    "GitHub/GitLab/Bitbucket Setup",
                    "Pushing & Pulling (push, pull, fetch)",
                    "Remote Tracking Branches",
                    "SSH vs HTTPS Authentication",
                    "Cloning Repositories"
                ),
                preferenceManager = preferenceManager,
                prefix = "git_p3"
            )

            // Phase 4: Undo & Advanced Commands
            GitRoadmapCard(
                phase = "04",
                title = "Undo & Reverting",
                items = listOf(
                    "Amending Commits (commit --amend)",
                    "Git Reset (Soft, Mixed, Hard)",
                    "Git Revert vs Git Reset",
                    "Git Checkout for Files",
                    "Git Restore & Switch"
                ),
                preferenceManager = preferenceManager,
                prefix = "git_p4"
            )

            // Phase 5: Rebase & Rewriting History
            GitRoadmapCard(
                phase = "05",
                title = "Advanced Workflows",
                items = listOf(
                    "Git Rebase",
                    "Interactive Rebase (squash, pick)",
                    "Cherry-picking Commits",
                    "Git Reflog (The Lifesaver)",
                    "Git Bisect (Debugging)"
                ),
                preferenceManager = preferenceManager,
                prefix = "git_p5"
            )

            // Phase 6: GitHub Workflow
            GitRoadmapCard(
                phase = "06",
                title = "GitHub Ecosystem",
                items = listOf(
                    "Pull Requests (PRs)",
                    "Code Review Features",
                    "GitHub Issues & Projects",
                    "GitHub Actions (CI/CD Basics)",
                    "Forking & Upstream Sync"
                ),
                preferenceManager = preferenceManager,
                prefix = "git_p6"
            )

            // Phase 7: Professional Standards
            GitRoadmapCard(
                phase = "07",
                title = "Team Standards",
                items = listOf(
                    "Git Flow vs GitHub Flow",
                    "Conventional Commits",
                    "Git Hooks (Husky)",
                    "gitignore best practices",
                    "Large File Storage (LFS)"
                ),
                preferenceManager = preferenceManager,
                prefix = "git_p7"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun GitRoadmapCard(
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = GitPrimary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = phase,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = GitPrimary
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                GitCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun GitCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = GitPrimary)
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
