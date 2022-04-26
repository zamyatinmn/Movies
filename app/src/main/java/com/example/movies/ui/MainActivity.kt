package com.example.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movies.Config
import com.example.movies.model.Movie
import com.example.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ListingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                Surface(Modifier.fillMaxSize()) {
                    val data = viewModel.movieList.observeAsState()
                    data.value?.let { MoviesList(it, viewModel) }
//                    data.value?.data?.results.let {
//                        if (it != null) {
//                            MoviesList(it, viewModel)
//                        }
//                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesList(list: List<Movie>, viewModel: ListingViewModel) {
    Column {
        Box(
            Modifier
                .background(Color(0xFFCCCCCC))
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(30.dp),
                text = "Top Movies",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h2
            )
        }
        val state = rememberLazyListState()
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            state = state
        ) {
            if (state.firstVisibleItemIndex == list.size / 2 - 6) {
                viewModel.fetchMovies()
            }
            items(list) {
                Card(modifier = Modifier.padding(20.dp), elevation = 0.dp) {
                    Column {
                        val imageUrl = "${Config.IMAGE_URL}${it.poster_path}"
                        val context = LocalContext.current
                        val height = (LocalConfiguration.current.screenHeightDp / 3).dp
                        Box(Modifier.padding(bottom = 20.dp)) {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height),
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(context)
                                        .data(imageUrl)
                                        .build()
                                ),
                                contentDescription = null
                            )
                            Votemeter(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(start = 80.dp, bottom = 10.dp),
                                vote = it.vote_average
                            )
                        }

                        Text(
                            modifier = Modifier.padding(horizontal = 30.dp),
                            text = it.title,
                            maxLines = 2,
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .alpha(0.6f),
                            text = it.release_date
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesTheme {
    }
}