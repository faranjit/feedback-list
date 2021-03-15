package com.faranjit.feedbacklist.network

import android.content.Context
import com.faranjit.feedbacklist.BuildConfig
import okhttp3.*

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
object MockServer {

    fun createMockServer(context: Context) = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            if (BuildConfig.DEBUG) {
                val responseString = readDummyJson(context)

                return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .body(
                        ResponseBody.create(
                            MediaType.parse("application/json"),
                            responseString.toByteArray()
                        )
                    )
                    .addHeader("content-type", "application/json")
                    .build()
            } else {
                //just to be on safe side.
                throw IllegalAccessError(
                    "MockInterceptor is only meant for Testing Purposes and " +
                            "bound to be used only with DEBUG mode"
                )
            }
        }
    }

    private fun readDummyJson(context: Context) =
        context.assets.open("apidemo.json").bufferedReader().use { it.readText() }
}