package org.halulkin.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.halulkin.BuildConfig
import org.halulkin.core.utils.Constants

object KtorHttpClient {

    fun httpClient() = HttpClient {
        expectSuccess = true
        addDefaultResponseValidation()

        defaultRequest {
            url {
                protocol = URLProtocol.Companion.HTTPS
                host = Constants.BASE_URL
                path(Constants.URL_PATH)
                parameters.append("api_key", BuildConfig.API_KEY)
            }
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("Http: $message")
                }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
}
