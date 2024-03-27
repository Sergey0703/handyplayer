package com.serhiibaliasnyi.cozyplayer.screens

//import android.content.Context

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.media.browse.MediaBrowser
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.media3.exoplayer.ExoPlayer
import com.serhiibaliasnyi.cozyplayer.MainActivity
import com.serhiibaliasnyi.cozyplayer.R
import com.serhiibaliasnyi.cozyplayer.ui.theme.irishGroverFontFamily
import androidx.media3.common.MediaItem
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
//import com.example.musicplayer.ui.theme.MusicPlayerTheme
import kotlinx.coroutines.delay


@Composable
//@Preview(showSystemUi = true)
fun MainScreen(player:ExoPlayer,playList: List<MainActivity.Music>){
      val listTrack=listOf(R.raw.track1,R.raw.track2,R.raw.track3,R.raw.track4,R.raw.track5,
          R.raw.track6,R.raw.track7,R.raw.track8,R.raw.track9,R.raw.track10
          )
     // var mediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.track1)
     //lateinit  var mediaPlayer:MediaPlayer
    var mediaPlayer = MediaPlayer()
    //val list:List<String>
    val list = mutableListOf<String>()
    var i:Int=0
    LaunchedEffect(Unit) {
        playList.forEach {
            val path = "android.resource://" + "com.serhiibaliasnyi.cozyplayer" + "/" + it.music
            val mediaItem = MediaItem.fromUri(Uri.parse(path))
            player.addMediaItem(mediaItem)
            list.add(it.name)
        }
    }
    player.prepare()
    //mediaPlayer=null
   // mediaPlayer.setDataSource(filepath)

      //var track=R.raw.track1
      val context = LocalContext.current
      val numberOfTrack= remember {
          mutableStateOf(0)
      }

      val isPlaying= remember{
          mutableStateOf(false)
      }
     //var track=R.raw.track1
     //var track=R.raw.track1;
    // val mediaPlayer:MediaPlayer by remember {
    //    mutableStateOf(MediaPlayer.create(context,listTrack[numberOfTrack.value]))
    // }



      Surface(
          modifier = Modifier
              .padding(0.dp)
              .fillMaxSize(),
          color = Color(125,150,141)
      ) {

       //   val list = stringArrayResource(id = R.array.playlistf)
          //val list =playList
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
                      height = Dimension.percent(0.7f)
                  }
                  //.background(color = Color.Red)
                  .background(color = Color(125, 150, 141))

              ) {
                  /*Image(
                      //painter = painterResource(id = R.drawable.default_screen),
                      painter = painterResource(id = R.drawable.default_screen),
                      contentDescription = null, //stringResource(id = R.string.dog_content_description),
                      contentScale = ContentScale.Crop,
                      modifier = Modifier
                          .fillMaxSize()
                          //.size(200.dp)
                          .clip(RoundedCornerShape(5.dp))
                  )

                   */
                  AssetImage(numberOfTrack.value,mediaPlayer)
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
                      height = Dimension.percent(0.3f)
                  }
                  .background(color = Color(0, 81, 65)),
                  contentAlignment = Alignment.Center
              ) {
                  Row(
                      modifier = Modifier
                          //.background(Color.Gray)
                          //.fillMaxSize(),
                          .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceEvenly,
                           verticalAlignment = Alignment.CenterVertically

                     ) {
                      Button(
                          onClick = {
                              if(numberOfTrack.value>1) numberOfTrack.value -= 1
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
                              if (isPlaying.value) {
                                  player.pause()
                              } else {
                                  player.play()
                              }
                              isPlaying.value = player.isPlaying
                            /*
                              Log.d("state",isPlaying.value.toString())
                              if(numberOfTrack.value==0){ numberOfTrack.value=1}
                              isPlaying.value=!isPlaying.value
                              //val context = this
                              //val assetManger = context.assets
                              Log.d("state",numberOfTrack.value.toString())
                             if(isPlaying.value){
                                // Log.d("state", mediaPlayer.toString())
                                 //if (mediaPlayer == null) {
                                 val isPaused =
                                     !mediaPlayer.isPlaying && mediaPlayer.currentPosition > 1
                                 if (!isPaused) {
                                     Log.d("state", numberOfTrack.value.toString())
                                     mediaPlayer.stop()
                                     mediaPlayer.reset()
                                     mediaPlayer.release()
                                  //   mediaPlayer=null
                                     mediaPlayer = MediaPlayer.create(context, listTrack[numberOfTrack.value-1]);
                                     mediaPlayer.start()
                                 }else {
                                     mediaPlayer.start()
                                 }
                                 //AssetPlayer(numberOfTrack.value, context,"start")
                             }else{
                                 if (mediaPlayer != null) {
                                     mediaPlayer?.pause()
                                     Log.d("state","pause")
                                 }
                                 //AssetPlayer(numberOfTrack.value, context,"pause")
                             }

                             */

                          },
                          // modifier= Modifier.size(100.dp),
                          shape = CircleShape,
                          // border= BorderStroke(8.dp, Color(246, 151, 64)),
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
                              modifier = Modifier.size(100.dp)
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
                              if(numberOfTrack.value<list.size)
                             numberOfTrack.value= numberOfTrack.value+1
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
                   LazyColumn {
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
                                    //mediaPlayer = null
                                    numberOfTrack.value = index + 1
                                    Log.d("image", numberOfTrack.value.toString())
                                }
                                .wrapContentWidth()
                                .padding(5.dp, 20.dp)


                        )
                    }
                }
            }

              }

          }
      }

}


@Composable
fun AssetImage(trackName: Int, mediaPlayer: MediaPlayer?){
    if (mediaPlayer != null) {
      //  mediaPlayer.stop()
        //mediaPlayer.reset()
        //mediaPlayer.release()
        //mediaPlayer =null
        Log.d("state", "asset")
    }

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
    //return null
}
//@Composable
fun AssetPlayer(trackName: Int, context: Context,comand: String){
    //val context = LocalContext.current
    //val assetManger = context.assets
    if(comand=="start") {
      //  val mediaPlayer = MediaPlayer.create(context, )
     //   mediaPlayer.start()
    }
   // return mediaPlayer
   // mediaPlayer.start()

}