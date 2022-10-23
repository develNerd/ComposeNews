package org.paypay.fidonews.data.network

import co.touchlab.kermit.Logger
import io.ktor.client.plugins.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.MutableStateFlow

suspend fun <T : Any> makeRequestToApi(
    call: suspend () -> T,
): ApiResult<T> {
    return try {
        ApiResult.Success(call())
    } catch (throwable: Exception) {
        throwable.printStackTrace()
        when (throwable) {
            is ServerResponseException -> {
                ApiResult.GenericError(throwable)
            }
            is ClientRequestException ->{
                ApiResult.GenericError(throwable)
            }
            is IOException -> {
                Logger.v("Error : $throwable")
                ApiResult.NoInternet
            }
            else -> ApiResult.GenericError(throwable)
        }
    }
}

sealed class ApiResult<out T : Any> {

    data class Success<out T : Any>(val response: T) : ApiResult<T>()


    data class GenericError(val error: Exception) : ApiResult<Nothing>()

    data class HttpError(val error: ClientRequestException) : ApiResult<Nothing>()

    object InProgress : ApiResult<Nothing>()

    object NoInternet : ApiResult<Nothing>()

    // Override for quick logging
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success [data=$response]"
            is HttpError -> "Http Error [httpCode=${error.message}]"
            is GenericError -> "Error [error=${error.message}]"
            is NoInternet -> "No Internet"
            is InProgress -> "In progress"
        }
    }


    fun <T> toOnResultObtained(): MutableStateFlow<OnResultObtained<T>> {
        return when (this) {
            is HttpError -> MutableStateFlow(OnResultObtained(result = null,true,"Something Went Wrong"))
            is GenericError -> MutableStateFlow(OnResultObtained(result = null,true,"Something Went Wrong"))
            is NoInternet -> MutableStateFlow(OnResultObtained(result = null,true,"We are finding it difficult to connect, Please Try Again"))
            is InProgress -> MutableStateFlow(OnResultObtained(result = null,false,null))
            else -> MutableStateFlow(OnResultObtained(result = null,false,null))
        }
    }
}

class OnResultObtained<T>(val result:T?,val isLoaded:Boolean,error:String? = null)
