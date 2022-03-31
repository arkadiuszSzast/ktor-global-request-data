package com.szastarek.ktor.globalrequestdata

import kotlinx.coroutines.currentCoroutineContext

suspend fun requestData() = currentCoroutineContext()[RequestDataContext]?.applicationCall