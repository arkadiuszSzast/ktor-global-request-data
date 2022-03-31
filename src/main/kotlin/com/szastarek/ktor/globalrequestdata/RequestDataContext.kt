package com.szastarek.ktor.globalrequestdata

import io.ktor.application.ApplicationCall
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

data class RequestDataContext(val applicationCall: ApplicationCall) :
    AbstractCoroutineContextElement(RequestDataContext) {

    companion object Key : CoroutineContext.Key<RequestDataContext>
}