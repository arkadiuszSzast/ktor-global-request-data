package com.szastarek.ktor.globalrequestdata

import io.ktor.application.Application
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.ApplicationFeature
import io.ktor.application.call
import io.ktor.util.AttributeKey
import io.ktor.util.pipeline.PipelinePhase
import kotlinx.coroutines.withContext

class GlobalRequestData {

    class Configuration

    companion object Feature : ApplicationFeature<Application, Configuration, GlobalRequestData> {
        override val key = AttributeKey<GlobalRequestData>("GlobalRequestData")
        private var config = Configuration()

        override fun install(pipeline: Application, configure: Configuration.() -> Unit): GlobalRequestData {
            config = Configuration().apply(configure)
            val feature = GlobalRequestData()

            val startingPhase = PipelinePhase("GlobalRequestDataInject")
            pipeline.insertPhaseAfter(ApplicationCallPipeline.Setup, startingPhase)

            pipeline.intercept(startingPhase) {
                withContext(coroutineContext + RequestDataContext(call)) {
                    proceed()
                }
            }
            return feature
        }
    }
}
