package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cloud
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

val AWSBgStart = Color(0xFFF1F4FF)
val AWSBgEnd = Color(0xFFFFFFFF)
val AWSPrimary = Color(0xFFFF9900) // AWS Orange
val AWSDarkBlue = Color(0xFF232F3E) // AWS Dark Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AWSRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Cloud, null, tint = AWSPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AWSDarkBlue)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = AWSDarkBlue)
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
                .background(Brush.verticalGradient(listOf(AWSBgStart, AWSBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Surface(
                color = AWSPrimary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Text(
                    text = "LEARNING PATH",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = AWSPrimary
                )
            }

            Text(
                text = "AWS\nDeveloper\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = AWSDarkBlue,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the world's leading cloud platform. Track your progress across core services, infrastructure, and serverless design patterns.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            AWSRoadmapCard(
                phase = "01",
                title = "Cloud & AWS Basics",
                description = "Foundational knowledge and core concepts.",
                items = listOf("Cloud Foundations", "AWS Core Concepts", "Global Infrastructure - Regions & AZs"),
                preferenceManager = preferenceManager,
                prefix = "aws_p1"
            )

            // Phase 02
            AWSRoadmapCard(
                phase = "02",
                title = "Essential Infrastructure",
                description = "Core compute and secure networking.",
                items = listOf("Compute - EC2", "Networking - VPC", "Identity & Access - IAM", "Security Groups & NACLs"),
                preferenceManager = preferenceManager,
                prefix = "aws_p2"
            )

            // Phase 03
            AWSRoadmapCard(
                phase = "03",
                title = "Storage & Content Delivery",
                description = "Managing data and global distribution.",
                items = listOf("Object Storage - S3", "Content Delivery - CloudFront", "Domain Management - Route53", "EFS & EBS Storage"),
                preferenceManager = preferenceManager,
                prefix = "aws_p3"
            )

            // Phase 04
            AWSRoadmapCard(
                phase = "04",
                title = "Databases & Analytics",
                description = "Persistence and observability.",
                items = listOf("Relational - RDS", "NoSQL - DynamoDB", "ElastiCache (Redis/Memcached)", "Monitoring & Logs - CloudWatch"),
                preferenceManager = preferenceManager,
                prefix = "aws_p4"
            )

            // Phase 05
            AWSRoadmapCard(
                phase = "05",
                title = "Scalability & Containers",
                description = "High availability and orchestration.",
                items = listOf("Auto Scaling & Load Balancing (ELB)", "Container Services - ECS & EKS", "AWS Fargate (Serverless Containers)"),
                preferenceManager = preferenceManager,
                prefix = "aws_p5"
            )

            // Phase 06
            AWSRoadmapCard(
                phase = "06",
                title = "Serverless Architecture",
                description = "Event-driven and decoupled systems.",
                items = listOf("Compute - Lambda", "Integration - API Gateway", "Messaging - SQS & SNS", "Step Functions (Workflows)"),
                preferenceManager = preferenceManager,
                prefix = "aws_p6"
            )

            // Phase 07
            AWSRoadmapCard(
                phase = "07",
                title = "Security & Compliance",
                description = "Protecting your cloud environment.",
                items = listOf("AWS WAF & Shield", "Key Management Service (KMS)", "CloudTrail (Auditing)", "Secrets Manager"),
                preferenceManager = preferenceManager,
                prefix = "aws_p7"
            )

            // Phase 08
            AWSRoadmapCard(
                phase = "08",
                title = "Developer Tools & CI/CD",
                description = "Automating deployment pipelines.",
                items = listOf("CodeCommit", "CodeBuild", "CodeDeploy", "CodePipeline", "AWS X-Ray (Tracing)"),
                preferenceManager = preferenceManager,
                prefix = "aws_p8"
            )

            // Phase 09
            AWSRoadmapCard(
                phase = "09",
                title = "Infrastructure as Code",
                description = "Defining infrastructure through code.",
                items = listOf("CloudFormation", "AWS CDK (Cloud Development Kit)", "AWS SAM (Serverless Application Model)"),
                preferenceManager = preferenceManager,
                prefix = "aws_p9"
            )

            // Phase 10
            AWSRoadmapCard(
                phase = "10",
                title = "Machine Learning & AI",
                description = "Leveraging built-in AI capabilities.",
                items = listOf("Amazon SageMaker", "Amazon Rekognition", "Amazon Polly", "Amazon Lex (Chatbots)"),
                preferenceManager = preferenceManager,
                prefix = "aws_p10"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AWSRoadmapCard(
    phase: String,
    title: String,
    description: String,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Phase $phase: $title",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = AWSDarkBlue
                    )
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        lineHeight = 16.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            items.forEach { item ->
                AWSCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun AWSCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = AWSPrimary)
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
