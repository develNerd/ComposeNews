package org.compose.news.data.model
import kotlinx.serialization.Serializable

@Serializable
data class Source(val name: String? = null,
                  val id: String? = null)