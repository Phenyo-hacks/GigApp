package za.co.gigapp.ui.theme.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import za.co.gigapp.ui.theme.auth.HomeContentScreen
import za.co.gigapp.ui.theme.profile.ProfileScreen
import za.co.gigapp.ui.theme.settings.SettingsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
    parentNavController: NavController, // ✅ ADD THIS
    onLogout: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = "home_tab",
        modifier = Modifier.padding(padding)
    ) {
        composable("home_tab") {
            HomeContentScreen(
                navController = parentNavController, // ✅ PASS TO HOME CONTENT
                onLogout = onLogout
            )
        }
        composable("profile_tab") { ProfileScreen() }
        composable("settings_tab") { SettingsScreen() }
    }
}
