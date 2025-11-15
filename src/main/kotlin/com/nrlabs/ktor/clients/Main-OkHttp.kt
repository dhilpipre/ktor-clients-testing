package com.nrlabs.ktor.clients

import com.newrelic.api.agent.Trace
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.delay

suspend fun main() {
    val client = OkHttpClient()
    client.iterations(200)

}

class OkHttpClient {

    suspend fun iterations(n: Int) {
        for (i in 1..n) {
            println("iteration: $i of $n")
            process()
            delay(5000)
        }
    }

    suspend fun process() {
        val response = getResponse();
        println(response)
    }

    @Trace(dispatcher = true)
    suspend fun getResponse(): String {
        val client = HttpClient(OkHttp)
        val response: HttpResponse = client.get("http://localhost:8090/RandomlySlow/")
        client.close()
        println("status code: " + response.status)
        val body = response.bodyAsText()
        val begin = body.indexOf("<h1>")
        val end = body.indexOf("</h1>")

        return body.substring(begin + 4, end)
    }
}