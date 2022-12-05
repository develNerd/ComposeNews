package org.compose.news.android.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.compose.news.android.R
import org.compose.news.android.dimes.*
import org.compose.news.android.presentation.viewcomponets.TextHeader

@Composable
fun ColumnInset(modifier: Modifier = Modifier, verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(
    spacing10dp), content:@Composable () -> Unit){
    Column(modifier.fillMaxSize().padding(start = windowHorizontalInset, end = windowHorizontalInset, top = spacing10dp), verticalArrangement = verticalArrangement) {
        content()
    }
}

@Composable
fun HorizontalCustomTopAppBar(
    title:String = "",
    backgroundColor: Color = Color.Transparent,
    contentTint: Color = MaterialTheme.colors.onBackground,
    modifier: Modifier = Modifier,
    onNavBack: () -> Unit,
) {
    Box(modifier = modifier
        .padding(top = topFullScreenInset, start = spacing10dp, end = spacing10dp)
        .fillMaxWidth()
        .background(backgroundColor)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(modifier = Modifier.align(Alignment.CenterVertically),onClick = { onNavBack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_nav_back),
                    contentDescription = stringResource(id = R.string.app_name),
                    Modifier.size(size24),
                    tint = contentTint
                )
            }
            TextHeader(text = title, color = contentTint, modifier = Modifier
                .align(
                    Alignment.CenterVertically
                ))

        }

    }
}