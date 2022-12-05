package org.compose.news.android.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import org.compose.news.android.MainActivityViewModel
import org.compose.news.android.core.BaseComposeFragment
import org.compose.news.android.dimes.*
import org.compose.news.android.presentation.GenericListViewRenderer
import org.compose.news.android.presentation.viewcomponets.LargeBoldText
import org.compose.news.android.presentation.viewcomponets.MediumTextBold
import org.compose.news.android.presentation.viewcomponets.RegularText
import org.compose.news.data.model.ArticlesItem
import org.compose.news.android.R


/**
 * A simple [Fragment] subclass.
 * Use the [NewsHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsHomeFragment : BaseComposeFragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    @Composable
    override fun initCompoUI() {
        Scaffold(topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(top = topFullScreenInset)
            ) {
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
        }) { padding ->
            padding.calculateBottomPadding()
            val articleResult = mainActivityViewModel.articlesByCountry.collectAsState()

            GenericListViewRenderer(
                articleResult.value.result?.articles,
                loadComplete = articleResult.value.isLoaded,
                isError = articleResult.value.error?.isEmpty() == false,
                onRetryClicked = {
                    mainActivityViewModel.getArticlesByCountry(mainActivityViewModel.articlesSupportedCountries.last().code)
                }
            ) {
                LazyColumn(
                    content = {
                        items(articleResult.value.result?.articles ?: emptyList()) { article ->
                            NewsItem(articlesItem = article, onClick = {currentItem ->
                                mainActivityViewModel.setCurrentNewsItem(currentItem)
                                findNavController().navigate(R.id.newsDetailsFragment)
                            })
                        }
                    },
                    verticalArrangement = Arrangement.spacedBy(spacing10dp),
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }

    override fun viewCreated() {
        //Code Should be from the UI
        mainActivityViewModel.getArticlesByCountry(mainActivityViewModel.articlesSupportedCountries.last().code)
    }


    override fun onResume() {
        super.onResume()
        if (mainActivityViewModel.articlesByCountry.value.isLoaded) {
            mainActivityViewModel.getArticlesByCountry(mainActivityViewModel.articlesSupportedCountries.last().code)
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsItem(articlesItem: ArticlesItem, onClick: (ArticlesItem) -> Unit) {
    Card(shape = RoundedCornerShape(size10dp), elevation = 3.dp, onClick = {
        onClick(articlesItem)
    }, modifier = Modifier.padding(horizontal = spacing10dp, vertical = spacing2dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing10dp), modifier = Modifier.padding(
                spacing10dp
            )
        ) {
            ArticlesImageHeader(articlesItem)
            MediumTextBold(text = articlesItem.title)
            RegularText(text = articlesItem.description)
        }
    }
}

@Composable
fun ArticlesImageHeader(articlesItem:ArticlesItem){
    AsyncImage(
        model = articlesItem.urlToImage,
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(articleImageHeight)
            .clip(RoundedCornerShape(size10dp)),
        contentScale = ContentScale.Crop,
    )
}