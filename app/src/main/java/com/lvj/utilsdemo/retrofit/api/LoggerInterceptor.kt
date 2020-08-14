package com.lvj.utilsdemo.retrofit.api

import com.lvj.utilsdemo.util.loge
import com.lvj.utilsdemo.util.logi
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer

/**
 * okhttp log日志
 */
class LoggerInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        logRequest(request)

        val response = chain.proceed(request)

        val build = response.newBuilder().build()
        val body = build.body?.string()
        logResponse(response, body)
        return response.newBuilder().body(body?.toResponseBody("UTF-8".toMediaTypeOrNull())).build()
    }

    /**
     * 打印返回参数
     */
    private fun logResponse(response: Response, body: String?) {
        try {
            val build = response.newBuilder().build()
            val url = build.request.url.toString()
            val headers = build.request.headers.toString()
            val requestBody = bodyToStr(build.request)

            val logStr = StringBuilder()
                .append("============= response log time ${System.currentTimeMillis()} ===============")
                .append("\n\t request url = $url")
                .append("\n\t request headers = $headers")
                .append("\n\t request body = $requestBody")
                .append("\n\t response body = ${decode(body)}")
            logi(logStr.toString(), "request")
        } catch (e: Exception) {
            loge("返回日志打印异常 ${e.localizedMessage}", "request")
        }

    }

    /**
     * 打印请求参数 头 + 请求体
     */
    private fun logRequest(request: Request) {
        try {
            val url = request.url.toString()
            val headers = request.headers.toString()
            val bodyStr = bodyToStr(request)
            val logStr = StringBuilder()
                .append("============= request log  time ${System.currentTimeMillis()}===============")
                .append("\n\t request url = $url")
                .append("\n\t request headers = $headers")
                .append("\n\t request body = $bodyStr")
            logi(logStr.toString(), "request")
        } catch (e: Exception) {
            logi("请求日志打印异常 ${e.localizedMessage}", "request")
        }
    }

    /**
     * 请求体
     */
    private fun bodyToStr(request: Request): String {
        return try {
            val build = request.newBuilder().build()
            val buffer = Buffer()
            build.body?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: Exception) {
            "request body show error"
        }
    }

    /**
     * unicode转中文
     */
    fun decode(unicodeStr: String?): String? {
        if (unicodeStr.isNullOrEmpty()) {
            return ""
        }
        val retBuf = StringBuffer()
        val maxLoop = unicodeStr.length
        var i = 0
        while (i < maxLoop) {
            if (unicodeStr[i] == '\\') {
                if (i < maxLoop - 5 && (unicodeStr[i + 1] == 'u' || unicodeStr[i + 1] == 'U')) try {
                    retBuf.append(unicodeStr.substring(i + 2, i + 6).toInt(16).toChar())
                    i += 5
                } catch (localNumberFormatException: NumberFormatException) {
                    retBuf.append(unicodeStr[i])
                } else retBuf.append(unicodeStr[i])
            } else {
                retBuf.append(unicodeStr[i])
            }
            i++
        }
        return retBuf.toString()
    }

}