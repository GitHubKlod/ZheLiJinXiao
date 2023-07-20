package com.klod.base.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.klod.base.BaseViewModel
import com.klod.base.activity.BaseVmActivity
import com.klod.base.ext.util.logE
import com.klod.base.fragment.BaseVmFragment
import com.klod.base.network.AppException
import com.klod.base.network.BaseResponse
import com.klod.base.network.ExceptionHandle
import kotlinx.coroutines.*
import com.klod.base.state.ResultState
import com.klod.base.state.paresException
import com.klod.base.state.paresResult
import retrofit2.HttpException


//1.这种是在 Activity/Fragment的监听回调中拿到已脱壳的数据（项目有基类的可以用）
//request(
//{ apiService.login(username, password) }//请求体
//, loginResult,//请求的返回结果，请求成功与否都会改变该值，在Activity或fragment中监听回调结果，具体可看loginActivity中的回调
//true,//是否显示等待框，，默认false不显示 可以默认不传
//"正在登录中..."//等待框内容，可以默认不填请求网络中...
//)
//2.这种是在Activity/Fragment中的监听拿到未脱壳的数据，你可以自己根据code做业务需求操作（项目没有基类的可以用）
/*requestNoCheck({HttpRequestCoroutine.login(username,password)},loginResult2,true)*/

//3. 这种是直接在当前ViewModel中就拿到了脱壳数据数据，做一层封装再给Activity/Fragment，如果 （项目有基类的可以用）
/*request({apiService.login(username, password)},{
    //请求成功 已自动处理了 请求结果是否正常
},{
    //请求失败 网络异常，或者请求结果码错误都会回调在这里
})*/

//4.这种是直接在当前ViewModel中就拿到了未脱壳数据数据，（项目没有基类的可以用）
/*requestNoCheck({HttpRequestCoroutine.login(username,password)},{
    //请求成功 自己拿到数据做业务需求操作
    if(it.errorCode==0){
        //结果正确
    }else{
        //结果错误
    }
},{
    //请求失败 网络异常回调在这里
})*/

/**
 * 作者　: KLOD
 * 时间　: 2020/4/8
 * 描述　:BaseViewModel请求协程封装
 */

/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultState 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 *
 */
fun <T> BaseVmActivity<*>.parseState(
    resultState: ResultState<T>,
    onSuccess: (T) -> Unit,
    onError: ((AppException) -> Unit)? = null,
    onLoading: (() -> Unit)? = null
) {
    when (resultState) {
        is ResultState.Loading -> {
            showLoading(resultState.loadingMessage)
            onLoading?.run { this }
        }
        is ResultState.Success -> {
            dismissLoading()
            onSuccess(resultState.data)
        }
        is ResultState.Error -> {
            dismissLoading()
            onError?.run { this(resultState.error) }
        }
    }
}

/**
 * 显示页面状态，这里有个技巧，成功回调在第一个，其后两个带默认值的回调可省
 * @param resultState 接口返回值
 * @param onLoading 加载中
 * @param onSuccess 成功回调
 * @param onError 失败回调
 *
 */
fun <T> BaseVmFragment<*>.parseState(
    resultState: ResultState<T>,
    onSuccess: (T) -> Unit,
    onError: ((AppException) -> Unit)? = null,
    onLoading: ((message:String) -> Unit)? = null
) {
    when (resultState) {
        is ResultState.Loading -> {
            if(onLoading==null){
                showLoading(resultState.loadingMessage)
            }else{
                onLoading.invoke(resultState.loadingMessage)
            }
        }
        is ResultState.Success -> {
            dismissLoading()
            onSuccess(resultState.data)
        }
        is ResultState.Error -> {
            dismissLoading()
            onError?.run { this(resultState.error) }
        }
    }
}


/**
 * net request 不校验请求结果数据是否是成功
 * @param block 请求体方法
 * @param resultState 请求回调的ResultState数据
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    resultState: MutableLiveData<ResultState<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = "请稍等..."
): Job {
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) resultState.value = ResultState.onAppLoading(loadingMessage)
            //请求体
            block()

        }.onSuccess {
            resultState.paresResult(it)
        }.onFailure {
            "RequestError = ${it.message}".logE()
            //打印错误栈信息
            it.printStackTrace()
            resultState.paresException(it)
        }
    }
}

/**
 * net request 不校验请求结果数据是否是成功
 * @param block 请求体方法
 * @param resultState 请求回调的ResultState数据
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    resultState: MutableLiveData<ResultState<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = "请稍等..."
): Job {
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) resultState.value = ResultState.onAppLoading(loadingMessage)
            //请求体
            block()
        }.onSuccess {
//            "设置用户信息接口请求到了".logE()
            resultState.paresResult(it)
        }.onFailure {
            "requestNoCheck Error = ${it.message}".logE()
            //打印错误栈信息
            it.printStackTrace()

            resultState.paresException(it)
        }
    }
}

/**
 * 过滤服务器结果，失败抛异常
 * @param block 请求体方法，必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不传
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请稍等..."
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it) { t -> success(t)
                }
            }.onFailure { e ->
                //打印错误消息
                e.message?.logE()
                //打印错误栈信息
                e.printStackTrace()
                //失败回调
                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            //网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            //打印错误消息
            it.message?.logE()
            //打印错误栈信息
            it.printStackTrace()
            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 *  不过滤请求结果
 * @param block 请求体 必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不给
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请稍等..."
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
    return viewModelScope.launch {
        runCatching {
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            //成功回调
            success(it)
        }.onFailure {
            //网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            //打印错误消息
            it.message?.logE()
            //打印错误栈信息
            it.printStackTrace()
            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 */
suspend fun <T> executeResponse(
    response: BaseResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        when {
            response.isSuccess() -> {
                success(response.getResponseData())
            }
            else -> {
                throw AppException(
                    response.getResponseCode(),
                    response.getResponseMsg(),
                    response.getResponseMsg()
                )
            }
        }
    }
}

/**
 *  调用携程
 * @param block 操作耗时操作任务
 * @param success 成功回调
 * @param error 失败回调 可不给
 */
fun <T> BaseViewModel.launch(
    block: () -> T,
    success: (T) -> Unit,
    error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            success(it)
        }.onFailure {
            error(it)
        }
    }
}
