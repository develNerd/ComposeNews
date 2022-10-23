package org.fido.fidonews.android.presentation

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.fido.fidonews.android.R
import org.fido.fidonews.android.dimes.large_bold_text_size
import org.fido.fidonews.android.dimes.mediumPadding
import org.fido.fidonews.android.presentation.viewcomponets.GenericCircleProgressIndicator
import org.fido.fidonews.android.presentation.viewcomponets.RegularText
import org.fido.fidonews.android.presentation.viewcomponets.RoundedOutlinedButton

@Composable
fun <T> GenericListViewRenderer(list:List<T>? = null, loadComplete:Boolean, isError:Boolean = false, emptyStateValue:String = stringResource(id =  R.string.nothing_here), content: @Composable () -> Unit){
    Log.e("loadComplete", loadComplete.toString())
    Crossfade(targetState = list){ retrievedList ->
        when{
            isError -> {
                ErrorRetryStateScreen(text = stringResource(id = R.string.something_went_wrong)) {

                }
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
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(mediumPadding)) {

            RegularText(
                text = text,
                color = MaterialTheme.colors.onBackground,
                size = large_bold_text_size
            )

            RoundedOutlinedButton(text = stringResource(id = R.string.retry)) {

            }
        }

    }
}