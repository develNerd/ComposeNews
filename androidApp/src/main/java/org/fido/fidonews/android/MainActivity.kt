package org.fido.fidonews.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.fido.fidonews.android.dimes.*
import org.fido.fidonews.android.presentation.GenericListViewRenderer
import org.fido.fidonews.android.presentation.viewcomponets.LargeBoldText
import org.fido.fidonews.android.presentation.viewcomponets.MediumTextBold
import org.fido.fidonews.android.presentation.viewcomponets.RegularText
import org.fido.fidonews.data.model.ArticlesItem


class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.getArticlesByCountry(mainActivityViewModel.articlesSupportedCountries.last().code)
        setContent {
            FidoNewsTheme {
                Scaffold(topBar = {
                    Box(contentAlignment = Alignment.Center) {
                        Row(
                            Modifier.padding(vertical = spacing10dp, horizontal = spacing12dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                spacing12dp
                            )
                        ) {
                            Image(
                                modifier = Modifier.height(size32dp),
                                painter = painterResource(id = R.drawable.app_icon),
                                contentDescription = stringResource(
                                    id = R.string.app_name
                                )
                            )
                            LargeBoldText(text = stringResource(id = R.string.news))
                        }
                    }
                }) {
                    val articleResult = mainActivityViewModel.articlesByCountry.collectAsState()




                    GenericListViewRenderer(
                        articleResult.value.result?.articles,
                        loadComplete = articleResult.value.isLoaded,
                        isError = articleResult.value.error?.isEmpty() == false
                    ) {
                            LazyColumn(content = {
                                items(articleResult.value.result?.articles?: emptyList()) { article ->
                                    NewsItem(articlesItem = article, onClick = {

                                    })
                                }
                            }, verticalArrangement = Arrangement.spacedBy(spacing10dp), modifier = Modifier.fillMaxSize())
                        }

                }
            }
        }
    }
}

@Composable
fun ArticlesScreen(listOfArticles: List<ArticlesItem>) {

}

@Composable
fun NewsItem(articlesItem: ArticlesItem, onClick: (ArticlesItem) -> Unit) {
    Card(shape = RoundedCornerShape(size10dp), elevation = 3.dp, modifier = Modifier.padding(horizontal = spacing10dp, vertical = spacing2dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing10dp), modifier = Modifier.padding(
            spacing10dp)) {
            AsyncImage(
                model = articlesItem.url,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(articleImageHeight)
                    .clip(RoundedCornerShape(size10dp))
                    .padding(
                        spacing10dp
                    ),
                contentScale = ContentScale.Crop,
            )
            MediumTextBold(text = articlesItem.title)
            RegularText(text = articlesItem.description)
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    FidoNewsTheme {
        Greeting("Hello, Android!")
    }
}
