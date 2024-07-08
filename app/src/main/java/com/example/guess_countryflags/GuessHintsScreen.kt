import android.content.res.AssetManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guess_countryflags.getFlagResourceId
import kotlinx.coroutines.delay

@Composable
fun GuessHintsScreen(navController: NavController, assetManager: AssetManager, gameViewModel: GameViewModel, timerEnabled: Boolean) {
    val countries = loadCountries(assetManager)
    if (gameViewModel.flagOptions.isEmpty()) {
        gameViewModel.loadNewFlags(countries)
    }

    var guessedCharacters by rememberSaveable { mutableStateOf("") }
    var guessedChar by rememberSaveable { mutableStateOf("") }
    var currentCountryName by rememberSaveable { mutableStateOf("") }
    var incorrectGuesses by rememberSaveable { mutableStateOf(0) }
    var timer by remember { mutableStateOf(10) }

    LaunchedEffect(timerEnabled, timer) {
        if (timerEnabled) {
            if (timer > 0) {
                delay(1000L)
                timer -= 1
            } else {
                // Simulate submit action when timer reaches 0
                incorrectGuesses = 3
                currentCountryName = gameViewModel.correctFlags.firstOrNull() ?: ""
            }
        }
    }

    val dashes = remember(gameViewModel.correctFlags.firstOrNull(), guessedCharacters) {
        gameViewModel.correctFlags.first().map { if (guessedCharacters.contains(it, ignoreCase = true)) it else "-" }.joinToString("")
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

        Text("Guess the country name", fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = getFlagResourceId(gameViewModel.flagOptions.first().toString())),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Text(dashes, fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = guessedChar,
            onValueChange = { guessedChar = it.takeLast(1) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (incorrectGuesses < 3 && dashes.contains('-')) {
                if (gameViewModel.correctFlags.first().contains(guessedChar, ignoreCase = true)) {
                    // Correct guess
                    guessedCharacters += guessedChar
                } else {
                    incorrectGuesses++
                    if (incorrectGuesses >= 3) {
                        gameViewModel.guessResult.value = false
                        currentCountryName = gameViewModel.correctFlags.firstOrNull() ?: ""
                    }
                }

//                if (incorrectGuesses >= 3) {
//                    currentCountryName = gameViewModel.correctFlags.firstOrNull() ?: ""
//                }

                if (!dashes.contains('-')) {
                    // Correct answer
                    gameViewModel.guessResult.value = true
                }

            } else {
                gameViewModel.loadNewFlags(countries)
                currentCountryName = ""
                guessedCharacters = ""
                incorrectGuesses = 0
                gameViewModel.guessResult.value = null
                if (timerEnabled) {
                    timer = 10
                }
            }
        }) {
            Text(text = if (incorrectGuesses < 3 && dashes.contains('-')) "Submit" else "Next", fontSize = 20.sp)
        }

        if (gameViewModel.guessResult.value != null) {
            if (gameViewModel.guessResult.value == true || (incorrectGuesses < 3 && !dashes.contains('-'))) {
                Text("CORRECT!", color = Color.Green, fontSize = 24.sp)
            } else {
                Text("WRONG! The correct country is $currentCountryName", color = Color.Red, fontSize = 24.sp)
            }
        }
    }
}
