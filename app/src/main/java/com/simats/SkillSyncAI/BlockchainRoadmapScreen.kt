package com.simats.SkillSyncAI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val BlockchainBgStart = Color(0xFFF1F4FF)
val BlockchainBgEnd = Color(0xFFFFFFFF)
val BlockchainPrimary = Color(0xFF1A237E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlockchainRoadmapScreen(onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, null, tint = BlockchainPrimary, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SkillSync AI", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = BlockchainPrimary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = BlockchainPrimary)
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
                .background(Brush.verticalGradient(listOf(BlockchainBgStart, BlockchainBgEnd)))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            Text(
                text = "Blockchain\nDeveloper\nRoadmap",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = BlockchainPrimary,
                    lineHeight = 38.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A strategic research roadmap through decentralized systems, smart contracts, and scalable infrastructure.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    lineHeight = 22.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Phase 01
            BlockchainPhaseCard(
                phase = "01",
                title = "Introduction & Theoretical Foundations",
                icon = Icons.Default.Lock,
                items = listOf(
                    "The 'Why' and 'What'",
                    "What is Blockchain?",
                    "Why decentralized?",
                    "Applications of Blockchain",
                    "Understanding Bit-com/Node Mechanics",
                    "Block and its Structure",
                    "Cryptography & Hashing",
                    "Decentralization vs. Trust",
                    "Consensus Protocols: PoW & PoS",
                    "Web3.0 v1"
                ),
                preferenceManager = preferenceManager,
                prefix = "bc_p1"
            )

            // Phase 02
            BlockchainPhaseCard(
                phase = "02",
                title = "Types of Blockchains & Ecosystems",
                icon = Icons.Default.Hub,
                items = listOf(
                    "EVM-based: Ethereum, Polygon, BSC Chain, Avalanche, Fantom",
                    "UTXO-based: Bitcoin, Cardano",
                    "TVM-Based: Solana, Polkadot, Near, Aptos",
                    "Subnets",
                    "L2: Arbitrum, Optimism, ZKSync, Starknet"
                ),
                preferenceManager = preferenceManager,
                prefix = "bc_p2"
            )

            // Phase 03
            BlockchainPhaseCard(
                phase = "03",
                title = "Smart Contracts Development",
                icon = Icons.Default.Terminal,
                items = listOf(
                    "Programming Languages: Solidity, Rust, Vyper",
                    "Development Tools & IDEs: Hardhat, VS Code",
                    "Automated Security: Mythril, Slither, Echidna",
                    "Management: Foundry, OpenZeppelin",
                    "Version Control: Git basics",
                    "Unit Testing, Linting & Coverage",
                    "Blockchain Monitoring, and Analytics"
                ),
                preferenceManager = preferenceManager,
                prefix = "bc_p3"
            )

            // Phase 04
            BlockchainPhaseCard(
                phase = "04",
                title = "Security & Oracles",
                icon = Icons.Default.Security,
                items = listOf(
                    "Smart Contract Audits",
                    "Fuzz Testing & Static Analysis",
                    "Governance and DAOs",
                    "Sources of Randomness (VRFs)",
                    "Oracles: What are Oracles?",
                    "Chainlink and Oracle Networks",
                    "Hybrid Smart Contracts"
                ),
                preferenceManager = preferenceManager,
                prefix = "bc_p4"
            )

            // Phase 05
            BlockchainPhaseCard(
                phase = "05",
                title = "Decentralized Applications (dApps)",
                icon = Icons.Default.Layers,
                items = listOf(
                    "Frontend Development",
                    "Library: Ethers.js, Web3.js",
                    "Frameworks: Truffle, Hardhat, Viem",
                    "Client-side libraries: Wagmi, WalletConnect",
                    "Infrastructure: Infura, Alchemy, Blast Nodes",
                    "Wallets: Metamask, Ledger",
                    "Decentralized Storage: IPFS",
                    "Social dApps & Use Cases: Lens, Link3",
                    "DeFi and DEX mechanics"
                ),
                preferenceManager = preferenceManager,
                prefix = "bc_p5"
            )

            // Phase 06
            BlockchainPhaseCard(
                phase = "06",
                title = "Building for Scale",
                icon = Icons.Default.Speed,
                items = listOf(
                    "Layer 2 Scaling Solutions",
                    "Rollups: Optimistic & ZK Rollups",
                    "Sidechains & Subnets",
                    "On-Chain Scaling: Sharding, ZK"
                ),
                preferenceManager = preferenceManager,
                prefix = "bc_p6"
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun BlockchainPhaseCard(
    phase: String,
    title: String,
    icon: ImageVector,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, null, tint = BlockchainPrimary, modifier = Modifier.size(20.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "PHASE $phase",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = BlockchainPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            items.forEach { item ->
                BlockchainCheckboxItem(item, title, prefix, preferenceManager)
            }
        }
    }
}

@Composable
fun BlockchainCheckboxItem(item: String, title: String, prefix: String, preferenceManager: PreferenceManager) {
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
            colors = CheckboxDefaults.colors(checkedColor = BlockchainPrimary)
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
