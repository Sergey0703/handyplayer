package com.serhiibaliasnyi.cozyplayer.screens

import android.graphics.BitmapFactory
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.serhiibaliasnyi.cozyplayer.R
import com.serhiibaliasnyi.cozyplayer.ui.theme.irishGroverFontFamily


@Composable
@Preview(showSystemUi = true)
fun MainScreen(){
      var mainImage="image_default.jpg"
      Surface(
          modifier = Modifier
              .padding(0.dp)
              .fillMaxSize(),
          color = Color(125,150,141)
      ) {
          val list = stringArrayResource(id = R.array.playlistf)

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
                  AssetImage(mainImage)
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
                          onClick = { },
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
                              imageVector = ImageVector.vectorResource(R.drawable.button_prev),
                              contentDescription = "content description",
                              tint = Color(246, 151, 64),
                              modifier = Modifier.size(100.dp)
                          )
                      }


                      Button(
                          onClick = { },
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
                              imageVector = ImageVector.vectorResource(R.drawable.button_play),
                              contentDescription = "content description",
                              tint = Color(246, 151, 64),
                              modifier = Modifier.size(100.dp)
                          )
                      }

                      Button(
                          onClick = { },
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
                              tint = Color(246, 151, 64),
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
                    itemsIndexed(list){index, title->
                    Card(
                        colors = CardDefaults.cardColors(
                           // containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            containerColor = Color(0,81,65)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp)
                          ) {
                        Text(text = title,
                            textAlign = TextAlign.Center,
                            //fontFamily = FontFamily.Serif,
                            fontFamily = irishGroverFontFamily,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    mainImage="image"+index.toString()+".jpg";
                                    Log.d("image",mainImage)
                                }
                                .padding(0.dp, 20.dp)
                                .wrapContentWidth()

                        )
                    }
                }
            }

              }

          }
      }

}


@Composable
fun AssetImage(imageName: String){
    val context = LocalContext.current
    val assetManger = context.assets
    val inputStream = assetManger.open(imageName)
    val bitMap = BitmapFactory.decodeStream(inputStream)
    Image(
        bitmap = bitMap.asImageBitmap(),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}