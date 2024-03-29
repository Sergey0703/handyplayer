package com.serhiibaliasnyi.cozyplayer.screens


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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Tracks
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.serhiibaliasnyi.cozyplayer.MainActivity
import com.serhiibaliasnyi.cozyplayer.R
import com.serhiibaliasnyi.cozyplayer.ui.theme.irishGroverFontFamily
import kotlinx.coroutines.launch

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


@androidx.annotation.OptIn(UnstableApi::class) @Composable
@OptIn(ExperimentalFoundationApi::class)
//@Preview(showSystemUi = true)
fun MainScreen(playList: List<MainActivity.Music>){
    Log.d("counter", "Recomposition-------------------------------------------------------")
     lateinit var player: ExoPlayer
     //val context = ContextAmbient.current
     val context = LocalContext.current
     player = ExoPlayer.Builder(context).build()

    val listState = rememberLazyListState()
// Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()
    val list = mutableListOf<String>()

    //var i:Int=0
    LaunchedEffect(Unit) {
        Log.d("counter","Launch0="+player.currentMediaItemIndex.toString())
        playList.forEach {
            val path = "android.resource://" + "com.serhiibaliasnyi.cozyplayer" + "/" + it.music
            val mediaItem = MediaItem.fromUri(Uri.parse(path))
            player.addMediaItem(mediaItem)
            list.add(it.name)
        }
    }
    player.prepare()


      val numberOfTrack= remember {
          mutableStateOf(0)
      }

      val playingSongIndex = remember {
         mutableIntStateOf(0)
      }

      var firstLaunch= remember {
          true //mutableStateOf(true)
      }
      LaunchedEffect(player.currentMediaItemIndex) {
      //LaunchedEffect(player.currentMediaItemIndex) {

         // playingSongIndex.intValue = player.currentMediaItemIndex
          playingSongIndex.value = player.currentMediaItemIndex
          Log.d("counter","Launch="+player.currentMediaItemIndex.toString())
          coroutineScope.launch {

              listState.animateScrollToItem(player.currentMediaItemIndex)
          }
       //   numberOfTrack.value= playingSongIndex.intValue

    }

      val isPlaying= remember{
          mutableStateOf(false)
      }
      player.addListener(
        object : Player.Listener {
            override fun onTracksChanged(tracks: Tracks) {
                // Update UI using current tracks.
                Log.d("counter","updateTrack="+tracks.toString())
                Log.d("counter","player.currentMediaItemIndex="+player.currentMediaItemIndex.toString())
                 if(!firstLaunch) numberOfTrack.value =player.currentMediaItemIndex+1;
                 Log.d("counter","firstLaunch="+firstLaunch)
                coroutineScope.launch {
                    listState.animateScrollToItem(player.currentMediaItemIndex)
                }
            // Log.d("counter","updateTrack="+playingSongIndex.value.toString())
            }
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    isPlaying.value=false
                }
            }

            /*
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playWhenReady && playbackState == Player.STATE_READY) {
                    // media actually playing

                } else if (playWhenReady) {
                    // might be idle (plays after prepare()),
                    // buffering (plays when data available)
                    // or ended (plays when seek away from end)
                } else {
                    // player paused in any state
                  //  isPlaying.value=false
                }
              }
            */
        }
    )



          ConstraintLayout(modifier = Modifier
              .padding(5.dp)) {

              val firstRect = createRef()
              val dashboardRec = createRef()
              Box(modifier = Modifier
                  // .size(300.dp, 300.dp)
                  .constrainAs(firstRect) {
                      //centerTo(parent)
                      start.linkTo(parent.start, margin = 0.dp)
                      // end.linkTo(parent.end, margin = 0.dp)
                      top.linkTo(parent.top, margin = 0.dp)
                      //bottom.linkTo(parent.bottom, margin = 0.dp)
                      width = Dimension.percent(0.6f)
                      //height=Dimension.value(300.dp)
                      //height = Dimension.fillToConstraints
                      height = Dimension.percent(0.6f)
                  }
                  //.background(color = Color.Red)
                  .background(color = Color(125, 150, 141))

              ) {

                  AssetImage(numberOfTrack.value)
                 // mediaPlayer= AssetPlayer(numberOfTrack.value)
              }
              Box(modifier = Modifier
                  // .size(300.dp, 300.dp)
                  .constrainAs(dashboardRec) {
                      //centerTo(parent)
                      start.linkTo(parent.start, margin = 0.dp)
                      // end.linkTo(parent.end, margin = 0.dp)
                      top.linkTo(firstRect.bottom, margin = 0.dp)
                      bottom.linkTo(parent.bottom, margin = 0.dp)
                      width = Dimension.percent(0.6f)
                      //height=Dimension.value(300.dp)
                      //height = Dimension.fillToConstraints
                      height = Dimension.percent(0.4f)
                  }
                  .background(color = Color(0, 81, 65)),
                  contentAlignment = Alignment.Center
              ) {
                  Column(){
                  Row(
                      modifier = Modifier
                          //.background(Color.Gray)
                          //.fillMaxSize(),
                          .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceEvenly,
                           verticalAlignment = Alignment.CenterVertically

                     ) {
//                      ControlButton(icon = R.drawable.ic_previous, size = 40.dp, onClick = {
//                          player.seekToPreviousMediaItem()
//                      })
                      Button(
                          onClick = {

                              if(numberOfTrack.value>1) {
                                  if(player.isPlaying) {
                                      //    numberOfTrack.value -= 1
                                      player.seekToPreviousMediaItem()
                                      coroutineScope.launch {
                                          // listState.animateScrollToItem(numberOfTrack.value)
                                     // listState.animateScrollToItem(playingSongIndex.value)
                                      }
                                      numberOfTrack.value =player.currentMediaItemIndex+1
                                  }else{
                                      numberOfTrack.value -= 1
                                      coroutineScope.launch {
                                          listState.animateScrollToItem(numberOfTrack.value-1)
                                      }

                                  }

                              }
                           },
                          // modifier= Modifier.size(100.dp),
                          shape = CircleShape,
                          // border= BorderStroke(5.dp, Color(0XFF0F9D58)),
                          contentPadding = PaddingValues(0.dp),
                          //colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.White)
                          colors = ButtonDefaults.outlinedButtonColors()
                          //colors = Color(246, 151, 64),
                      ) {
                          // Adding an Icon "Add" inside the Button
                          Icon(
                              imageVector = ImageVector.vectorResource(R.drawable.button_prev),
                              contentDescription = "content description",
                             // tint = Color(246, 151, 64),
                              tint = Color(255, 255, 255),
                              modifier = Modifier.size(100.dp)
                          )
                      }


                      Button(

                          onClick = {
                              firstLaunch=false;
                              if (isPlaying.value) {

                                  player.pause()
                                  isPlaying.value = false
                              } else {
                                  var track=numberOfTrack.value-1;
                                  if(numberOfTrack.value==0){
                                      track=0
                                      numberOfTrack.value=1
                                  }
                                  player.seekTo(track, C.TIME_UNSET);
                                  player.setPlayWhenReady(true);
                                  player.play()
                                  isPlaying.value = true
                              }

                          },
                          // modifier= Modifier.size(100.dp),
                          shape = CircleShape,
                          //border= BorderStroke(7.dp, Color(255, 255, 255)),
                          contentPadding = PaddingValues(0.dp),
                          //colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Blue)
                          colors = ButtonDefaults.outlinedButtonColors()
                          //colors = ButtonDefaults.Transparent
                      ) {
                          // Adding an Icon "Add" inside the Button
                          if(!isPlaying.value){
                          Icon(
                              imageVector = ImageVector.vectorResource(R.drawable.button_play),
                              contentDescription = "content description",
                              tint = Color(246, 151, 64),
                              //tint = Color(255, 255, 255),
                              modifier = Modifier
                                  .size(100.dp)
                                  .padding(0.dp),
                             // size=20.dp
                          )} else{
                              Icon(
                                  imageVector = ImageVector.vectorResource(R.drawable.button_pause),
                                  contentDescription = "content description",
                                  tint = Color(246, 151, 64),
                                  //tint = Color(255, 255, 255),
                                  modifier = Modifier.size(100.dp)
                              )
                          }
                      }

                      Button(
                          onClick = {
                              if(numberOfTrack.value<list.size) {
                                  if(player.isPlaying){
                                  player.seekToNextMediaItem()
                                  //Log.d("state",player.seekToNextMediaItem().toString())
                                  coroutineScope.launch {
                                     // listState.animateScrollToItem(playingSongIndex.value)
                                  }
                                      numberOfTrack.value =player.currentMediaItemIndex+1
                                  }else{
                                      numberOfTrack.value = numberOfTrack.value + 1
                                      coroutineScope.launch {
                                          listState.animateScrollToItem(numberOfTrack.value-1)
                                      }
                                  }
                              }
                          },
                          // modifier= Modifier.size(100.dp),
                          shape = CircleShape,
                          // border= BorderStroke(5.dp, Color(0XFF0F9D58)),
                          contentPadding = PaddingValues(0.dp),
                          //colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Blue)
                          colors = ButtonDefaults.outlinedButtonColors()
                          //colors = ButtonDefaults.Transparent
                      ) {
                          // Adding an Icon "Add" inside the Button
                          Icon(
                              imageVector = ImageVector.vectorResource(R.drawable.button_next),
                              contentDescription = "content description",
                              //tint = Color(246, 151, 64),
                              tint = Color(255, 255, 255),
                              modifier = Modifier.size(100.dp)
                          )
                      }
                  }

                  }

              }

              Box(modifier =
              Modifier
                  .constrainAs(createRef()) {
                      // centerTo(parent)
                      start.linkTo(firstRect.end, margin = 5.dp)
                      end.linkTo(parent.end, margin = 0.dp)
                      top.linkTo(parent.top, margin = 0.dp)
                      bottom.linkTo(parent.bottom, margin = 0.dp)
                      width = Dimension.fillToConstraints
                      height = Dimension.fillToConstraints
                      //  height=Dimension.value(300.dp)
                  }
                  .background(color = Color(125, 150, 141))
              ) {
                   LazyColumn(state = listState) {
                    //itemsIndexed(list){index, title->
                    itemsIndexed(playList){index, title->
                    Card(
                        colors = CardDefaults.cardColors(
                           // containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            containerColor = Color(0,81,65)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp)
                          ) {
                        val color = if (index+1==numberOfTrack.value) {
                            Color(246, 151, 64)
                        } else {
                            Color.White
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
                                    numberOfTrack.value = index + 1
                                    //playingSongIndex.value=index;
                                    // Log.d("image", numberOfTrack.value.toString())
                                }
                                .wrapContentWidth()
                                .padding(5.dp, 20.dp)


                        )
                    }
                }
            }

              }

          }
      //}

}


@Composable
fun AssetImage(trackName: Int){


    val context = LocalContext.current
    val assetManger = context.assets
    val inputStream = assetManger.open("image"+trackName.toString()+".jpg")
    val bitMap = BitmapFactory.decodeStream(inputStream)
    Image(
        bitmap = bitMap.asImageBitmap(),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

}
