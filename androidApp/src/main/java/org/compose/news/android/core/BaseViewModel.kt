package org.compose.news.android.core

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okio.IOException
import org.compose.news.android.contants.ErrorMessageConstants.SOMETHING_WENT_WRONG
import org.compose.news.data.usecases.BaseUseCase

abstract class BaseViewModel : ViewModel() {

    data class UIResult<out T>(val result:T?,val isLoaded:Boolean,val error:String? = null)

    fun <Input,Output:Any> executeApiCallUseCase(
        viewModelScope: CoroutineScope,
        inputValue:Input,
        useCase: BaseUseCase<Input, Output>,
        callback: (UIResult<Output>) -> Unit = {}){
        viewModelScope.launch {
            useCase.dispatch(inputValue).collectLatest {result ->
                if(result.error != null){
                    Log.e("ERROR OCCURRED", result.error!!.localizedMessage ?: "")
                    val message = when(result.error){
                        is IOException -> result.error?.message
                        else -> SOMETHING_WENT_WRONG
                    }
                    callback(UIResult(result.result,result.isLoaded,message))
                }else {
                    callback(UIResult(result.result,result.isLoaded,null))
                }
            }
        }
    }

}