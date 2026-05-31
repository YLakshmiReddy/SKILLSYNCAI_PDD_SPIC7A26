package com.simats.SkillSyncAI

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.io.OutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInfoScreen(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    var fullName by remember { mutableStateOf(preferenceManager.getUserName()) }
    var email by remember { mutableStateOf(preferenceManager.getUserEmail()) }
    var phone by remember { mutableStateOf(preferenceManager.getUserPhone()) }
    var targetRole by remember { mutableStateOf(preferenceManager.getUserRole()) }
    var imageUriString by remember { mutableStateOf(preferenceManager.getUserImageUri() ?: "") }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // In a real app, you should copy the file to internal storage to ensure persistent access
            imageUriString = it.toString()
        }
    }

    fun downloadImage() {
        if (imageUriString.isEmpty()) {
            Toast.makeText(context, "No image to download", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            val uri = Uri.parse(imageUriString)
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            
            val filename = "SkillSync_Profile_${System.currentTimeMillis()}.jpg"
            var fos: OutputStream? = null
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.contentResolver?.also { resolver ->
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/SkillSync")
                    }
                    val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    fos = imageUri?.let { resolver.openOutputStream(it) }
                }
            } else {
                // Fallback for older versions if needed, though MediaStore works for many
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                }
                val imageUri: Uri? = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { context.contentResolver.openOutputStream(it) }
            }

            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                Toast.makeText(context, "Image saved to Gallery", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to save image: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Account Information", fontWeight = FontWeight.Bold, color = Color(0xFF1A1C1E)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF1A1C1E))
                    }
                },
                actions = {
                    if (imageUriString.isNotEmpty()) {
                        IconButton(onClick = { downloadImage() }) {
                            Icon(Icons.Filled.Download, contentDescription = "Download Picture", tint = PrimaryPurpleProfile)
                        }
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
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture Section
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5))
                    .border(3.dp, PrimaryPurpleProfile, CircleShape)
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imageUriString.isEmpty()) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(70.dp),
                        tint = Color(0xFF757575)
                    )
                } else {
                    AsyncImage(
                        model = imageUriString,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(36.dp)
                        .offset(x = (-8).dp, y = (-8).dp),
                    shape = CircleShape,
                    color = PrimaryPurpleProfile
                ) {
                    Icon(
                        Icons.Filled.CameraAlt,
                        contentDescription = "Edit Picture",
                        modifier = Modifier.padding(8.dp),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Editable Fields with Darker Text
            EditableField(
                label = "Full Name",
                value = fullName,
                onValueChange = { fullName = it },
                icon = Icons.Filled.Person
            )

            Spacer(modifier = Modifier.height(20.dp))

            EditableField(
                label = "Email Address",
                value = email,
                onValueChange = { email = it },
                icon = Icons.Filled.Email
            )

            Spacer(modifier = Modifier.height(20.dp))

            EditableField(
                label = "Phone Number",
                value = phone,
                onValueChange = { phone = it },
                icon = Icons.Filled.Phone
            )

            Spacer(modifier = Modifier.height(20.dp))

            EditableField(
                label = "Target Role",
                value = targetRole,
                onValueChange = { targetRole = it },
                icon = Icons.Filled.Work
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    preferenceManager.saveProfileData(
                        fullName, email, phone, targetRole, if (imageUriString.isEmpty()) null else imageUriString
                    )
                    onBackClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryPurpleProfile)
            ) {
                Text(
                    "Save Changes",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun EditableField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121), // Much darker for better readability
            modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = { Icon(icon, contentDescription = null, tint = PrimaryPurpleProfile) },
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF1A1C1E)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryPurpleProfile,
                unfocusedBorderColor = Color(0xFF757575),
                focusedTextColor = Color(0xFF1A1C1E),
                unfocusedTextColor = Color(0xFF1A1C1E)
            ),
            singleLine = true
        )
    }
}
