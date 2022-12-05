package org.compose.news.data.usecases

import org.compose.news.data.repositories.StateFlowResult

abstract class BaseUseCase<in Input,out T>(){
    abstract suspend fun dispatch(input: Input):StateFlowResult<T>
}
