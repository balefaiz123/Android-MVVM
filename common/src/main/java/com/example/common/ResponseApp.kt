package com.example.common.entity

import okhttp3.ResponseBody

sealed class ResponseApp<T>(
    val data: T?, val error: Exception?, val code: Int?, val errorBody: ResponseBody?) {
    companion object {
        fun <T> success(t : T) :ResponseApp<T> = ResponseSuccess(t)
        fun <T> errorSystem(exc:Exception):ResponseApp<T> =
            ResponseError(exc,ResponseError.ERROR_SYSTEM, null)
        fun <T> errorBackend(statusCode:Int, body: ResponseBody?) :ResponseApp<T> =
            ResponseError(null, statusCode, body)
        fun <T> loading():ResponseApp<T> = ResponseLoading()

    }
}
class ResponseSuccess<T>(data : T) : ResponseApp<T>(data,null,null,null)
class ResponseError<T>(exc: Exception?, code:Int, responseBody: ResponseBody?) :ResponseApp<T>(
    null, exc, code, responseBody
){
    companion object{
        const val ERROR_SYSTEM = -1
        const val ERROR_BACKEND = -2
    }
}
class ResponseLoading<T> : ResponseApp<T>(null, null, null, null)