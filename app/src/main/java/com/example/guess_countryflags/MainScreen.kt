import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController, timerEnabled: MutableState<Boolean>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Enable Timer")
            Switch(
                checked = timerEnabled.value,
                onCheckedChange = { timerEnabled.value = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("guess_the_country_screen") }) {
            Text("Guess the Country", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("guess_hints_screen") }) {
            Text("Guess-Hints", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("guess_the_flag_screen") }) {
            Text("Guess the Flag", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("advanced_level_screen") }) {
            Text("Advanced Level", fontSize = 20.sp)
        }
    }
}
