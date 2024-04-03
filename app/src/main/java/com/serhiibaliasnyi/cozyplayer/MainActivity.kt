package com.serhiibaliasnyi.cozyplayer

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.shapes.Shape
import android.media.MediaPlayer
import android.media.browse.MediaBrowser
import android.net.Uri
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.serhiibaliasnyi.cozyplayer.screens.MainScreen
import com.serhiibaliasnyi.cozyplayer.ui.theme.CozyPlayerTheme
import kotlinx.coroutines.delay
import kotlin.io.path.Path


//import androidx.constraintlayout.widget.ConstraintLayout
//import com.serhiibaliasnyi.cozyplayer.ui.theme.HandyPlayerTheme

class MainActivity : ComponentActivity() {
   //private lateinit var mediaPlayer: MediaPlayer
   //var mediaPlayer = MediaPlayer()
   private lateinit var player: ExoPlayer

   override fun onCreate(savedInstanceState: Bundle?) {

       super.onCreate(savedInstanceState)
       player = ExoPlayer.Builder(this).build()
       val playList = getPlayList()

        setContent {
                CozyPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                             .padding(0.dp)
                             .fillMaxSize(),
                              color = Color(125,150,141)
                ) {
                   MainScreen(playList)
                  //  SongScreen(playList)
                }

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
            Music(
                name = "I'm a Little Teapot",
                cover = "assets.image11",
                music = R.raw.track11
            ),
            Music(
                name = "Hey Diddle Diddle",
                cover = "assets.image12",
                music = R.raw.track12
            ),
            Music(
                name = "One Two Buckle My Shoe",
                cover = "assets.image13",
                music = R.raw.track13
            ),
            Music(
                name = "Old MacDonald Had a Farm",
                cover = "assets.image14",
                music = R.raw.track14
            ),
            Music(
                name = "This Is the Way the Ladies Ride",
                cover = "assets.image15",
                music = R.raw.track15
            ),
            Music(
                name = "Teddy Bear's Picnic",
                cover = "assets.image16",
                music = R.raw.track16
            ),
            Music(
                name = "The Animals Went in Two by Two",
                cover = "assets.image17",
                music = R.raw.track17
            ),
            Music(
                name = "Row Row Row Your Boat",
                cover = "assets.image18",
                music = R.raw.track18
            ),
            Music(
                name = "Old King Cole",
                cover = "assets.image19",
                music = R.raw.track19
            ),
            Music(
                name = "1 2 3 4 5 Once I Caught a Fish Alive",
                cover = "assets.image20",
                music = R.raw.track20
            ),
            Music(
                name = "Hickory Dickory Dock",
                cover = "assets.image21",
                music = R.raw.track21
            ),
            Music(
                name = "Bobby Shaftoe",
                cover = "assets.image22",
                music = R.raw.track22
            ),
            Music(
                name = "Ten in the Bed",
                cover = "assets.image23",
                music = R.raw.track23
            ),
            Music(
                name = "Little Miss Muffet",
                cover = "assets.image24",
                music = R.raw.track24
            ),
            Music(
                name = "Humpty Dumpty",
                cover = "assets.image25",
                music = R.raw.track25
            ),
            Music(
                name = "This Old Man",
                cover = "assets.image26",
                music = R.raw.track26
            ),
            Music(
                name = "Five Little Speckled Frogs",
                cover = "assets.image27",
                music = R.raw.track27
            ),
            Music(
                name = "Baa Baa Black Sheep",
                cover = "assets.image28",
                music = R.raw.track28
            ),
            Music(
                name = "Ring a Ring O' Roses",
                cover = "assets.image29",
                music = R.raw.track29
            ),
            Music(
                name = "With My Foot I Tap Tap Tap",
                cover = "assets.image30",
                music = R.raw.track30
            ),
            Music(
                name = "Jack and Jill",
                cover = "assets.image31",
                music = R.raw.track31
            ),
            Music(
                name = "The Alphabet Song",
                cover = "assets.image32",
                music = R.raw.track32
            ),
            Music(
                name = "Polly Put the Kettle On",
                cover = "assets.image33",
                music = R.raw.track33
            ),
            Music(
                name = "She'll Be Coming Round the Mountain",
                cover = "assets.image34",
                music = R.raw.track34
            ),
            Music(
                name = "One Finger One Thumb",
                cover = "assets.image35",
                music = R.raw.track35
            ),
            Music(
                name = "One Man Went to Mow",
                cover = "assets.image36",
                music = R.raw.track36
            ),
            Music(
                name = "London Bridge Is Falling Down",
                cover = "assets.image37",
                music = R.raw.track37
            ),
            Music(
                name = "The Muffin Man",
                cover = "assets.image38",
                music = R.raw.track38
            ),
            Music(
                name = "Five Little Ducks",
                cover = "assets.image39",
                music = R.raw.track39
            ),
            Music(
                name = "Pop! Goes the Weasel",
                cover = "assets.image40",
                music = R.raw.track40
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

