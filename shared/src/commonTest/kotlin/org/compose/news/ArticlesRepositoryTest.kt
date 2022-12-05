package org.compose.news

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.compose.news.data.model.*
import org.compose.news.data.repositories.ArticlesRepository
import org.compose.news.data.usecasefactories.NewsUseCaseFactory
import org.compose.news.repositories.FakeArticlesRepository
import kotlin.test.*

class RepositoryTest {

    private lateinit var articlesRepository: ArticlesRepository
    private lateinit var useCaseFactory: NewsUseCaseFactory
    private val articles = mutableListOf(
        ArticlesItem("2022-12-05T08:01:47","Sophie Kiderlin","https://apnews.com/article",
            "Description 1",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1"),
        ArticlesItem("2010-06-01T22:19:44","Sophie Kiderlin","https://apnews.com/article",
            "Description 2",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1"),
        ArticlesItem("2010-06-01T22:19:44","Sophie Kiderlin","https://apnews.com/article",
            "Description 3",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1"),
        ArticlesItem("2010-06-01T22:19:44","Sophie Kiderlin","https://apnews.com/article",
            "Description 4",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1"),
        ArticlesItem("2010-06-01T22:19:44","Sophie Kiderlin","https://apnews.com/article",
            "Description 4",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1")
    )

    //2022-12-4T22:19:44
    //2010-06-01T22:19:44
    private lateinit var today:LocalDate


    private lateinit var articleResult: ArticleResult
    @BeforeTest
    fun createRepository() {
         today = Clock.System.todayIn(TimeZone.currentSystemDefault())

        val day = "${today.dayOfMonth}".padStart(2,'0')
        val publishedAtToday = "${today.year}-${today.monthNumber}-${day}T00:00:00"

       val todayITem =  ArticlesItem(publishedAtToday,"Sophie Kiderlin","https://apnews.com/article",
            "Description 4",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1")


        val yesterday = "${today.dayOfMonth - 1}".padStart(2,'0')
        val publishedAtYesterday = "${today.year}-${today.monthNumber}-${yesterday}T22:19:44"


        val yesterdayItem =  ArticlesItem(publishedAtYesterday,"Sophie Kiderlin","https://apnews.com/article",
            "Description 4",
            Source("CNBC","CNBC1"),"Title 1","https://apnews.com/article","Content 1")

        articles.addAll(listOf(todayITem,yesterdayItem))

        articleResult = ArticleResult(articles.size,articles,"success")
        articlesRepository = FakeArticlesRepository(articleResult)
        useCaseFactory = NewsUseCaseFactory(articlesRepository)
    }

    @Test
    fun getArticlesByCountry() = runTest {
        val result = useCaseFactory.getArticlesUseCase.dispatch("")
        result.test {
            assertEquals(articleResult,awaitItem().result)
        }
    }

    @Test
    fun testForArticleTodayDateFormat() = runTest {
        val result = useCaseFactory.getArticlesUseCase.dispatch("")
        result.test {
            val todayItem = awaitItem().result?.articles?.firstOrNull { it.formatToArticleDate() == TODAY }
            assertNotNull(todayItem)
        }
    }

    @Test
    fun testForArticleYesterdayDateFormat() = runTest {
        val result = useCaseFactory.getArticlesUseCase.dispatch("")
        result.test {
            val todayItem = awaitItem().result?.articles?.firstOrNull { it.formatToArticleDate() == YESTERDAY }
            assertNotNull(todayItem)
        }
    }





    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }
}