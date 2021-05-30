package com.raywenderlich.flatrock.util

import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

open class NetworkErrorHandler(private val uiComponent: UiErrorInterface) {

    fun handle(e: Throwable) {
        when (e) {
            is IOException -> {
                uiComponent.onNoInternet()
            }
            is SocketTimeoutException -> {
                uiComponent.onNoInternet()
            }
            else -> throw e
        }
    }



}

interface UiErrorInterface {

    fun onNoInternet()

    fun onServerError(message: String)


}

fun UiErrorInterface.handleNetworkError(e: Throwable) {
    NetworkErrorHandler(this).handle(e)
}