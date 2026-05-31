package com.simats.SkillSyncAI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BestPracticesScreen(
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val primaryBlue = Color(0xFF1D4ED8)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Best Practices Hub", 
                        fontSize = 18.sp, 
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1C1E)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF1F5F9))
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            // Header Section
            Text(
                text = "Master the\nDigital Canyon",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1D4ED8),
                    lineHeight = 40.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Professional guides on clean code, system design, and AI ethics to ensure your skills meet professional industry standards.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0xFF64748B),
                    lineHeight = 24.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 1. Programming Languages
            BestPracticeCard(
                icon = Icons.Default.Code,
                title = "Programming Languages",
                points = listOf(
                    "Use the right tool for the job.",
                    "Master the fundamentals before moving to libraries.",
                    "Focus on readability and maintainability.",
                    "Stay updated with latest language features.",
                    "Write unit tests for your code.",
                    "Follow language-specific style guides (PEP8, Google Style).",
                    "Understand memory management and performance trade-offs."
                ),
                tags = listOf("JAVA", "PYTHON", "C++", "JAVASCRIPT", "GO", "RUST")
            )

            // 2. Frameworks & Ecosystems
            BestPracticeCard(
                icon = Icons.Default.Layers,
                title = "Frameworks & Ecosystems",
                points = listOf(
                    "Understand the underlying framework concepts.",
                    "Choose frameworks with strong community support.",
                    "Avoid over-engineering with too many libraries.",
                    "Keep dependencies updated.",
                    "Use official documentation as primary source.",
                    "Leverage framework-specific CLI tools and scaffolding.",
                    "Master the dependency injection and lifecycle management."
                ),
                tags = listOf("SPRING", "REACT", "DJANGO", "KUBERNETES", "TENSORFLOW")
            )

            // 3. Frontend & UI Design
            BestPracticeCard(
                icon = Icons.Default.Palette,
                title = "Frontend & UI Design",
                points = listOf(
                    "Design with accessibility in mind (WCAG).",
                    "Ensure responsive design across all devices.",
                    "Optimize assets for fast loading.",
                    "Use consistent design patterns.",
                    "Prioritize user experience (UX).",
                    "Stay updated with design trends.",
                    "Master CSS and styling frameworks.",
                    "Implement state management effectively (Redux, Vuex, Bloc).",
                    "Conduct usability testing early and often."
                ),
                tags = listOf("HTML5", "CSS3", "FIGMA", "RESPONSIVE"),
                hasImage = true
            )

            // 4. Backend & Infrastructure Engineering
            BestPracticeCard(
                icon = Icons.Default.Dns,
                title = "Backend & Infrastructure Engineering",
                points = listOf(
                    "Build scalable and resilient systems.",
                    "Use microservices architecture for complexity.",
                    "Implement robust logging and monitoring.",
                    "Ensure high availability and fault tolerance.",
                    "Optimize database performance.",
                    "Implement caching strategies (Redis, Memcached).",
                    "Secure APIs with proper authentication and rate limiting."
                ),
                tags = listOf("SQL", "DOCKER", "AWS", "REST", "GRPC")
            )

            // 5. DevOps, Security & Reliability
            BestPracticeCard(
                icon = Icons.Default.Security,
                title = "DevOps, Security & Reliability",
                points = listOf(
                    "Automate everything from build to deployment.",
                    "Implement CI/CD pipelines.",
                    "Prioritize security from the start.",
                    "Use infrastructure as code (IaC).",
                    "Monitor and respond to incidents.",
                    "Perform regular security audits and penetration tests.",
                    "Implement Secrets Management (HashiCorp Vault, AWS Secrets Manager)."
                ),
                tags = listOf("LINUX", "KUBERNETES", "TERRAFORM", "SECURITY")
            )

            // 6. AI, Machine Learning & Data Science
            BestPracticeCard(
                icon = Icons.Default.Psychology,
                title = "AI, Machine Learning & Data Science",
                points = listOf(
                    "Understand data quality and pre-processing.",
                    "Choose the right algorithms for the problem.",
                    "Monitor model performance and bias.",
                    "Use version control for data and models.",
                    "Stay updated with AI research.",
                    "Explainability and transparency in AI models (XAI).",
                    "Implement robust data validation and cleaning pipelines."
                ),
                tags = emptyList(),
                subBox = {
                    Surface(
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Model Deployment & MLOps",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "• Scalable inference with GPUs.\n• CI/CD for ML models.\n• Feature Stores and Model Registries.",
                                fontSize = 13.sp,
                                color = Color(0xFF64748B),
                                lineHeight = 20.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                "SCIPY | PYTORCH | NLP | LLM",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1D4ED8)
                            )
                        }
                    }
                }
            )

            // 7. Database Systems
            BestPracticeCard(
                icon = Icons.Default.Storage,
                title = "Database Systems",
                points = listOf(
                    "Choose the right database (SQL vs NoSQL).",
                    "Indexing for performance.",
                    "Database security and backup.",
                    "Normalization vs Denormalization.",
                    "Distributed databases and consistency.",
                    "Monitor database health and slow queries.",
                    "Implement database migration tools (Flyway, Liquibase)."
                ),
                tags = listOf("POSTGRES", "MONGODB", "REDIS", "CASSANDRA")
            )

            // 8. Software Architecture
            BestPracticeCard(
                icon = Icons.Default.Memory,
                title = "Software Architecture",
                points = listOf(
                    "Design for change and extensibility.",
                    "SOLID and DRY principles.",
                    "Design patterns for common problems.",
                    "Distributed systems design.",
                    "Cloud native architecture.",
                    "Use architectural decision records (ADRs).",
                    "Focus on domain-driven design (DDD)."
                ),
                tags = listOf("MICROSERVICES", "EVENT-DRIVEN", "CLEAN-CODE")
            )

            // 9. Soft Skills & Career
            BestPracticeCard(
                icon = Icons.Default.Groups,
                title = "Soft Skills & Career",
                points = listOf(
                    "Communication and collaboration.",
                    "Time management and productivity.",
                    "Continuous learning and growth.",
                    "Networking and mentoring.",
                    "Emotional intelligence and empathy.",
                    "Conflict resolution and negotiation.",
                    "Building a personal brand and portfolio."
                ),
                tags = listOf("AGILE", "LEADERSHIP", "PUBLIC-SPEAKING")
            )
            
            // 10. Testing & Quality Assurance
            BestPracticeCard(
                icon = Icons.Default.CheckCircle,
                title = "Testing & Quality Assurance",
                points = listOf(
                    "Implement the testing pyramid (Unit, Integration, E2E).",
                    "Practice Test-Driven Development (TDD).",
                    "Use automated testing frameworks.",
                    "Focus on test coverage and quality metrics.",
                    "Perform manual exploratory testing.",
                    "Implement mutation testing.",
                    "Use linters and static analysis tools."
                ),
                tags = listOf("JUNIT", "SELENIUM", "JEST", "CYPRESS")
            )

            // 11. Cloud Computing & Platforms
            BestPracticeCard(
                icon = Icons.Default.Cloud,
                title = "Cloud Computing & Platforms",
                points = listOf(
                    "Understand cloud service models (IaaS, PaaS, SaaS).",
                    "Leverage serverless architectures (Lambda, Cloud Functions).",
                    "Optimize cloud costs and resource usage.",
                    "Implement multi-cloud or hybrid cloud strategies.",
                    "Master cloud provider specific tools and services.",
                    "Design for regional availability and failover.",
                    "Use Managed Services to reduce operational overhead."
                ),
                tags = listOf("AWS", "AZURE", "GCP", "SERVERLESS")
            )

            // 12. Mobile Development
            BestPracticeCard(
                icon = Icons.Default.Smartphone,
                title = "Mobile Development",
                points = listOf(
                    "Optimize app performance and battery life.",
                    "Ensure seamless offline experience.",
                    "Follow platform-specific design guidelines (Material Design, HIG).",
                    "Master native vs cross-platform development trade-offs.",
                    "Implement robust crash reporting and analytics.",
                    "Design for various screen sizes and orientations.",
                    "Secure user data and app communications."
                ),
                tags = listOf("KOTLIN", "SWIFT", "FLUTTER", "REACT-NATIVE")
            )

            // 13. API Design & Management
            BestPracticeCard(
                icon = Icons.Default.Api,
                title = "API Design & Management",
                points = listOf(
                    "Follow RESTful principles for web APIs.",
                    "Use GraphQL for efficient data fetching.",
                    "Provide comprehensive API documentation (Swagger/OpenAPI).",
                    "Implement API versioning and backward compatibility.",
                    "Monitor API usage and performance.",
                    "Secure APIs with OAuth2 and JWT.",
                    "Leverage API Gateways for management and security."
                ),
                tags = listOf("REST", "GRAPHQL", "SWAGGER", "POSTMAN")
            )

            // 14. Agile & Project Management
            BestPracticeCard(
                icon = Icons.Default.Speed,
                title = "Agile & Project Management",
                points = listOf(
                    "Embrace Agile values and principles.",
                    "Participate in Scrum or Kanban ceremonies.",
                    "Break down work into manageable tasks.",
                    "Prioritize product backlogs effectively.",
                    "Focus on continuous delivery and feedback.",
                    "Use project management tools (Jira, Trello, Linear).",
                    "Foster a culture of transparency and accountability."
                ),
                tags = listOf("SCRUM", "KANBAN", "JIRA", "PRODUCTIVITY")
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun BestPracticeCard(
    icon: ImageVector,
    title: String,
    points: List<String>,
    tags: List<String>,
    hasImage: Boolean = false,
    subBox: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFDBEAFE),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    title, 
                    fontWeight = FontWeight.Bold, 
                    fontSize = 17.sp, 
                    color = Color(0xFF0F172A)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            points.forEach { point ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        "•", 
                        modifier = Modifier.padding(end = 8.dp),
                        color = Color(0xFF1D4ED8),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        point,
                        fontSize = 14.sp,
                        color = Color(0xFF64748B),
                        lineHeight = 20.sp
                    )
                }
            }
            
            if (hasImage) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.bg_mountains),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            subBox?.invoke()

            if (tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    tags.joinToString(" | "),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D4ED8)
                )
            }
        }
    }
}
