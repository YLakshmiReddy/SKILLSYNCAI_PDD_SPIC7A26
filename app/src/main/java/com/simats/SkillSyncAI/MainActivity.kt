package com.simats.SkillSyncAI

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.simats.SkillSyncAI.ui.theme.SkillsyncaiappTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ScreenState {
    Splash,
    Onboarding,
    Welcome,
    SignIn,
    SignUp,
    ForgotPassword,
    Home,
    Terms,
    Privacy,
    ContactPrivacy,
    Analysis,
    AnalysisResult,
    Roadmap,
    UploadResume,
    Profile,
    AccountInfo,
    AppSettings,
    ChangePassword,
    ResumeManagement,
    HelpSupport,
    ProjectIdeas,
    BestPractices,
    RoleBasedRoadmaps,
    SkillBasedRoadmaps,
    FrontendRoadmap,
    BackendRoadmap,
    FullStackRoadmap,
    AIEngineerRoadmap,
    DevOpsRoadmap,
    UXDesignRoadmap,
    CyberSecurityRoadmap,
    GameDevRoadmap,
    ServerSideGameDevRoadmap,
    MLOpsRoadmap,
    ProductManagementRoadmap,
    DevSecOpsRoadmap,
    DataAnalystRoadmap,
    BIAnalystRoadmap,
    DevRelRoadmap,
    DataScientistRoadmap,
    DataEngineerRoadmap,
    MachineLearningRoadmap,
    PostgresRoadmap,
    IOSRoadmap,
    AndroidRoadmap,
    BlockchainRoadmap,
    QAEngineerRoadmap,
    SoftwareArchitectRoadmap,
    TechnicalWriterRoadmap,
    AIAgentsRoadmap,
    AIRedTeamingRoadmap,
    APIDesignRoadmap,
    AspNetCoreRoadmap,
    AWSRoadmap,
    AngularRoadmap,
    AzureRoadmap,
    CppRoadmap,
    CssRoadmap,
    CloudflareRoadmap,
    CodeReviewRoadmap,
    ComputerScienceRoadmap,
    DsaRoadmap,
    DesignSystemRoadmap,
    DockerRoadmap,
    FlutterRoadmap,
    GcpRoadmap,
    GitRoadmap,
    GoRoadmap,
    GraphqlRoadmap,
    HtmlRoadmap,
    JavaRoadmap,
    KotlinRoadmap,
    KubernetesRoadmap,
    JavascriptRoadmap,
    LinuxRoadmap,
    MongodbRoadmap,
    NextjsRoadmap,
    NoSqlRoadmap,
    NodejsRoadmap,
    PhpRoadmap,
    PromptEngineeringRoadmap,
    PythonRoadmap,
    ReactNativeRoadmap,
    RustRoadmap,
    ReactRoadmap,
    RedisRoadmap,
    SqlRoadmap,
    SpringBootRoadmap,
    SoftwareDesignRoadmap,
    SystemDesignRoadmap,
    TypeScriptRoadmap,
    VueRoadmap,
    TerraformRoadmap
}

class MainActivity : ComponentActivity() {

    // ── Google Sign-In setup ───────────────────────────────────────────────
    // Replace with your Web Client ID from Google Cloud Console:
    // console.cloud.google.com → SkillSyncAI → APIs & Services → Credentials
    private val WEB_CLIENT_ID = "198439672213-7vh6lioc1e903ies4eajgbui517sle9g.apps.googleusercontent.com"

