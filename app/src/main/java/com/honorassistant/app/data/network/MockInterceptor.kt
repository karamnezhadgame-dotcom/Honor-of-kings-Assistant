package com.honorassistant.app.data.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.Protocol

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.url.encodedPath == "/heroes") {
            val json = """
                [{"id":"1","name":"李白","title":"青莲剑仙","lore":"十步杀一人...","role":"刺客","difficulty":3,"splashArtUrl":"https://picsum.photos/seed/li_bai/600/800","skillsJson":"[]","skinsJson":"[]"}]
            """.trimIndent()
            return Response.Builder()
                .code(200).message("OK").protocol(Protocol.HTTP_1_1)
                .request(request)
                .body(json.toResponseBody(okhttp3.MediaType.parse("application/json")!!))
                .build()
        }
        return chain.proceed(request)
    }
}
