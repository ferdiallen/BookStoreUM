package com.tia.bookstoreum.presentation.read

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tia.bookstoreum.ui.theme.BackgroundColor
import com.tia.bookstoreum.ui.theme.SliderColor

@Composable
fun ReadBookScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = BackgroundColor)
                .padding(start = 28.dp, end = 28.dp, top = 12.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back button",
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .weight(1F)
                        .clickable { }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(9F), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Catcher in the Rye", color = Color.Black)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "J.D Salinger", color = Color.Gray)
                }
                Icon(
                    imageVector = Icons.Filled.MoreVert, contentDescription = "Settings",
                    Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .weight(1F)
                        .clickable { }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = stringResource(id = com.tia.bookstoreum.R.string.lorem_ipsum_placeholder),
                textAlign = TextAlign.Justify
            )
        }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            BottomSettingsMenu()
        }
    }
}

@Composable
fun BottomSettingsMenu() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(102.dp)
            .shadow(28.dp, shape = RoundedCornerShape(12.dp), spotColor = Color.LightGray),
        elevation = 12.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        var sliderValue by remember {
            mutableStateOf(0F)
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 28.dp, end = 24.dp, top = 12.dp)
        ) {
            Text(
                text = "Font Size : ${sliderValue.times(100).toInt()} ",
                fontWeight = FontWeight.SemiBold
            )
            Slider(
                value = sliderValue,
                onValueChange = {
                    sliderValue = it
                },
                colors = SliderDefaults.colors(
                    activeTrackColor = SliderColor,
                    thumbColor = Color.White
                )
            )
        }
    }
}