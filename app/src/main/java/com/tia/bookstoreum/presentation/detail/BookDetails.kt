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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.StepSize
import com.tia.bookstoreum.R
import com.tia.bookstoreum.navigation.NavAddress
import org.koin.androidx.compose.get

@Composable
fun BookDetail(
    id: Int,
    controller: NavController
) {
    val imagePlaceholder =
        rememberAsyncImagePainter(model = stringResource(R.string.imgplaceholder))
    val context = LocalContext.current
    var ratingScore by remember {
        mutableStateOf(0F)
    }
    val viewModel = get<BookDetailViewModel>()
    viewModel.retrieveSelectedData(id)
    ratingScore = viewModel.bookDetails?.rating?.toFloat() ?: 0F
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            IconButton(onClick = { controller.popBackStack() }, modifier = Modifier.weight(1F)) {
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
                    model = viewModel.bookDetails?.cover.toString(),
                    contentDescription = "cover image",
                    modifier = Modifier
                        .size(210.dp, 295.dp)
                        .clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.FillBounds,
                    error = imagePlaceholder
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = viewModel.bookDetails?.title.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = viewModel.bookDetails?.author.toString(),
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
                        text = viewModel.bookDetails?.about.toString(),
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
                        text = viewModel.bookDetails?.body.toString(),
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
                        controller.navigate(NavAddress.ReadBookScreen.route)
                    })
                    Spacer(modifier = Modifier.width(8.dp))
                    ButtonBuyOrReadPreview(
                        text = "Buy for $ NoN",
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