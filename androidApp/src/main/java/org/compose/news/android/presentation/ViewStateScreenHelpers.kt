package org.compose.news.android.presentation

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import org.compose.news.android.R
import org.compose.news.android.contants.TestDoubleTags.EMPTY_STATE_TEST_TAG
import org.compose.news.android.contants.TestDoubleTags.ERROR_RETRY_TEST_TAG
import org.compose.news.android.dimes.large_bold_text_size
import org.compose.news.android.dimes.mediumPadding
import org.compose.news.android.presentation.viewcomponets.GenericCircleProgressIndicator
import org.compose.news.android.presentation.viewcomponets.RegularText
import org.compose.news.android.presentation.viewcomponets.RoundedOutlinedButton

@Composable
fun <T> GenericListViewRenderer(list:List<T>? = null, loadComplete:Boolean, isError:Boolean = false, emptyStateValue:String = stringResource(id =  R.string.nothing_here),onRetryClicked: () -> Unit, content: @Composable () -> Unit){

    Crossfade(targetState = list){ retrievedList ->
        when{
            isError -> {
                ErrorRetryStateScreen(text = stringResource(id = R.string.something_went_wrong),onRetryClicked)
            }
            !loadComplete && retrievedList == null  -> {
                GenericCircleProgressIndicator()
            }
            loadComplete && retrievedList?.isEmpty()  == true -> {
                EmptyStateText(text = emptyStateValue)
            }
            loadComplete -> {
                Log.e("Content","Conent Loaded")
                content()
            }

            //loadComplete and List is not Empty

        }
    }

}


@Composable
fun EmptyStateText(text:String){
    Box(modifier = Modifier.fillMaxSize().testTagged(EMPTY_STATE_TEST_TAG), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(mediumPadding)) {
            RegularText(
                text = text,
                color = MaterialTheme.colors.onBackground,
                size = large_bold_text_size
            )
        }

    }
}

@Composable
fun ErrorRetryStateScreen(text:String,onRetryClicked:() -> Unit){
    Box(modifier = Modifier.fillMaxSize().testTagged(ERROR_RETRY_TEST_TAG), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(mediumPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            RegularText(
                text = text,
                color = MaterialTheme.colors.onBackground,
                size = large_bold_text_size
            )

            RoundedOutlinedButton(text = stringResource(id = R.string.retry)) {
                    onRetryClicked()
            }
        }

    }
}

@Stable
fun Modifier.testTagged(tag: String) = semantics(
    properties = {
        testTag = tag
        contentDescription = tag
    }
)

