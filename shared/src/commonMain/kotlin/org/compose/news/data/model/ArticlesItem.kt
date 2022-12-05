package org.compose.news.data.model
import kotlinx.datetime.*
import kotlinx.serialization.Serializable

const val TODAY = "Today"
const val YESTERDAY = "Yesterday"

@Serializable
data class ArticlesItem(val publishedAt: String? = null,
                        val author: String? = null,
                        val urlToImage: String? = null,
                        val description: String? = null,
                        val source: Source? = null,
                        val title: String? = null,
                        val url: String? = null,
                        val content: String? = null)
{

    fun formatToArticleDate():String {
        val date = publishedAt?.replace("Z","")?.toLocalDateTime()
        date ?: return ""
        val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        return when (date.dayOfMonth) {
            today.dayOfMonth -> {
                TODAY
            }
            today.dayOfMonth - 1 -> {
                YESTERDAY
            }
            else -> {
                "${date.year} - ${date.monthNumber + 1} - ${date.dayOfMonth}"
            }
        }
    }

}