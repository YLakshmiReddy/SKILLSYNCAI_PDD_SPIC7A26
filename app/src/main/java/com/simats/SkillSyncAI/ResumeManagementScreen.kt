package com.simats.SkillSyncAI

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.io.OutputStream

data class ResumeTemplate(
    val id: Int,
    val name: String,
    val role: String,
    val color: Color,
    val description: String = "A clean and professional template designed specifically for $role roles in the modern job market."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumeManagementScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val templates = remember {
        listOf(
            ResumeTemplate(1, "Tech Innovator", "Software Engineer", Color(0xFFE3F2FD)),
            ResumeTemplate(2, "Design Flow", "UI/UX Designer", Color(0xFFF3E5F5)),
            ResumeTemplate(3, "Data Guru", "Data Scientist", Color(0xFFE8F5E9)),
            ResumeTemplate(4, "Product Roadmap", "Product Manager", Color(0xFFFFF3E0)),
            ResumeTemplate(5, "Scale Master", "DevOps Engineer", Color(0xFFE0F2F1)),
            ResumeTemplate(6, "Business Core", "Business Analyst", Color(0xFFEFEBE9)),
            ResumeTemplate(7, "Pixel Perfect", "Frontend Developer", Color(0xFFF1F8E9)),
            ResumeTemplate(8, "Logic Builder", "Backend Developer", Color(0xFFFFFDE7)),
            ResumeTemplate(9, "Intelligence", "ML Engineer", Color(0xFFE1F5FE)),
            ResumeTemplate(10, "Safe Guard", "Security Specialist", Color(0xFFFFEBEE)),
            ResumeTemplate(11, "Mobile Native", "Android Developer", Color(0xFFF0F4C3)),
            ResumeTemplate(12, "Swift Elegant", "iOS Developer", Color(0xFFE1BEE7)),
            ResumeTemplate(13, "Cloud Scale", "Cloud Architect", Color(0xFFB2EBF2)),
            ResumeTemplate(14, "Quality First", "QA Engineer", Color(0xFFDCEDC8)),
            ResumeTemplate(15, "Stack Master", "Full Stack Developer", Color(0xFFFFCCBC)),
            ResumeTemplate(16, "Data Vault", "Database Admin", Color(0xFFD1C4E9)),
            ResumeTemplate(17, "Project Lead", "Project Manager", Color(0xFFB3E5FC)),
            ResumeTemplate(18, "Shell Script", "System Administrator", Color(0xFFC8E6C9)),
            ResumeTemplate(19, "Level Up", "Game Developer", Color(0xFFF8BBD0)),
            ResumeTemplate(20, "Connected", "Network Engineer", Color(0xFFFFE0B2)),
            ResumeTemplate(21, "Shield Pro", "Cyber Security Analyst", Color(0xFFECEFF1)),
            ResumeTemplate(22, "Draft Master", "Technical Writer", Color(0xFFF5F5F5)),
            ResumeTemplate(23, "People First", "HR Manager", Color(0xFFFCE4EC)),
            ResumeTemplate(24, "Growth Hack", "Marketing Specialist", Color(0xFFE0F7FA))
        )
    }

    var selectedTemplate by remember { mutableStateOf<ResumeTemplate?>(null) }

    fun downloadResume(template: ResumeTemplate) {
        try {
            val bitmap = Bitmap.createBitmap(1200, 1600, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val paint = Paint()
            
            canvas.drawColor(android.graphics.Color.WHITE)
            
            paint.color = template.color.toArgb()
            canvas.drawRect(0f, 0f, 1200f, 250f, paint)
            
            paint.color = android.graphics.Color.BLACK
            paint.textSize = 60f
            paint.isFakeBoldText = true
            canvas.drawText(template.name, 80f, 130f, paint)
            
            paint.textSize = 40f
            paint.isFakeBoldText = false
            paint.color = android.graphics.Color.DKGRAY
            canvas.drawText(template.role, 80f, 200f, paint)
            
            paint.color = android.graphics.Color.BLACK
            paint.textSize = 45f
            paint.isFakeBoldText = true
            canvas.drawText("PROFESSIONAL SUMMARY", 80f, 350f, paint)
            paint.strokeWidth = 3f
            canvas.drawLine(80f, 370f, 1120f, 370f, paint)
            
            paint.textSize = 30f
            paint.isFakeBoldText = false
            canvas.drawText(template.description, 80f, 430f, paint)
            
            canvas.drawText("WORK EXPERIENCE", 80f, 550f, paint)
            canvas.drawLine(80f, 570f, 1120f, 570f, paint)
            canvas.drawText("Senior ${template.role} at Tech Solutions (2020-Present)", 80f, 630f, paint)
            canvas.drawText("Junior ${template.role} at Startup Inc (2018-2020)", 80f, 680f, paint)
            
            canvas.drawText("SKILLS", 80f, 800f, paint)
            canvas.drawLine(80f, 820f, 1120f, 820f, paint)
            canvas.drawText("Expert proficiency in ${template.role} domain and tools.", 80f, 880f, paint)
            
            val filename = "Resume_${template.name.replace(" ", "_")}_${System.currentTimeMillis()}.jpg"
            var fos: OutputStream? = null
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/SkillSyncResumes")
                }
                val uri = context.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
                fos = uri?.let { context.contentResolver.openOutputStream(it) }
            } else {
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                }
                val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = uri?.let { context.contentResolver.openOutputStream(it) }
            }

            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                Toast.makeText(context, "Saved to Downloads/SkillSyncResumes", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Download failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resume Management", fontWeight = FontWeight.Bold, color = Color(0xFF1A1C1E)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF1A1C1E))
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
                .background(Color.White)
        ) {
            Text(
                text = "SELECT A TEMPLATE TO PREVIEW",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF616161),
                letterSpacing = 1.2.sp,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 32.dp)
            ) {
                items(templates) { template ->
                    TemplateItem(
                        template = template,
                        onCardClick = { selectedTemplate = template },
                        onDownloadClick = { downloadResume(template) }
                    )
                }
            }
        }
    }

    selectedTemplate?.let { template ->
        PreviewDialog(
            template = template,
            onDismiss = { selectedTemplate = null },
            onDownload = { downloadResume(template) }
        )
    }
}