    private lateinit var googleSignInClient: GoogleSignInClient
    private var onGoogleResult: ((String?, String?, String?) -> Unit)? = null

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            onGoogleResult?.invoke(account?.idToken, account?.email, account?.displayName)
        } catch (e: ApiException) {
            onGoogleResult?.invoke(null, null, null)
        }
    }

    private fun launchGoogleSignIn(callback: (idToken: String?, email: String?, name: String?) -> Unit) {
        onGoogleResult = callback
        googleSignInClient.signOut().addOnCompleteListener {
            googleSignInLauncher.launch(googleSignInClient.signInIntent)
        }
    }
    // ─────────────────────────────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(WEB_CLIENT_ID)
            .requestEmail()
            .requestProfile()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Enable edge-to-edge
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.BLACK
            )
        )

        setContent {
            SkillsyncaiappTheme {
                val context = LocalContext.current
                val preferenceManager = remember { PreferenceManager(context) }
                var currentScreen by remember { mutableStateOf(ScreenState.Splash) }

                // Global Back Navigation Handling
                BackHandler(enabled = currentScreen != ScreenState.Splash && currentScreen != ScreenState.Onboarding && currentScreen != ScreenState.Home) {
                    currentScreen = when (currentScreen) {
                        ScreenState.Welcome -> ScreenState.Onboarding
                        ScreenState.SignIn -> ScreenState.Welcome
                        ScreenState.SignUp -> ScreenState.Welcome
                        ScreenState.ForgotPassword -> ScreenState.SignIn
                        ScreenState.Terms -> ScreenState.Welcome
                        ScreenState.Privacy -> ScreenState.Welcome
                        ScreenState.ContactPrivacy -> ScreenState.Privacy
                        ScreenState.Analysis -> ScreenState.Home
                        ScreenState.AnalysisResult -> ScreenState.Analysis
                        ScreenState.Roadmap -> ScreenState.Home
                        ScreenState.UploadResume -> ScreenState.Home
                        ScreenState.Profile -> ScreenState.Home
                        ScreenState.AccountInfo -> ScreenState.Profile
                        ScreenState.AppSettings -> ScreenState.Profile
                        ScreenState.ChangePassword -> ScreenState.AppSettings
                        ScreenState.ResumeManagement -> ScreenState.Profile
                        ScreenState.HelpSupport -> ScreenState.Profile
                        ScreenState.ProjectIdeas -> ScreenState.Roadmap
                        ScreenState.BestPractices -> ScreenState.Roadmap
                        ScreenState.RoleBasedRoadmaps -> ScreenState.Roadmap
                        ScreenState.SkillBasedRoadmaps -> ScreenState.Roadmap
                        ScreenState.FrontendRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.BackendRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.FullStackRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.AIEngineerRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.DevOpsRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.UXDesignRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.CyberSecurityRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.GameDevRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.ServerSideGameDevRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.MLOpsRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.ProductManagementRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.DevSecOpsRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.DataAnalystRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.BIAnalystRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.DevRelRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.DataScientistRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.DataEngineerRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.MachineLearningRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.PostgresRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.IOSRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.AndroidRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.BlockchainRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.QAEngineerRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.SoftwareArchitectRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.TechnicalWriterRoadmap -> ScreenState.RoleBasedRoadmaps
                        ScreenState.AIAgentsRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.AIRedTeamingRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.APIDesignRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.AspNetCoreRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.AWSRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.AngularRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.AzureRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.CppRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.CssRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.CloudflareRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.CodeReviewRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.ComputerScienceRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.DsaRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.DesignSystemRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.DockerRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.FlutterRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.GcpRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.GitRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.GoRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.GraphqlRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.HtmlRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.JavaRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.KotlinRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.KubernetesRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.JavascriptRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.LinuxRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.MongodbRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.NextjsRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.NoSqlRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.NodejsRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.PhpRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.PromptEngineeringRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.PythonRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.ReactNativeRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.RustRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.ReactRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.RedisRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.SqlRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.SpringBootRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.SoftwareDesignRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.SystemDesignRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.TypeScriptRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.VueRoadmap -> ScreenState.SkillBasedRoadmaps
                        ScreenState.TerraformRoadmap -> ScreenState.SkillBasedRoadmaps
                        else -> ScreenState.Welcome
                    }
                }

                when (currentScreen) {
                    ScreenState.Splash -> {
                        SplashScreen(onLoadingComplete = {
                            if (preferenceManager.isLoggedIn()) {
                                currentScreen = ScreenState.Home
                            } else {
                                currentScreen = ScreenState.Onboarding
                            }
                        })
                    }
                    ScreenState.Onboarding -> {
                        OnboardingScreen(onGetStartedClick = {
                            currentScreen = ScreenState.Welcome
                        })
                    }
                    ScreenState.Welcome -> {
                        WelcomeScreen(
                            onSignInClick = { currentScreen = ScreenState.SignIn },
                            onCreateAccountClick = { currentScreen = ScreenState.SignUp },
                            onTermsClick = { currentScreen = ScreenState.Terms },
                            onPrivacyClick = { currentScreen = ScreenState.Privacy }
                        )
                    }
                    ScreenState.SignIn -> {
                        SignInScreen(
                            onBackClick = { currentScreen = ScreenState.Welcome },
                            onForgotPasswordClick = { currentScreen = ScreenState.ForgotPassword },
                            onSignInSuccessClick = { rememberMe, name, email ->
                                preferenceManager.saveLoginSession(rememberMe)
                                preferenceManager.saveProfileData(
                                    name,
                                    email,
                                    preferenceManager.getUserPhone(),
                                    preferenceManager.getUserRole(),
                                    preferenceManager.getUserImageUri()
                                )
                                currentScreen = ScreenState.Home
                            },
                            onGoogleSignInClick = {
                                launchGoogleSignIn { idToken, email, name ->
                                    if (idToken == null) {
                                        Toast.makeText(this@MainActivity, "Google sign-in failed. Please try again.", Toast.LENGTH_LONG).show()
                                        return@launchGoogleSignIn
                                    }
                                    CoroutineScope(Dispatchers.Main).launch {
                                        try {
                                            val response = withContext(Dispatchers.IO) {
                                                RetrofitClient.apiService.googleLogin(GoogleLoginRequest(idToken))
                                            }
                                            if (response.isSuccessful && response.body() != null) {
                                                val loginResponse = response.body()!!
                                                preferenceManager.saveLoginSession(true)
                                                preferenceManager.saveProfileData(
                                                    loginResponse.user.full_name,
                                                    loginResponse.user.email,
                                                    preferenceManager.getUserPhone(),
                                                    preferenceManager.getUserRole(),
                                                    preferenceManager.getUserImageUri()
                                                )
                                                currentScreen = ScreenState.Home
                                            } else {
                                                Toast.makeText(this@MainActivity, "Google sign-in failed. Please try again.", Toast.LENGTH_LONG).show()
                                            }
                                        } catch (e: Exception) {
                                            Toast.makeText(this@MainActivity, "Network error: ${e.message}", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            },
                            onSignUpClick = { currentScreen = ScreenState.SignUp }
                        )
                    }
                    ScreenState.SignUp -> {
                        SignUpScreen(
                            onBackClick = { currentScreen = ScreenState.Welcome },
                            onSignUpClick = { name, email ->
                                preferenceManager.saveLoginSession(true)
                                preferenceManager.saveProfileData(
                                    name,
                                    email,
                                    preferenceManager.getUserPhone(),
                                    preferenceManager.getUserRole(),
                                    preferenceManager.getUserImageUri()
                                )
                                currentScreen = ScreenState.Home
                            },
                            onGoogleSignUpClick = {
                                launchGoogleSignIn { idToken, email, name ->
                                    if (idToken == null) {
                                        Toast.makeText(this@MainActivity, "Google sign-in failed. Please try again.", Toast.LENGTH_LONG).show()
                                        return@launchGoogleSignIn
                                    }
                                    CoroutineScope(Dispatchers.Main).launch {
                                        try {
                                            val response = withContext(Dispatchers.IO) {
                                                RetrofitClient.apiService.googleLogin(GoogleLoginRequest(idToken))
                                            }
                                            if (response.isSuccessful && response.body() != null) {
                                                val loginResponse = response.body()!!
                                                preferenceManager.saveLoginSession(true)
                                                preferenceManager.saveProfileData(
                                                    loginResponse.user.full_name,
                                                    loginResponse.user.email,
                                                    preferenceManager.getUserPhone(),
                                                    preferenceManager.getUserRole(),
                                                    preferenceManager.getUserImageUri()
                                                )
                                                currentScreen = ScreenState.Home
                                            } else {
                                                Toast.makeText(this@MainActivity, "Google sign-up failed. Please try again.", Toast.LENGTH_LONG).show()
                                            }
                                        } catch (e: Exception) {
                                            Toast.makeText(this@MainActivity, "Network error: ${e.message}", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                            },
                            onLoginClick = { currentScreen = ScreenState.SignIn }
                        )
                    }
                    ScreenState.ForgotPassword -> {
                        ForgotPasswordScreen(
                            onBackClick = { currentScreen = ScreenState.SignIn },
                            onResetLinkSent = { currentScreen = ScreenState.SignIn }
                        )
                    }
                    ScreenState.Terms -> {
                        TermsOfServiceScreen(
                            onBackClick = { currentScreen = ScreenState.Welcome },
                            onDeclineClick = { currentScreen = ScreenState.Welcome },
                            onAcceptClick = { currentScreen = ScreenState.Welcome }
                        )
                    }
                    ScreenState.Privacy -> {
                        PrivacyPolicyScreen(
                            onBackClick = { currentScreen = ScreenState.Welcome },
                            onDeclineClick = { currentScreen = ScreenState.Welcome },
                            onAcceptClick = { currentScreen = ScreenState.Welcome },
                            onContactTeamClick = { currentScreen = ScreenState.ContactPrivacy }
                        )
                    }
                    ScreenState.ContactPrivacy -> {
                        ContactPrivacyScreen(
                            onBackClick = { currentScreen = ScreenState.Privacy },
                            onMessageSent = { currentScreen = ScreenState.Privacy }
                        )
                    }
                    ScreenState.Home -> {
                        HomeScreen(
                            onAnalysisClick = { currentScreen = ScreenState.Analysis },
                            onRoadmapClick = { currentScreen = ScreenState.Roadmap },
                            onProfileClick = { currentScreen = ScreenState.Profile },
                            onUploadResumeClick = { currentScreen = ScreenState.UploadResume }
                        )
                    }
                    ScreenState.Analysis -> AnalysisScreen(
                        onDashboardClick = { currentScreen = ScreenState.Home },
                        onRoadmapClick = { currentScreen = ScreenState.Roadmap },
                        onProfileClick = { currentScreen = ScreenState.Profile },
                        onViewResultsClick = { currentScreen = ScreenState.AnalysisResult }
                    )
                    ScreenState.AnalysisResult -> AnalysisResultsScreen(
                        onBackClick = { currentScreen = ScreenState.Analysis }
                    )
                    ScreenState.Roadmap -> RoadmapScreen(
                        onDashboardClick = { currentScreen = ScreenState.Home },
                        onAnalysisClick = { currentScreen = ScreenState.Analysis },
                        onProfileClick = { currentScreen = ScreenState.Profile },
                        onProjectIdeasClick = { currentScreen = ScreenState.ProjectIdeas },
                        onBestPracticesClick = { currentScreen = ScreenState.BestPractices },
                        onRoleBasedRoadmapsClick = { currentScreen = ScreenState.RoleBasedRoadmaps },
                        onSkillBasedRoadmapsClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                    )
                    ScreenState.UploadResume -> {
                        UploadResumeScreen(
                            onBackClick = { currentScreen = ScreenState.Home },
                            onProcessClick = { currentScreen = ScreenState.Analysis }
                        )
                    }
                    ScreenState.Profile -> {
                        ProfileScreen(
                            onHomeClick = { currentScreen = ScreenState.Home },
                            onAnalysisClick = { currentScreen = ScreenState.Analysis },
                            onRoadmapClick = { currentScreen = ScreenState.Roadmap },
                            onAccountInfoClick = { currentScreen = ScreenState.AccountInfo },
                            onAppSettingsClick = { currentScreen = ScreenState.AppSettings },
                            onResumeManagementClick = { currentScreen = ScreenState.ResumeManagement },
                            onHelpSupportClick = { currentScreen = ScreenState.HelpSupport },
                            onSignOutClick = {
                                preferenceManager.clearSession()
                                currentScreen = ScreenState.Welcome
                            }
                        )
                    }
                    ScreenState.AccountInfo -> {
                        AccountInfoScreen(
                            onBackClick = { currentScreen = ScreenState.Profile }
                        )
                    }
                    ScreenState.AppSettings -> {
                        AppSettingsScreen(
                            onBackClick = { currentScreen = ScreenState.Profile },
                            onChangePasswordClick = { currentScreen = ScreenState.ChangePassword },
                            onTermsClick = { currentScreen = ScreenState.Terms },
                            onPrivacyClick = { currentScreen = ScreenState.Privacy }
                        )
                    }
                    ScreenState.ChangePassword -> {
                        ChangePasswordScreen(
                            onBackClick = { currentScreen = ScreenState.AppSettings }
                        )
                    }
                    ScreenState.ResumeManagement -> {
                        ResumeManagementScreen(
                            onBackClick = { currentScreen = ScreenState.Profile }
                        )
                    }
                    ScreenState.HelpSupport -> {
                        HelpSupportScreen(
                            onBackClick = { currentScreen = ScreenState.Profile }
                        )
                    }
                    ScreenState.ProjectIdeas -> {
                        ProjectIdeasScreen(
                            onBackClick = { currentScreen = ScreenState.Roadmap }
                        )
                    }
                    ScreenState.BestPractices -> {
                        BestPracticesScreen(
                            onBackClick = { currentScreen = ScreenState.Roadmap }
                        )
                    }
                    ScreenState.RoleBasedRoadmaps -> {
                        CareerPathScreen(
                            onBackClick = { currentScreen = ScreenState.Roadmap },
                            onFrontendClick = { currentScreen = ScreenState.FrontendRoadmap },
                            onBackendClick = { currentScreen = ScreenState.BackendRoadmap },
                            onFullStackClick = { currentScreen = ScreenState.FullStackRoadmap },
                            onAIEngineerClick = { currentScreen = ScreenState.AIEngineerRoadmap },
                            onDevOpsClick = { currentScreen = ScreenState.DevOpsRoadmap },
                            onUXDesignClick = { currentScreen = ScreenState.UXDesignRoadmap },
                            onCyberSecurityClick = { currentScreen = ScreenState.CyberSecurityRoadmap },
                            onGameDeveloperClick = { currentScreen = ScreenState.GameDevRoadmap },
                            onServerSideGameDevClick = { currentScreen = ScreenState.ServerSideGameDevRoadmap },
                            onMLOpsClick = { currentScreen = ScreenState.MLOpsRoadmap },
                            onProductManagerClick = { currentScreen = ScreenState.ProductManagementRoadmap },
                            onDevSecOpsClick = { currentScreen = ScreenState.DevSecOpsRoadmap },
                            onDataAnalystClick = { currentScreen = ScreenState.DataAnalystRoadmap },
                            onBIAnalystClick = { currentScreen = ScreenState.BIAnalystRoadmap },
                            onDevRelClick = { currentScreen = ScreenState.DevRelRoadmap },
                            onDataScientistClick = { currentScreen = ScreenState.DataScientistRoadmap },
                            onDataEngineerClick = { currentScreen = ScreenState.DataEngineerRoadmap },
                            onMachineLearningClick = { currentScreen = ScreenState.MachineLearningRoadmap },
                            onAndroidClick = { currentScreen = ScreenState.AndroidRoadmap },
                            onPostgresClick = { currentScreen = ScreenState.PostgresRoadmap },
                            onIOSClick = { currentScreen = ScreenState.IOSRoadmap },
                            onBlockchainClick = { currentScreen = ScreenState.BlockchainRoadmap },
                            onQAClick = { currentScreen = ScreenState.QAEngineerRoadmap },
                            onSoftwareArchitectClick = { currentScreen = ScreenState.SoftwareArchitectRoadmap },
                            onTechnicalWriterClick = { currentScreen = ScreenState.TechnicalWriterRoadmap }
                        )
                    }
                    ScreenState.SkillBasedRoadmaps -> {
                        AllSkillsScreen(
                            onBackClick = { currentScreen = ScreenState.Roadmap },
                            onSkillClick = { skillName ->
                                when (skillName) {
                                    "AI Agents" -> currentScreen = ScreenState.AIAgentsRoadmap
                                    "AI Red Teaming" -> currentScreen = ScreenState.AIRedTeamingRoadmap
                                    "API Design" -> currentScreen = ScreenState.APIDesignRoadmap
                                    "ASP.NET Core" -> currentScreen = ScreenState.AspNetCoreRoadmap
                                    "AWS" -> currentScreen = ScreenState.AWSRoadmap
                                    "Angular" -> currentScreen = ScreenState.AngularRoadmap
                                    "Azure" -> currentScreen = ScreenState.AzureRoadmap
                                    "C++" -> currentScreen = ScreenState.CppRoadmap
                                    "CSS" -> currentScreen = ScreenState.CssRoadmap
                                    "Cloudflare" -> currentScreen = ScreenState.CloudflareRoadmap
                                    "Code Review" -> currentScreen = ScreenState.CodeReviewRoadmap
                                    "Computer Science" -> currentScreen = ScreenState.ComputerScienceRoadmap
                                    "Data Structures and Algorithms" -> currentScreen = ScreenState.DsaRoadmap
                                    "Design System" -> currentScreen = ScreenState.DesignSystemRoadmap
                                    "Docker" -> currentScreen = ScreenState.DockerRoadmap
                                    "Flutter" -> currentScreen = ScreenState.FlutterRoadmap
                                    "GCP" -> currentScreen = ScreenState.GcpRoadmap
                                    "Git" -> currentScreen = ScreenState.GitRoadmap
                                    "Go" -> currentScreen = ScreenState.GoRoadmap
                                    "GraphQL" -> currentScreen = ScreenState.GraphqlRoadmap
                                    "HTML" -> currentScreen = ScreenState.HtmlRoadmap
                                    "Java" -> currentScreen = ScreenState.JavaRoadmap
                                    "Kotlin" -> currentScreen = ScreenState.KotlinRoadmap
                                    "Kubernetes" -> currentScreen = ScreenState.KubernetesRoadmap
                                    "JavaScript" -> currentScreen = ScreenState.JavascriptRoadmap
                                    "Linux" -> currentScreen = ScreenState.LinuxRoadmap
                                    "MongoDB" -> currentScreen = ScreenState.MongodbRoadmap
                                    "Next.js" -> currentScreen = ScreenState.NextjsRoadmap
                                    "NoSQL" -> currentScreen = ScreenState.NoSqlRoadmap
                                    "Node.js" -> currentScreen = ScreenState.NodejsRoadmap
                                    "PHP" -> currentScreen = ScreenState.PhpRoadmap
                                    "Prompt Engineering" -> currentScreen = ScreenState.PromptEngineeringRoadmap
                                    "Python" -> currentScreen = ScreenState.PythonRoadmap
                                    "React Native" -> currentScreen = ScreenState.ReactNativeRoadmap
                                    "Rust" -> currentScreen = ScreenState.RustRoadmap
                                    "React" -> currentScreen = ScreenState.ReactRoadmap
                                    "Redis" -> currentScreen = ScreenState.RedisRoadmap
                                    "SQL" -> currentScreen = ScreenState.SqlRoadmap
                                    "Spring Boot" -> currentScreen = ScreenState.SpringBootRoadmap
                                    "Software Design and Architecture" -> currentScreen = ScreenState.SoftwareDesignRoadmap
                                    "System Design" -> currentScreen = ScreenState.SystemDesignRoadmap
                                    "TypeScript" -> currentScreen = ScreenState.TypeScriptRoadmap
                                    "Vue.js" -> currentScreen = ScreenState.VueRoadmap
                                    "Terraform" -> currentScreen = ScreenState.TerraformRoadmap
                                }
                            }
                        )
                    }
                    ScreenState.FrontendRoadmap -> {
                        FrontendScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.BackendRoadmap -> {
                        BackendScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.FullStackRoadmap -> {
                        FullStackScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.AIEngineerRoadmap -> {
                        AIEngineerScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.DevOpsRoadmap -> {
                        DevOpsScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.UXDesignRoadmap -> {
                        UXDesignScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.CyberSecurityRoadmap -> {
                        CyberSecurityScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.GameDevRoadmap -> {
                        GameDevScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.ServerSideGameDevRoadmap -> {
                        ServerSideGameDevScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.MLOpsRoadmap -> {
                        MLOpsScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.ProductManagementRoadmap -> {
                        ProductManagementScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.DevSecOpsRoadmap -> {
                        DevSecOpsScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.DataAnalystRoadmap -> {
                        DataAnalystScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.BIAnalystRoadmap -> {
                        BIAnalystScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.DevRelRoadmap -> {
                        DevRelScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.DataScientistRoadmap -> {
                        DataScientistScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.DataEngineerRoadmap -> {
                        DataEngineerScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.MachineLearningRoadmap -> {
                        MachineLearningScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.PostgresRoadmap -> {
                        PostgresScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.IOSRoadmap -> {
                        IOSDeveloperScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.AndroidRoadmap -> {
                        AndroidDeveloperScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.BlockchainRoadmap -> {
                        BlockchainRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.AIAgentsRoadmap -> {
                        AIAgentsRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.AIRedTeamingRoadmap -> {
                        AIRedTeamingScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.APIDesignRoadmap -> {
                        APIDesignScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.AspNetCoreRoadmap -> {
                        AspNetCoreRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.AWSRoadmap -> {
                        AWSRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.AngularRoadmap -> {
                        AngularRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.AzureRoadmap -> {
                        AzureRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.CppRoadmap -> {
                        CppRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.CssRoadmap -> {
                        CssRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.CloudflareRoadmap -> {
                        CloudflareRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.CodeReviewRoadmap -> {
                        CodeReviewRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.ComputerScienceRoadmap -> {
                        ComputerScienceRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.DsaRoadmap -> {
                        DsaRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.DesignSystemRoadmap -> {
                        DesignSystemRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.DockerRoadmap -> {
                        DockerRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.FlutterRoadmap -> {
                        FlutterRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.GcpRoadmap -> {
                        GcpRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.GitRoadmap -> {
                        GitRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.GoRoadmap -> {
                        GoRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.GraphqlRoadmap -> {
                        GraphqlRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.HtmlRoadmap -> {
                        HtmlRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.JavaRoadmap -> {
                        JavaRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.KotlinRoadmap -> {
                        KotlinRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.KubernetesRoadmap -> {
                        KubernetesRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.JavascriptRoadmap -> {
                        JavascriptRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.LinuxRoadmap -> {
                        LinuxRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.MongodbRoadmap -> {
                        MongodbRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.NextjsRoadmap -> {
                        NextjsRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.NoSqlRoadmap -> {
                        NoSqlRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.NodejsRoadmap -> {
                        NodejsRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.PhpRoadmap -> {
                        PhpRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.PromptEngineeringRoadmap -> {
                        PromptEngineeringRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.PythonRoadmap -> {
                        PythonRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.ReactNativeRoadmap -> {
                        ReactNativeRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.RustRoadmap -> {
                        RustRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.ReactRoadmap -> {
                        ReactRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.RedisRoadmap -> {
                        RedisRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.SqlRoadmap -> {
                        SqlRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.SpringBootRoadmap -> {
                        SpringBootRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.SoftwareDesignRoadmap -> {
                        SoftwareDesignRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.SystemDesignRoadmap -> {
                        SystemDesignRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.TypeScriptRoadmap -> {
                        TypeScriptRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.VueRoadmap -> {
                        VueRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.TerraformRoadmap -> {
                        TerraformRoadmapScreen(
                            onBackClick = { currentScreen = ScreenState.SkillBasedRoadmaps }
                        )
                    }
                    ScreenState.QAEngineerRoadmap -> {
                        QAEngineerScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.SoftwareArchitectRoadmap -> {
                        SoftwareArchitectScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                    ScreenState.TechnicalWriterRoadmap -> {
                        TechnicalWriterScreen(
                            onBackClick = { currentScreen = ScreenState.RoleBasedRoadmaps }
                        )
                    }
                }
            }
        }
    }
}
