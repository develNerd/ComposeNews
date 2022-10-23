package org.paypay.fidonews.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Source(val name: String = "",
                  val id: String = "")