package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Coffee
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

val JavaBgStart = Color(0xFFF8F9FF)
val JavaBgEnd = Color(0xFFFFFFFF)
val JavaPrimary = Color(0xFF5382A1) // Java Blue
val JavaOrange = Color(0xFFE76F00) // Java Orange
val JavaDark = Color(0xFF1A1A1A)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JavaRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Coffee, null, tint = JavaOrange, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Java Architect", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = JavaDark)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = JavaDark)
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
                .background(Brush.verticalGradient(listOf(JavaBgStart, JavaBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Development\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = JavaDark,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Master the journey from fundamentals to enterprise architecture. A structured path for modern Java Engineers.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 1
            JavaRoadmapCard(
                phase = "01",
                title = "Learn the Basics",
                items = listOf(
                    "Fundamentals (Variables, Data Types, Operators)",
                    "Core Structures (Loops, Conditionals, Arrays)",
                    "Input/Output (Scanner, Formatter)",
                    "Methods, Parameters & Return Types",
                    "Java Memory Basics (Stack vs Heap)"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p1"
            )

            // Phase 2
            JavaRoadmapCard(
                phase = "02",
                title = "OOP Deep Dive",
                items = listOf(
                    "Fundamentals (Classes, Objects, Packages)",
                    "Advanced (Inheritance, Polymorphism)",
                    "Interfaces & Abstract Classes",
                    "Encapsulation & Access Modifiers",
                    "Composition vs Inheritance",
                    "Inner Classes & Static Members"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p2"
            )

            // Phase 3
            JavaRoadmapCard(
                phase = "03",
                title = "Functional Programming",
                items = listOf(
                    "Lambdas & Functional Interfaces",
                    "Streams API (Intermediate & Terminal ops)",
                    "Modern Language Features (Records, Sealed Classes)",
                    "Optional Class Usage",
                    "Method References",
                    "Default & Static Interface Methods"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p3"
            )

            // Phase 4
            JavaRoadmapCard(
                phase = "04",
                title = "Collections & Concurrency",
                items = listOf(
                    "List, Set, Map & Queue Implementations",
                    "Threads & Thread Lifecycle",
                    "Synchronization & Locks (ReentrantLock)",
                    "Concurrent Utils (Atomic, CountDownLatch, CyclicBarrier)",
                    "Executors, ThreadPools & ForkJoinPool",
                    "CompletableFuture & Virtual Threads (Project Loom)"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p4"
            )

            // Phase 5
            JavaRoadmapCard(
                phase = "05",
                title = "JVM Internals & Performance",
                items = listOf(
                    "Class Loaders & Bytecode",
                    "Garbage Collection Algorithms (G1, ZGC)",
                    "JIT Compiler & HotSpot",
                    "Profiling Tools (JVisualVM, JProfiler)",
                    "Memory Leak Analysis & Heap Dumps"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p5"
            )

            // Phase 6
            JavaRoadmapCard(
                phase = "06",
                title = "System Operations & Utilities",
                items = listOf(
                    "File I/O & NIO2",
                    "Networking (HTTP Client, Sockets)",
                    "Serialization (JSON, XML, Protocol Buffers)",
                    "Reflection API & Proxying",
                    "Annotations & Custom Processors"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p6"
            )

            // Phase 7
            JavaRoadmapCard(
                phase = "07",
                title = "Enterprise Ecosystem",
                items = listOf(
                    "Spring Framework & Spring Boot",
                    "Microservices Patterns (API Gateway, Circuit Breaker)",
                    "Persistence (Hibernate, JPA, Spring Data)",
                    "RESTful APIs, GraphQL & gRPC",
                    "Messaging (Kafka, RabbitMQ, ActiveMQ)",
                    "Cloud Native Java (Quarkus, Micronaut)"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p7"
            )

            // Phase 8
            JavaRoadmapCard(
                phase = "08",
                title = "Security & Best Practices",
                items = listOf(
                    "Java Security Manager & Cryptography",
                    "Spring Security (OAuth2, JWT)",
                    "OWASP Top 10 for Java Developers",
                    "Secure Coding Guidelines",
                    "Dependency Vulnerability Scanning"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p8"
            )

            // Phase 9
            JavaRoadmapCard(
                phase = "09",
                title = "Testing & Quality",
                items = listOf(
                    "Unit Testing (JUnit 5, Mockito, AssertJ)",
                    "Integration Testing (Testcontainers)",
                    "Logging (SLF4J, Logback, Log4j2)",
                    "Static Analysis (SonarQube, Checkstyle)",
                    "Code Coverage (JaCoCo)"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p9"
            )

            // Phase 10
            JavaRoadmapCard(
                phase = "10",
                title = "Deployment & DevOps",
                items = listOf(
                    "Build Tools (Maven, Gradle Kotlin DSL)",
                    "CI/CD Pipelines (Jenkins, GitHub Actions)",
                    "Dockerization of Java Apps",
                    "Kubernetes Deployment & Helm",
                    "Monitoring (Micrometer, Prometheus, Grafana)"
                ),
                preferenceManager = preferenceManager,
                prefix = "java_p10"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun JavaRoadmapCard(
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
            Text(
                text = "PHASE $phase",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F51B5)
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = JavaDark
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                JavaCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun JavaCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3F51B5))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item,
            fontSize = 14.sp,
            color = if (checked) Color.Gray else JavaDark,
            modifier = Modifier.weight(1f)
        )
    }
}
