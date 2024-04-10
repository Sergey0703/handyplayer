package com.serhiibaliasnyi.cozyplayer


//import com.example.musicplayer.ui.theme.MusicPlayerTheme
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Tracks
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.serhiibaliasnyi.cozyplayer.ui.theme.ActionColor
import com.serhiibaliasnyi.cozyplayer.ui.theme.GreenBackground
import com.serhiibaliasnyi.cozyplayer.ui.theme.GreenMain
import com.serhiibaliasnyi.cozyplayer.ui.theme.MainActionColor
import com.serhiibaliasnyi.cozyplayer.ui.theme.irishGroverFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//import java.time.format.TextStyle

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


@androidx.annotation.OptIn(UnstableApi::class)
@Composable
@OptIn(ExperimentalFoundationApi::class)
//@Preview(showSystemUi = true)
fun MainScreen(playList: List<MainActivity.Music>, player: ExoPlayer) {
    Log.d("counter", "Recomposition-------------------------------------------------------")
    //lateinit var player: ExoPlayer
    val context =   LocalContext.current
    //player = ExoPlayer.Builder(context).build()

    val listState = rememberLazyListState()
    // Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()
    val list =   remember {
        mutableListOf<String>()
    }

    var currentValue by remember { mutableStateOf(0L) }
    var isPlaying by remember { mutableStateOf(false) }

    val numberOfTrack = remember {
        mutableStateOf(0)
    }

    val playingSongIndex = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(numberOfTrack.value) {
        Log.d("counter","Launch1")
        playingSongIndex.intValue = numberOfTrack.value-1
      //  player.seekTo(numberOfTrack.value-1, 0)
    }

    LaunchedEffect(Unit) {
        //   Log.d("counter", "Launch0=" + player.currentMediaItemIndex.toString())
        playList.forEach {
            val path = "android.resource://" + "com.serhiibaliasnyi.cozyplayer" + "/" + it.music
            val mediaItem = MediaItem.fromUri(Uri.parse(path))
            player.addMediaItem(mediaItem)
            list.add(it.name)
        }
        player.prepare()
    }


    var firstLaunch = remember {
        mutableStateOf(true)
    }

    val currentPosition = remember {
        mutableLongStateOf(0)
    }

    val sliderPosition = remember {
        mutableLongStateOf(0)
    }

    val totalDuration = remember {
        mutableLongStateOf(0)
    }

    LaunchedEffect(player.currentMediaItemIndex) {
        // playingSongIndex.intValue = player.currentMediaItemIndex
        sliderPosition.longValue = 0
        currentPosition.longValue = 0

        playingSongIndex.value = player.currentMediaItemIndex
        coroutineScope.launch {
                listState.animateScrollToItem(player.currentMediaItemIndex)
        }
       Log.d("counter", "Launch=" )
        if (!firstLaunch.value){
            numberOfTrack.value= playingSongIndex.intValue+1
        }else{
            firstLaunch.value=false
        }
    }

    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying_: Boolean) {
                isPlaying = isPlaying_
            }
        }
        player.addListener(listener)
        onDispose {
            player.removeListener(listener)
        }
    }
    if (isPlaying) {
        LaunchedEffect(Unit) {
            while(true) {
                currentValue = player.currentPosition

                delay(1.seconds/5 )
               // delay(1000 )
                currentPosition.longValue = currentValue
                sliderPosition.longValue = currentPosition.longValue
            }
        }
    }
   // LaunchedEffect(key1 = player.currentPosition, key2 = player.isPlaying) {
      // LaunchedEffect(key1 = player.currentPosition) {
      //   Log.d("counter", "Launch4")
      //   delay(500)
      //   currentPosition.longValue = player.currentPosition
   // }

   // LaunchedEffect(currentPosition.longValue) {
      //  Log.d("counter", "Launch5")
      //  sliderPosition.longValue = currentPosition.longValue
   // }

    LaunchedEffect(player.duration) {

        if (player.duration > 0) {
     //       Log.d("counter", "Launch6")
            totalDuration.longValue = player.duration
        }
    }
