import android.content.res.AssetManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun AdvancedLevelScreen(navController: NavController, assetManager: AssetManager, gameViewModel: GameViewModel, timerEnabled: Boolean) {
    val countries = loadCountries(assetManager)
    if (gameViewModel.flagOptions.isEmpty()) {
        gameViewModel.loadNewFlags(countries)
    }

    var attempts by rememberSaveable { mutableStateOf(0) }
    var timer by remember { mutableStateOf(10) }

    LaunchedEffect(timerEnabled, timer) {
        if (timerEnabled) {
            if (timer > 0) {
                delay(1000L)
                timer -= 1
            } else {
                // Simulate submit action when timer reaches 0
                attempts++
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

        Text("Guess the countries for the flags", fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))

        gameViewModel.flagOptions.forEachIndexed { index, flag ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = LocalContext.current.resources.getIdentifier(flag, "drawable", LocalContext.current.packageName)),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                BasicTextField(
                    value = gameViewModel.guesses[index],
                    onValueChange = { gameViewModel.guesses[index] = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .background(if (attempts >= 3) if (gameViewModel.guesses[index] == gameViewModel.correctFlags[index]) Color.Green else Color.Red else Color.White)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (attempts >= 3) {
                // Reset game
                gameViewModel.loadNewFlags(countries)
                attempts = 0
                if (timerEnabled) {
                    timer = 10
                }
            } else {
                attempts++
            }
        }) {
            Text(text = if (attempts >= 3) "Next" else "Submit", fontSize = 20.sp)
        }

        if (attempts >= 3) {
            val correctFlags = gameViewModel.correctFlags.joinToString(", ")
            Text("The correct flags are $correctFlags", color = Color.Blue, fontSize = 24.sp)
        }
    }
}
