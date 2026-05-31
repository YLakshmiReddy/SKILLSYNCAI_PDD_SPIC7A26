package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TermsOfServiceScreen(
    onBackClick: () -> Unit,
    onDeclineClick: () -> Unit,
    onAcceptClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF0F172A)
                )
            }
            Text(
                text = "Terms of Service",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
        }

        // Compliance Banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F5F9))
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "SKILLSYNC AI LEGAL",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF475569)
                )
                Text(
                    text = "COMPLIANCE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF475569)
                )
            }
            Surface(
                color = Color(0xFFDBEAFE),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "Version 2.4",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2563EB)
                )
            }
        }
        
        // Horizontal Blue Line
        Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color(0xFF3B82F6)))

        // Content
        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp)
            ) {
                Text(
                    text = "SkillSync AI Terms of Service",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = "Last updated: October 24, 2023",
                    fontSize = 14.sp,
                    color = Color(0xFF64748B),
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                TermSection(
                    icon = Icons.Default.Info,
                    title = "1. User Agreement",
                    content = "By accessing or using SkillSync AI, you agree to be bound by these Terms of Service and all applicable laws and regulations. If you do not agree with any of these terms, you are prohibited from using or accessing this site.\n\nThe materials contained in this website are protected by applicable copyright and trademark law. We grant you a personal, non-exclusive, non-transferable, limited privilege to enter and use the service."
                )

                TermSection(
                    icon = Icons.Default.PrivacyTip,
                    title = "2. Data Privacy",
                    content = "Your privacy is important to us. SkillSync AI's Privacy Policy is incorporated into these Terms by reference. Please review our Privacy Policy to understand how we collect, use, and share information about you.\n\nWe implement industry-standard security measures to protect your data, but no method of transmission over the Internet is 100% secure. You are responsible for maintaining the confidentiality of your account credentials."
                )

                TermSection(
                    icon = Icons.Default.Warning,
                    title = "3. Limitations of Liability",
                    content = "In no event shall SkillSync AI or its suppliers be liable for any damages (including, without limitation, damages for loss of data or profit, or due to business interruption) arising out of the use or inability to use the materials on SkillSync AI's website.\n\nSkillSync AI does not warrant that any of the materials on its website are accurate, complete, or current. We may make changes to the materials contained on its website at any time without notice."
                )

                TermSection(
                    icon = Icons.Default.AccessTime,
                    title = "4. Changes to Terms",
                    content = "SkillSync AI may revise these terms of service for its website at any time without notice. By using this website you are agreeing to be bound by the then current version of these terms of service.\n\nWe encourage users to frequently check this page for any changes. Your continued use of the service after any modification to these Terms will constitute your acceptance of such modification."
                )
                
                Spacer(modifier = Modifier.height(120.dp)) // Space for the footer card
            }

            // Footer Card
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF1F5F9),
                shadowElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ready to proceed?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "By continuing, you confirm you've read the agreement.",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = onDeclineClick,
                            modifier = Modifier.weight(1f).height(48.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                            contentPadding = PaddingValues(horizontal = 4.dp)
                        ) {
                            Text("Decline", color = Color(0xFF475569), fontSize = 13.sp)
                        }
                        Button(
                            onClick = onAcceptClick,
                            modifier = Modifier.weight(1.2f).height(48.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
                            contentPadding = PaddingValues(horizontal = 4.dp)
                        ) {
                            Text(
                                text = "Accept & Continue",
                                fontSize = 13.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TermSection(
    icon: ImageVector,
    title: String,
    content: String
) {
    Column(modifier = Modifier.padding(bottom = 32.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF3B82F6),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = content,
            fontSize = 14.sp,
            color = Color(0xFF475569),
            lineHeight = 22.sp
        )
    }
}
