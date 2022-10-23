package org.paypay.fidonews.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResult(val totalResults: Int = 0,
                         val articles: List<ArticlesItem>?,
                         val status: String = "")