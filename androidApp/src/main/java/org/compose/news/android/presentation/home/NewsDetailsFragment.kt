package org.compose.news.android.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.compose.news.android.MainActivityViewModel
import org.compose.news.android.core.BaseComposeFragment
import org.compose.news.android.core.ColumnInset
import org.compose.news.android.core.HorizontalCustomTopAppBar
import org.compose.news.android.dimes.spacing6dp
import org.compose.news.android.presentation.viewcomponets.RegularText
import org.compose.news.android.presentation.viewcomponets.RegularTextContent


class NewsDetailsFragment : BaseComposeFragment() {

    private val mainActivityViewModel:MainActivityViewModel by activityViewModels()

    @Composable
    override fun initCompoUI() {
        val currentArticle by mainActivityViewModel.currentNewsItem.collectAsState()
        Scaffold(topBar = {
            HorizontalCustomTopAppBar(title = "") {
                findNavController().popBackStack()
            }
        }) {
            ColumnInset(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Column(Modifier.fillMaxWidth().padding(top = spacing6dp), verticalArrangement = Arrangement.spacedBy(
                    spacing6dp)) {
                    ArticlesImageHeader(articlesItem = currentArticle)
                    RegularText(text = currentArticle.author, size = 14.sp)
                    RegularText(text = currentArticle.publishedAt, size = 14.sp)
                }
                RegularTextContent(text = currentArticle.content)
            }
        }

    }

    override fun viewCreated() {

    }

    @Composable
    fun DetailsScreen(){

    }



}