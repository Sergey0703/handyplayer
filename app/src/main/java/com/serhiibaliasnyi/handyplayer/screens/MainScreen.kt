package com.serhiibaliasnyi.handyplayer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.serhiibaliasnyi.handyplayer.R


@Composable
@Preview(showSystemUi = true)
fun MainScreen(){
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
                      width = Dimension.percent(0.5f)
                      //height=Dimension.value(300.dp)
                      //height = Dimension.fillToConstraints
                      height = Dimension.percent(0.7f)
                  }
                  //.background(color = Color.Red)
                  .background(color = Color(125,150,141))

              ) {
                  Image(
                      painter = painterResource(id = R.drawable.default_screen),
                      contentDescription = null, //stringResource(id = R.string.dog_content_description),
                      contentScale = ContentScale.Crop,
                      modifier = Modifier
                          .fillMaxSize()
                          //.size(200.dp)
                          .clip(RoundedCornerShape(5.dp))
                  )
              }
              Box(modifier = Modifier
                  // .size(300.dp, 300.dp)
                  .constrainAs(dashboardRec) {
                      //centerTo(parent)
                      start.linkTo(parent.start, margin = 0.dp)
                      // end.linkTo(parent.end, margin = 0.dp)
                      top.linkTo(firstRect.bottom, margin = 0.dp)
                      bottom.linkTo(parent.bottom, margin = 0.dp)
                      width = Dimension.percent(0.5f)
                      //height=Dimension.value(300.dp)
                      //height = Dimension.fillToConstraints
                      height = Dimension.percent(0.3f)
                  }
                  .background(color = Color(0,81,65))
              ) {

              }

              Box(modifier =
                  Modifier
                  .constrainAs(createRef()) {
                      // centerTo(parent)
                      start.linkTo(firstRect.end, margin = 10.dp)
                      end.linkTo(parent.end, margin = 0.dp)
                      top.linkTo(parent.top, margin = 0.dp)
                      bottom.linkTo(parent.bottom, margin = 0.dp)
                      width = Dimension.fillToConstraints
                      height = Dimension.fillToConstraints
                      //  height=Dimension.value(300.dp)
                  }
                  .background(color = Color(125,150,141))
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
                            fontFamily = FontFamily.Serif,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                }
                                .padding(0.dp,20.dp)
                                .wrapContentWidth()
                        )
                    }
                }
            }

              }

          }
      }
}