package com.serhiibaliasnyi.handyplayer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
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
          modifier = Modifier.fillMaxSize(),
          //color = MaterialTheme.colorScheme.background
          color = Color(125,150,141)
      ) {
          val list = stringArrayResource(id = R.array.playlistf)

          ConstraintLayout {
              val firstRect = createRef()
              Box(modifier = Modifier

                  // .size(300.dp, 300.dp)
                  //.wrapContentWidth()
                  //.height(500.dp)
                  .constrainAs(firstRect) {
                      //centerTo(parent)
                      start.linkTo(parent.start, margin = 20.dp)
                      end.linkTo(parent.end, margin = 20.dp)
                      top.linkTo(parent.top, margin = 20.dp)
                      //bottom.linkTo(parent., margin = 20.dp)
                      width = Dimension.fillToConstraints
                      //height=Dimension.value(300.dp)
                      height = Dimension.percent(0.5f)
                  }
                  //.fillMaxSize()
                  .background(color = Color.Red)
              ) {}
              Box(modifier =
              Modifier
                  //.size(300.dp, 300.dp)
                  //.wrapContentWidth()
                  //.height(300.dp)
                  .constrainAs(createRef()) {
                      // centerTo(parent)
                      start.linkTo(parent.start, margin = 20.dp)
                      end.linkTo(parent.end, margin = 20.dp)
                      top.linkTo(firstRect.bottom, margin = 20.dp)
                      bottom.linkTo(parent.bottom, margin = 10.dp)
                      width = Dimension.fillToConstraints
                      height = Dimension.fillToConstraints
                      //  height=Dimension.value(300.dp)
                  }
                  //.fillMaxSize()
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
                           // .background(color = Color(0x005141))
                            //.backgroundColor=Color.Green
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
                               // .background(color = Color(0xFF03DAC5))

                        )
                    }
                }
            }

              }

          }
      }
}