package org.compose.news.android

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import coil.compose.AsyncImage
import org.compose.news.android.databinding.ActivityMainBinding
import org.compose.news.android.dimes.*
import org.compose.news.android.presentation.viewcomponets.MediumTextBold
import org.compose.news.android.presentation.viewcomponets.RegularText
import org.compose.news.data.model.ArticlesItem


class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        


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
                model = articlesItem.urlToImage,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(articleImageHeight)
                    .clip(RoundedCornerShape(size10dp)),
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