@Composable
fun TemplateItem(
    template: ResumeTemplate,
    onCardClick: () -> Unit,
    onDownloadClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .clickable { onCardClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(template.color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Description,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp),
                    tint = template.color.darken(0.5f)
                )
            }

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = template.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF1A1C1E),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = template.role,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF616161),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(10.dp))
                
                Button(
                    onClick = { onDownloadClick() },
                    modifier = Modifier.fillMaxWidth().height(38.dp),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Download", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun PreviewDialog(
    template: ResumeTemplate,
    onDismiss: () -> Unit,
    onDownload: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = template.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFF1A1C1E)
                        )
                        Text(
                            text = "Full Preview (Scroll to view)",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .border(1.dp, Color.LightGray)
                        .background(Color.White)
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(modifier = Modifier.fillMaxWidth().height(150.dp).background(template.color)) {
                        Column(modifier = Modifier.align(Alignment.Center).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(template.name.uppercase(), fontWeight = FontWeight.ExtraBold, fontSize = 28.sp, color = Color.Black)
                            Text(template.role.uppercase(), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.DarkGray)
                        }
                    }
                    
                    Column(modifier = Modifier.padding(24.dp)) {
                        ResumePreviewSection("PROFESSIONAL SUMMARY")
                        Text(template.description, fontSize = 14.sp, color = Color.DarkGray, lineHeight = 20.sp)
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        ResumePreviewSection("WORK EXPERIENCE")
                        ResumePreviewItem("Senior ${template.role}", "Global Technology Solutions", "Jan 2021 - Present")
                        Text("Led a team of specialists to implement high-impact solutions. Improved organizational efficiency by 30% through strategic planning and execution.", fontSize = 13.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 12.dp))
                        
                        ResumePreviewItem("Junior ${template.role}", "Innovative Systems Ltd.", "June 2018 - Dec 2020")
                        Text("Supported core project initiatives and collaborated with cross-functional teams to deliver quality results.", fontSize = 13.sp, color = Color.Gray)
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        ResumePreviewSection("SKILLS & TOOLS")
                        Text("• Industry Knowledge: ${template.role} Methodologies, Strategic Planning", fontSize = 14.sp)
                        Text("• Technical Skills: Advanced proficiency in core industry software and tools", fontSize = 14.sp)
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        ResumePreviewSection("EDUCATION")
                        ResumePreviewItem("B.S. in Professional Studies", "National University", "2014 - 2018")
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        ResumePreviewSection("PROJECTS")
                        Text("Key Project Alpha: Orchestrated the digital transformation for a retail client, resulting in a 50% increase in online sales.", fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Community Initiative: Developed a local mentorship program for aspiring professionals in the ${template.role} field.", fontSize = 14.sp)
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        ResumePreviewSection("AWARDS & CERTIFICATIONS")
                        Text("• Employee of the Year (2022)", fontSize = 14.sp)
                        Text("• Professional Excellence Certification", fontSize = 14.sp)
                        
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Close", fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { 
                            onDownload()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
                    ) {
                        Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Download", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun ResumePreviewSection(title: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF673AB7), letterSpacing = 1.sp)
        HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
    }
}

@Composable
fun ResumePreviewItem(title: String, subtitle: String, date: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF212121))
            Text(date, fontSize = 12.sp, color = Color.Gray)
        }
        Text(subtitle, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = Color(0xFF616161))
    }
}

private fun Color.darken(factor: Float): Color {
    return Color(
        red = this.red * (1 - factor),
        green = this.green * (1 - factor),
        blue = this.blue * (1 - factor),
        alpha = this.alpha
    )
}
