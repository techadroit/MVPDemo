package com.test.mvpdemo.ui.base

/**
 * Type heirarchy for the events in fetching data from the server
 */
sealed class Response {
    data class OnLoading(var showLoading : Boolean) : Response()
    data class SuccessResponse(var s : Any):Response()
    data class ErrorResponse(var s : String?) : Response()
}