/*
    player.addListener(
        object : Player.Listener {
            override fun onTracksChanged(tracks: Tracks) {
                sliderPosition.longValue = 0
                // Update UI using current tracks.
             //   Log.d("counter", "updateTrack=" + tracks.toString())
             //   Log.d("counter",
             //       "player.currentMediaItemIndex=" + player.currentMediaItemIndex.toString()
             //   )
                if (!firstLaunch.value) numberOfTrack.value = player.currentMediaItemIndex + 1;

           //     Log.d("counter", "firstLaunch=" + firstLaunch.value)
                if (player.duration > 0) {
                  //  Log.d("counter","Duration=${player.duration}")
                    totalDuration.longValue =player.duration
                }
                coroutineScope.launch {
                //55    listState.animateScrollToItem(player.currentMediaItemIndex)
                }
            }
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    isPlaying.value = false
                }
            }
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playWhenReady && playbackState == Player.STATE_READY) {
                    // media actually playing
                  //   Log.d("counter", "LaunchP------${player.currentPosition}")
                  //  sliderPosition.longValue = 0
                   // totalDuration.longValue =player.duration

                    //  delay(1000)
                } else if (playWhenReady) {
                 //   Log.d("counter", "LaunchP22------${player.currentPosition}")
                    // might be idle (plays after prepare()),
                    // buffering (plays when data available)
                    // or ended (plays when seek away from end)
                } else {
                    // player paused in any state
                    //  isPlaying.value=false
                }
            }
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    // Active playback.
                    //delay(1000)
                   // currentPosition.longValue = player.currentPosition
                    Log.d("counter","plsying=")
                } else {
                    // Not playing because playback is paused, ended, suppressed, or the player
                    // is buffering, stopped or failed. Check player.playWhenReady,
                    // player.playbackState, player.playbackSuppressionReason and
                    // player.playerError for details.
                }
            }
        }
    )
*/

    Row(
        modifier = Modifier
            .padding(5.dp)
            .background(color= GreenMain)

    ) {
        Log.d("counter", "Main111111111111111")
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f)) {
            Box(modifier = Modifier
                        .fillMaxHeight(0.7f)) {
                AssetImage(numberOfTrack)
            }

            Row(  modifier = Modifier
                             .background(color= GreenMain)
                            .fillMaxHeight(.1f)
                            .padding( 5.dp),
                            horizontalArrangement = Arrangement.Center,

                        ) {
                Log.d("counter", "Main222222222222222222")

                        TrackSlider(
                            value = sliderPosition.longValue.toFloat(),
                            onValueChange = {
                                sliderPosition.longValue = it.toLong()
                            },
                            onValueChangeFinished = {
                                currentPosition.longValue = sliderPosition.longValue
                                player.seekTo(sliderPosition.longValue)
                            },
                            songDuration = totalDuration.longValue.toFloat()
                        )
                    }

            Row( modifier = Modifier
                      //   .background(color= GreenMain)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Button(
                            onClick = {
                                if (numberOfTrack.value > 1) {
                                    sliderPosition.longValue = 0
                                    currentPosition.longValue = 0
                                    if (player.isPlaying) {
                                        player.seekToPreviousMediaItem()
                                        numberOfTrack.value = player.currentMediaItemIndex + 1
                                    } else {
                                        numberOfTrack.value -= 1
                                    }
                                }
                            },
                            // modifier= Modifier.size(100.dp),
                            shape = CircleShape,
                            // border= BorderStroke(5.dp, Color(0XFF0F9D58)),
                            contentPadding = PaddingValues(0.dp),
                            //colors = ButtonDefaults.outlinedButtonColors(contentColor =  ActionColor)
                            colors = ButtonDefaults.outlinedButtonColors()
                           ) {
                                Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.button_prev),
                                contentDescription = "content description",
                                // tint = Color(246, 151, 64),
                                tint = ActionColor,
                                modifier = Modifier.size(100.dp)
                            )
                        }

                        Button(
                            onClick = {
                                firstLaunch.value = false;
                                if (player.isPlaying) {
                                    player.pause()
                                } else {
                                    if (player.currentMediaItemIndex != numberOfTrack.value - 1) {
                                        if (numberOfTrack.value == 0) {
                                            numberOfTrack.value = 1
                                         //   player.seekTo(0, C.TIME_UNSET);
                                        } else {
                                            player.seekTo(numberOfTrack.value - 1, C.TIME_UNSET);
                                        }
                                    }
                                    player.setPlayWhenReady(true);
                                    player.play()
                                 }

                            },
                            shape = CircleShape,
                            //border= BorderStroke(7.dp, Color(255, 255, 255)),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.outlinedButtonColors()
                            ) {
                            if (!player.isPlaying) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.button_play),
                                    contentDescription = "content description",
                                    tint = MainActionColor,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(0.dp),
                                )
                            } else {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.button_pause),
                                    contentDescription = "content description",
                                    tint = MainActionColor,
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }

                        Button(
                            onClick = {
                                if (numberOfTrack.value < list.size) {

                                    sliderPosition.longValue = 0
                                    currentPosition.longValue = 0
                                    if (player.isPlaying) {
                                        player.seekToNextMediaItem()
                                    } else {
                                        numberOfTrack.value = numberOfTrack.value + 1
                                    }
                                }
                            },
                             shape = CircleShape,
                            // border= BorderStroke(5.dp, Color(0XFF0F9D58)),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.outlinedButtonColors()
                          ) {
                             Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.button_next),
                                contentDescription = "content description",
                                tint = ActionColor,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
        }//column
            Box(
                modifier =
                Modifier
                    .background(color = GreenBackground)
            ) {
                LazyColumn(state = listState) {
                    //itemsIndexed(list){index, title->
                    itemsIndexed(playList) { index, title ->
                        Card(
                            colors = CardDefaults.cardColors(
                                // containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                containerColor = GreenMain
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp)
                        ) {
                            val color = if (index + 1 == numberOfTrack.value) {
                                MainActionColor
                            } else {
                                ActionColor
                            }
                            Text(text = title.name,
                                textAlign = TextAlign.Center,
                                //fontFamily = FontFamily.Serif,
                                fontFamily = irishGroverFontFamily,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = color,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (player.isPlaying) {
                                            player.seekTo(index, C.TIME_UNSET);
                                            player.setPlayWhenReady(true);
                                            player.play()

                                        } else {
                                            sliderPosition.longValue = 0
                                        }
                                        numberOfTrack.value = index + 1
                                     }
                                    .wrapContentWidth()
                                    .padding(5.dp, 20.dp)
                            )
                        }
                    }
                } //Lazy Column
            } //box
        } //main Row
    } //constraint

