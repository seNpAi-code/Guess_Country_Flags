import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SetupNavGraph(navController: NavHostController, timerEnabled: MutableState<Boolean>, gameViewModel: GameViewModel = viewModel()) {
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable("main_screen") {
            MainScreen(navController = navController, timerEnabled = timerEnabled)
        }
        composable("guess_the_country_screen") {
            GuessTheCountryScreen(navController = navController, assetManager = LocalContext.current.assets, gameViewModel = gameViewModel, timerEnabled = timerEnabled.value)
        }
        composable("guess_hints_screen") {
            GuessHintsScreen(navController = navController, assetManager = LocalContext.current.assets, gameViewModel = gameViewModel, timerEnabled = timerEnabled.value)
        }
        composable("guess_the_flag_screen") {
            GuessTheFlagScreen(navController = navController, assetManager = LocalContext.current.assets, gameViewModel = gameViewModel, timerEnabled = timerEnabled.value)
        }
        composable("advanced_level_screen") {
            AdvancedLevelScreen(navController = navController, assetManager = LocalContext.current.assets, gameViewModel = gameViewModel, timerEnabled = timerEnabled.value)
        }
    }
}
