package com.serhiibaliasnyi.cozyplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.serhiibaliasnyi.cozyplayer.screens.MainScreen
import com.serhiibaliasnyi.cozyplayer.ui.theme.CozyPlayerTheme
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.serhiibaliasnyi.cozyplayer.ui.theme.HandyPlayerTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
         //   val activity = (LocalContext.current as Activity)
         //   activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            CozyPlayerTheme {
                // A surface container using the 'background' color from the theme
              //  Surface(
              //      modifier = Modifier.fillMaxSize(),
              //      color = MaterialTheme.colorScheme.background
              //  ) {
                 //   Greeting("Android")
                    MainScreen()
              //  }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
ConstraintLayout {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CozyPlayerTheme {
        Greeting("Android")
    }
}