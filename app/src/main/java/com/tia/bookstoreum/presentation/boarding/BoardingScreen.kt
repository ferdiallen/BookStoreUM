package com.tia.bookstoreum.presentation.boarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.tia.bookstoreum.navigation.NavAddress
import com.tia.bookstoreum.ui.theme.SliderColor
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BoardingScreen(controller: NavController) {
    val vM = getViewModel<BoardingViewModel>()
    val statePager = rememberPagerState()
    Column(Modifier.fillMaxSize()) {
        HorizontalPager(
            count = vM.pageList.count(),
            state = statePager,
            modifier = Modifier.weight(4F)
        ) { position ->
            PagerScreenTemplate(
                boarding = vM.pageList[position],
                position,
                vM.pageList.lastIndex, vM, controller
            )
        }

        HorizontalPagerIndicator(
            pagerState = statePager,
            activeColor = SliderColor,
            indicatorWidth = 12.dp,
            indicatorHeight = 6.dp,
            spacing = 7.dp,
            indicatorShape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .weight(1F)
                .align(CenterHorizontally)
        )

    }
}

@Composable
fun PagerScreenTemplate(
    boarding: OnboardingSlides,
    currPosition: Int, lastPosition: Int, vM: BoardingViewModel, controller: NavController
) {
    Column(
        Modifier
            .padding(start = 12.dp, end = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = boarding.image,
            contentDescription = "imageBoarding",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(370.dp)
        )

        Spacer(modifier = Modifier.height(42.dp))
        Text(
            text = boarding.mainTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = boarding.contentTitle,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(18.dp))
        if (currPosition == lastPosition) {
            Button(
                onClick = {
                    vM.hasAccepted()
                    controller.navigate(NavAddress.MainScreen.route) {
                        this.popUpTo(NavAddress.BoardingScreen.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.size(172.dp, 50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = SliderColor)
            ) {
                Text(text = "Start", color = Color.White)
            }
        }

    }
}