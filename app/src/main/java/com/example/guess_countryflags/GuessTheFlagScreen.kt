import android.content.res.AssetManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guess_countryflags.getFlagResourceId
import kotlinx.coroutines.delay

@Composable
fun GuessTheFlagScreen(navController: NavController, assetManager: AssetManager, gameViewModel: GameViewModel, timerEnabled: Boolean) {
    val countries = loadCountries(assetManager)
    if (gameViewModel.flagOptions.isEmpty()) {
        gameViewModel.loadNewFlags(countries)
    }

    var selectedFlag by rememberSaveable { mutableStateOf("") }
    var timer by remember { mutableStateOf(10) }

    LaunchedEffect(timerEnabled, timer) {
        if (timerEnabled) {
            if (timer > 0) {
                delay(1000L)
                timer -= 1
            } else {
                // Simulate submit action when timer reaches 0
                selectedFlag = gameViewModel.flagOptions.firstOrNull() ?: ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        if (timerEnabled) {
            Text("Timer: $timer", fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        Text("Select the flag for the country", fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            gameViewModel.flagOptions.forEach { flag ->
                Image(
                    painter = painterResource(id = getFlagResourceId(gameViewModel.flagOptions.first().toString())),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                        selectedFlag = flag
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (selectedFlag.isEmpty()) {
                // Do nothing, no flag selected
                return@Button
            }

            val correctFlag = gameViewModel.flagOptions.firstOrNull { countries[it] == gameViewModel.correctFlags.firstOrNull() } ?: ""
            gameViewModel.guessResult.value = (selectedFlag == correctFlag)

            gameViewModel.loadNewFlags(countries)
            selectedFlag = ""
            if (timerEnabled) {
                timer = 10
            }
        }) {
            Text(text = "Next", fontSize = 20.sp)
        }

        if (gameViewModel.guessResult.value != null) {
            if (gameViewModel.guessResult.value == true) {
                Text("CORRECT!", color = Color.Green, fontSize = 24.sp)
            } else {
                val correctFlag = gameViewModel.flagOptions.firstOrNull { countries[it] == gameViewModel.correctFlags.firstOrNull() } ?: ""
                Text("WRONG! The correct flag is $correctFlag", color = Color.Red, fontSize = 24.sp)
            }
        }
    }
}
