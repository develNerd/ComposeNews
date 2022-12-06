package org.compose.news.android.presentation.viewcomponets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import org.compose.news.android.contants.TestDoubleTags.PROGRESS_TEST_TAG
import org.compose.news.android.dimes.size24
import org.compose.news.android.dimes.spacing16dp
import org.compose.news.android.presentation.testTagged

@Composable
fun GenericCircleProgressIndicator(modifier: Modifier = Modifier, color: Color = MaterialTheme.colors.primary){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.testTagged(PROGRESS_TEST_TAG)
            .fillMaxWidth()
            .background(Color.Transparent, shape = RoundedCornerShape(0))
            .padding(spacing16dp)
    ) {
        CircularProgressIndicator(modifier = Modifier.size(size24),)
    }
}