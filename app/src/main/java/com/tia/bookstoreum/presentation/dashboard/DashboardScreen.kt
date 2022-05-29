package com.tia.bookstoreum.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.tia.bookstoreum.R
import com.tia.bookstoreum.data.BooksResponseItem
import com.tia.bookstoreum.navigation.NavAddress
import com.tia.bookstoreum.ui.theme.BackgroundColor
import com.tia.bookstoreum.ui.theme.SliderColor
import com.tia.bookstoreum.utils.CustomSettings.menuList
import org.koin.androidx.compose.getViewModel

@Composable
fun DashboardScreen(controller: NavController) {
    val viewModel = getViewModel<DashboardViewModel>()
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
            .padding(start = 28.dp, end = 24.dp, top = 30.dp)
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu Icon"
            )
        }
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Welcome Back, Adit!",
                        color = Color.Gray,
                        fontSize = 15.sp,
                    )
                    Text(
                        text = "What do you want to read today?",
                        color = Color.Black,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(end = 70.dp)
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(color = Color(0xFFC4C4C4).copy(alpha = 0.15F))
                    ) {
                        Row(
                            Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                .align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                tint = Color.Gray,
                                contentDescription = "Search Icon",
                                modifier = Modifier.weight(1F)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Search",
                                color = Color.Gray,
                                fontSize = 17.sp,
                                modifier = Modifier.weight(4F)
                            )
                            Icon(
                                imageVector = Icons.Filled.Mic,
                                tint = Color.Gray,
                                contentDescription = "Mic",
                                modifier = Modifier.weight(1F)
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        items(items = menuList) { item ->
                            Column(modifier = Modifier
                                .size(90.dp, 30.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    if (!viewModel.selectedItem.contains(item)) {
                                        viewModel.selectedItem
                                            .add(item)
                                            .also {
                                                viewModel.selectedItem.removeIf { out ->
                                                    out != item
                                                }
                                            }
                                    }
                                }
                            ) {
                                Text(
                                    text = item,
                                    fontSize = 15.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                if (viewModel.selectedItem.isNotEmpty() &&
                                    viewModel.selectedItem.contains(item)
                                ) {
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 4.dp, end = 4.dp),
                                        color = SliderColor,
                                        thickness = 1.dp
                                    )
                                }
                            }
                        }
                    }
                    //TAMBAHIN GARIS BAWAH KLO PAS KE SELECT FER. WARNANYA MERAH YA

                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(viewModel.booksData) { model ->
                            Cover(model = model, controller)
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                }
            }
            item {
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "New Arrivals",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    LazyRow (horizontalArrangement = Arrangement.spacedBy(8.dp)){
                        items(viewModel.booksData) { out ->
                            Cover(model = out, controller)
                        }
                    }
                }
            }
        }
    }


    // BANTU YA FER, YG DIBAWAH VERSI TEXTFIELDNYA
    /*
    OutlinedTextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")},
        label = {
            Text(text = "Search")
        },
        trailingIcon = { Icon(imageVector = Icons.Filled.Mic, contentDescription = "Mic Icon")},
    )
     */
}

internal fun toBookDetail(
    controller: NavController,
    index: Int
) {
    controller.navigate(NavAddress.BookDetailsScreen.route + "/$index")
}

@Composable
internal fun Cover(model: BooksResponseItem, controller: NavController) {
    val imagePlaceholder =
        rememberAsyncImagePainter(model = stringResource(R.string.imgplaceholder))
    Column(modifier = Modifier
        .width(150.dp)
        .padding(start = 2.dp)
        .clip(RoundedCornerShape(12.dp))
        .clickable {
            toBookDetail(controller, model.id)
        }) {
        AsyncImage(
            model = model.cover,
            contentDescription = "CoverImage",
            modifier = Modifier
                .size(width = 150.dp, height = 250.dp)
                .clip(shape = RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillBounds,
            error = imagePlaceholder
        )
        Column(Modifier.padding(start = 12.dp)) {
            Text(
                text = model.title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = model.author,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}

