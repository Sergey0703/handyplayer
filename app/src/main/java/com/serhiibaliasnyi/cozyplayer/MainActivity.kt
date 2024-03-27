package com.serhiibaliasnyi.cozyplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.media3.exoplayer.ExoPlayer
import com.serhiibaliasnyi.cozyplayer.screens.MainScreen
import com.serhiibaliasnyi.cozyplayer.ui.theme.CozyPlayerTheme
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.serhiibaliasnyi.cozyplayer.ui.theme.HandyPlayerTheme

class MainActivity : ComponentActivity() {
   //private lateinit var mediaPlayer: MediaPlayer
   //var mediaPlayer = MediaPlayer()
   public lateinit var player: ExoPlayer
   override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        player = ExoPlayer.Builder(this).build()
        val playList = getPlayList()
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
                    MainScreen(player,playList)
              //  }

            }
        }
    }


    private fun getPlayList(): List<Music> {
        return listOf(
            Music(
                name = "Incy Wincy Spider",
                cover = "assets.image1",
                music = R.raw.track1
            ),
            Music(
                name = "Head Shoulders Knees and Toes",
                cover = "assets.image2",
                music = R.raw.track2
            ),
            Music(
                name = "Twinkle Twinkle Little Star",
                cover = "assets.image3",
                music = R.raw.track3
            ),
            Music(
                name = "Jelly on a Plate",
                cover = "assets.image4",
                music = R.raw.track4
            ),
            Music(
                name = "Sleeping Bunnies",
                cover = "assets.image5",
                music = R.raw.track5
            ),
            Music(
                name = "Wind the Bobbin Up",
                cover = "assets.image6",
                music = R.raw.track6
            ),
            Music(
                name = "If You\'re Happy and You Know It",
                cover = "assets.image7",
                music = R.raw.track7
            ),
            Music(
                name = "The Wheels on the Bus",
                cover = "assets.image8",
                music = R.raw.track8
            ),
            Music(
                name = "Pat a Cake Pat a Cake",
                cover = "assets.image9",
                music = R.raw.track9
            ),
            Music(
                name = "Five Little Monkeys",
                cover = "assets.image10",
                music = R.raw.track10
            ),
        )
    }

    /***
     * Data class to represent a music in the list
     */
    data class Music(
        val name: String,
        val music: Int,
        val cover: String,
    )

}

