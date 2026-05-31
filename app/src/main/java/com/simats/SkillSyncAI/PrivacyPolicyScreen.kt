package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.ShowChart
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
fun PrivacyPolicyScreen(
    onBackClick: () -> Unit,
    onDeclineClick: () -> Unit,
    onAcceptClick: () -> Unit,
    onContactTeamClick: () -> Unit
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
                text = "Privacy Policy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
        }

        // Documentation Banner
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
                    color = Color(0xFF3B82F6)
                )
                Text(
                    text = "DOCUMENTATION",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3B82F6)
                )
            }
            Surface(
                color = Color(0xFFDBEAFE),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Version 2.4",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
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
                    text = "Privacy Policy",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = "Last updated: October 2023",
                    fontSize = 14.sp,
                    color = Color(0xFF64748B),
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Welcome to SkillSync AI. Your privacy is critically important to us. This policy explains how we handle your personal data across our platform, including our website, mobile application, and related AI-driven recruitment services. We are committed to protecting your personal information and your right to privacy.",
                    fontSize = 14.sp,
                    color = Color(0xFF475569),
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                PrivacySection(
                    icon = Icons.Default.PermIdentity,
                    title = "1. Information Collection",
                    content = "We collect personal information that you voluntarily provide to us when you register on the Services, express an interest in obtaining information about us or our products, or otherwise when you contact us."
                )
                
                BulletPoint("Personal Identifiers: Name, email address, phone number, and professional social media profiles.")
                BulletPoint("Professional Data: Employment history, education, skills, certifications, and resume documents.")
                BulletPoint("Technical Data: IP addresses, browser types, device information, and usage patterns collected via cookies and similar technologies.")

                Spacer(modifier = Modifier.height(24.dp))

                PrivacySection(
                    icon = Icons.Default.ShowChart,
                    title = "2. Use of Data",
                    content = "SkillSync AI uses the collected data for various purposes to enhance your experience and provide our core services:"
                )
                
                BulletPoint("To facilitate account creation and the logon process.")
                BulletPoint("To match your skills and profile with relevant job opportunities using our AI algorithms.")
                BulletPoint("To provide personalized career recommendations and skill gap analysis.")
                BulletPoint("To send administrative information, such as security alerts or product updates.")
                BulletPoint("To improve our AI models and platform performance through anonymized aggregate data.")

                Spacer(modifier = Modifier.height(24.dp))

                PrivacySection(
                    icon = Icons.Default.Security,
                    title = "3. Data Protection",
                    content = "We implement a variety of security measures to maintain the safety of your personal information. We use state-of-the-art encryption (AES-256) for data at rest and TLS for data in transit."
                )

                Surface(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Security Commitment",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF0F172A)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "While we strive to use commercially acceptable means to protect your personal information, no method of transmission over the internet is 100% secure. We conduct regular security audits and vulnerability assessments to ensure the highest level of protection.",
                            fontSize = 12.sp,
                            color = Color(0xFF475569),
                            lineHeight = 18.sp
                        )
                    }
                }

                PrivacySection(
                    icon = Icons.Default.Hub,
                    title = "4. Third-Party Services",
                    content = "We may share your information with third-party vendors, service providers, contractors, or agents who perform services for us or on our behalf and require access to such information to do that work.\n\nExamples include:"
                )
                
                BulletPoint("Cloud hosting providers (AWS/Google Cloud).")
                BulletPoint("Analytics tools (Google Analytics/Mixpanel).")
                BulletPoint("Payment processors (Stripe).")
                BulletPoint("Email delivery services (SendGrid).")
                
                Text(
                    text = "We do not sell your personal data to third parties for marketing purposes.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(140.dp))
            }

            // Floating footer
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color(0xFFF8FAFC).copy(alpha = 0.95f))
            ) {
                Surface(
                    modifier = Modifier
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
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                            ) {
                                Text("Decline", color = Color(0xFF475569), fontSize = 13.sp)
                            }
                            Button(
                                onClick = onAcceptClick,
                                modifier = Modifier.weight(1.2f).height(48.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))
                            ) {
                                Text("Accept & Continue", fontSize = 13.sp, maxLines = 1)
                            }
                        }
                    }
                }
                
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Have questions about our privacy practices?",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onContactTeamClick,
                        modifier = Modifier.height(40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Contact Privacy Team", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun PrivacySection(
    icon: ImageVector,
    title: String,
    content: String
) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(Color(0xFFDBEAFE), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF3B82F6),
                    modifier = Modifier.size(18.dp)
                )
            }
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

@Composable
fun BulletPoint(text: String) {
    Row(modifier = Modifier.padding(start = 12.dp, top = 8.dp)) {
        Text(text = "•", color = Color(0xFF3B82F6), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 13.sp,
            color = Color(0xFF64748B),
            lineHeight = 20.sp
        )
    }
}
