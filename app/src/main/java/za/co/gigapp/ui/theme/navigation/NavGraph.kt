package za.co.gigapp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import za.co.gigapp.ui.theme.auth.AuthViewModel
import za.co.gigapp.ui.theme.auth.HomeScreen
import za.co.gigapp.ui.theme.auth.LoginScreen
import za.co.gigapp.ui.theme.auth.RegisterScreen
import za.co.gigapp.ui.theme.gigs.GigFeedScreen
import za.co.gigapp.ui.theme.gigs.GigDetailsScreen
import za.co.gigapp.ui.theme.gigs.PostGigScreen


@Composable
fun NavGraph(navController: NavHostController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val currentUser = authViewModel.currentUser.collectAsState().value


    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController, viewModel = authViewModel)
        }
        composable("register") {
            RegisterScreen(navController = navController, viewModel = authViewModel)
        }

        composable("home") {
            HomeScreen(parentNavController = navController)
        }

        composable("post_gig") {
            PostGigScreen(
                navController = navController,
                currentUserEmail = currentUser?.email ?: "unknown@user.com"
            )
        }
        composable("gigs_feed") {
            GigFeedScreen(navController = navController)
        }
        // ✅ ADD GIG DETAILS SCREEN WITH NAVIGATION ARGUMENT
        composable(
            "gig_details/{gigId}",
            arguments = listOf(navArgument("gigId") { type = NavType.IntType })
        ) { backStackEntry ->
            val gigId = backStackEntry.arguments?.getInt("gigId") ?: 0
            GigDetailsScreen(
                navController = navController,
                gigId = gigId
            )
        }
    }
}