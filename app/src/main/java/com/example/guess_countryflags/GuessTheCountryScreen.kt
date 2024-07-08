import android.content.res.AssetManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

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
import com.example.guess_countryflags.R
import com.example.guess_countryflags.getCountriesFromAssets
import com.example.guess_countryflags.getFlagResourceId
import kotlinx.coroutines.delay
import org.json.JSONObject

@Composable
fun GuessTheCountryScreen(navController: NavController, assetManager: AssetManager, gameViewModel: GameViewModel, timerEnabled: Boolean) {
    val context = LocalContext.current
    val countries = getCountriesFromAssets(context)
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val filteredCountries = remember(searchQuery) {
        countries.filter { (_, name) -> name.contains(searchQuery.text, ignoreCase = true) }
    }
    //val countries = loadCountries(assetManager)
    var currentFlag by remember { mutableStateOf("") }
    val searchText = rememberSaveable { mutableStateOf("") }
    //val countries1 = remember { mutableStateOf(getCountries()) }
    //var filteredCountries: ArrayList<String>
    if (gameViewModel.flagOptions.isEmpty()) {
        gameViewModel.loadNewFlags(countries)
    }

    var selectedCountry by rememberSaveable { mutableStateOf<String?>(null) }
    var timer by remember { mutableStateOf(10) }

    // Timer logic
    LaunchedEffect(timerEnabled, timer) {
        if (timerEnabled) {
            if (timer > 0) {
                delay(1000L)
                timer -= 1
            } else {
                // Simulate submit action when timer reaches 0
                if (gameViewModel.guessResult.value == null) {
                    gameViewModel.guessResult.value = false
                    selectedCountry = ""
                }
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

        if (currentFlag.isEmpty()) {
            currentFlag = countries.keys.random()
        }

        Text("Select the country for the flag", fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))


        Image(
            painter = painterResource(id = getFlagResourceId(gameViewModel.flagOptions.first().toString())),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Column(modifier = Modifier.padding(1.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Search Country") },

                    )
                Button(modifier = Modifier.fillMaxWidth(),onClick = {
                    if (gameViewModel.guessResult.value == null) {
                        // Submit logic
                        val correctCountry = gameViewModel.correctFlags.firstOrNull() ?: ""
                        gameViewModel.guessResult.value = (selectedCountry == correctCountry)
                        selectedCountry = ""
                    } else {
                        // Next logic
                        gameViewModel.loadNewFlags(countries)
                        gameViewModel.guessResult.value = null
                        //countryFlag = countries.keys.random()
                        if (timerEnabled) {
                            timer = 10
                        }
                    }
                }) {
                    Text(text = if (gameViewModel.guessResult.value == null) "Submit" else "Next", fontSize = 15.sp)
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                LazyColumn(modifier = Modifier.height(200.dp)) {  // Set a fixed height for the dropdown list
                    items(filteredCountries.toList()) { (_, countryName) ->
                        Text(
                            text = countryName,
                            modifier = Modifier

                                .fillMaxWidth()
                                .clickable {
                                    selectedCountry = countryName
                                    searchQuery = TextFieldValue(countryName)
                                }
                        )
                    }
                }
            }
        }



        if (gameViewModel.guessResult.value != null) {
            val correctCountry = gameViewModel.correctFlags.firstOrNull() ?: ""
            if (gameViewModel.guessResult.value == true) {
                Text("CORRECT!", color = Color.Green, fontSize = 24.sp)
            } else {
                Text("WRONG! The correct country is $correctCountry", color = Color.Red, fontSize = 24.sp)
            }
        }
    }
}

fun loadCountries(assetManager: AssetManager): Map<String, String> {
    val inputStream = assetManager.open("countries.json")
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    val jsonObject = JSONObject(jsonString)
    val countries = mutableMapOf<String, String>()
    jsonObject.keys().forEach { key ->
        countries[key] = jsonObject.getString(key)
    }
    return countries
}

