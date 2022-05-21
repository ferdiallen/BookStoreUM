package com.tia.bookstoreum.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.StepSize

@Preview(showBackground = true)
@Composable
fun BookDetail(
    bookName: String = "",
    creatorName: String = "",
    rating: Float = 0F,
    aboutAuthorText: String = "",
    coverImage: String = "",
    overviewBook: String = "",
    price: String = ""
) {
    val context = LocalContext.current
    var ratingScore by remember {
        mutableStateOf(0F)
    }
    ratingScore = rating
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1F)) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "backfrom details",
                    modifier = Modifier.size(26.dp)
                )

            }
            Spacer(modifier = Modifier.weight(5F))
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1F)) {
                Icon(
                    imageVector = Icons.Outlined.BookmarkBorder,
                    contentDescription = "Bookmarkbutton",
                    modifier = Modifier.size(26.dp)
                )
            }
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                AsyncImage(
                    model = coverImage,
                    contentDescription = "cover image",
                    modifier = Modifier
                        .size(221.dp, 280.dp)
                        .clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = bookName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = creatorName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp, color = Color.LightGray
                )
                RatingBar(value = ratingScore, onValueChange = {

                }, onRatingChanged = {

                }, config = RatingBarConfig().numStars(5)
                    .isIndicator(true).stepSize(StepSize.HALF)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)) {
                    Text(
                        text = "About the Author",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                    Text(
                        text = aboutAuthorText,
                        textAlign = TextAlign.Justify,
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Overview",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = overviewBook,
                        textAlign = TextAlign.Justify,
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp, start = 12.dp)
                ) {
                    ButtonBuyOrReadPreview(text = "Read Previews", color = Color.Red, onClick = {
                        Toast.makeText(context, "Tapped", Toast.LENGTH_SHORT).show()
                    })
                    Spacer(modifier = Modifier.width(8.dp))
                    ButtonBuyOrReadPreview(
                        text = "Buy for $${price}",
                        color = Color.Black,
                        onClick = {
                            Toast.makeText(context, "Tapped", Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }
    }
}

@Composable
fun ButtonBuyOrReadPreview(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(170.dp, 50.dp)
            .clickable {
                onClick.invoke()
            },
        backgroundColor = color,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = Color.White
            )
        }
    }
}