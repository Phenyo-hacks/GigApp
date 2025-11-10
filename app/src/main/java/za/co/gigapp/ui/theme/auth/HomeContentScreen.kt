package za.co.gigapp.ui.theme.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeContentScreen(
    navController: NavController, // ✅ ADD THIS PARAMETER
    onLogout: () -> Unit = {},
    viewModel: AuthViewModel = hiltViewModel()
) {
    // Collect the current user state
    val currentUser by viewModel.currentUser.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    // Debug logging
    LaunchedEffect(currentUser) {
        println("🔄 DEBUG: HomeContentScreen - currentUser = $currentUser")
        println("🔄 DEBUG: HomeContentScreen - isLoggedIn = $isLoggedIn")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Personalized welcome message
        if (currentUser != null) {
            Text(
                "Welcome, ${currentUser?.username}! 👋",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Email: ${currentUser?.email}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            Text("Welcome to GigApp!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Please log in to see your profile",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ✅ ADD THESE BUTTONS:
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Post Gig Button
            Button(
                onClick = {
                    // ✅ NOW THIS WORKS - navigate to post_gig screen
                    navController.navigate("post_gig")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("📝 Post Gig")
            }

            // update the Browse Gigs button:
            Button(
                onClick = {
                    navController.navigate("gigs_feed") // ✅ NOW THIS WORKS
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("🔍 Browse Gigs")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick stats card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Your Gig Stats", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("📝 Completed: 0")
                Text("📨 Applied: 0")
                Text("💰 Earned: R0")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Logout button
        Button(
            onClick = {
                viewModel.logout()
                onLogout()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Text("Log Out")
        }

        // Debug info (remove in production)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Debug: User = ${currentUser?.username ?: "null"}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )
    }
}