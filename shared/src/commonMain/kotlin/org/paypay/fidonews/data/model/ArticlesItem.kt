package org.paypay.fidonews.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesItem(val publishedAt: String = "",
                        val author: String = "",
                        val urlToImage: String = "",
                        val description: String = "",
                        val source: Source,
                        val title: String = "",
                        val url: String = "",
                        val content: String = "")