/*private fun Long.convertToText(): String {
    val sec = this / 1000
    val minutes = sec / 60
    val seconds = sec % 60

    val minutesString = if (minutes < 10) {
        "0$minutes"
    } else {
        minutes.toString()
    }
    val secondsString = if (seconds < 10) {
        "0$seconds"
    } else {
        seconds.toString()
    }
    return "$minutesString:$secondsString"
}*/
@Composable
fun TrackSlider(
    value: Float,
    onValueChange: (newValue: Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    songDuration: Float
) {
    Slider(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
 //    steps = 5,
        onValueChangeFinished = {
            onValueChangeFinished()
        },
        valueRange = 0f..songDuration,
        colors = SliderDefaults.colors(
            thumbColor = MainActionColor,
            activeTrackColor = MainActionColor,
            inactiveTrackColor = ActionColor,
        )
    )
}

@Composable
fun AssetImage(trackName: MutableState<Int>) {
    /* var imageVisible by remember { mutableStateOf(false) }
     val imageAlpha: Float by animateFloatAsState(
         targetValue = if (imageVisible) 1f else 0f,
         animationSpec = tween(
             durationMillis = 2000,
             easing = LinearEasing,
         )
     )
    */
    val imageAlpha = 1f
    val context = LocalContext.current
    val assetManger = context.assets
    val inputStream = assetManger.open("image" + trackName.value.toString() + ".jpg")
    val bitMap = BitmapFactory.decodeStream(inputStream)
    Image(
        bitmap = bitMap.asImageBitmap(),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        alpha = imageAlpha,
        modifier = Modifier.fillMaxSize()
    )
}
