package com.lvj.utilsdemo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.lvj.utilsdemo.util.logi
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webView.run {
            settings.javaScriptEnabled = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    val uri = request.url
                    if (uri != null) {
                        if ("xiaomai" == uri.scheme) {
                            //自定义scheme
                            logi("自定义scheme url = ${uri.path}")
                            return true
                        } else {
                            val path = uri.host
                            if (path != null && path.contains("xiaomaigongkao.com")) {
                                //本Web
                                logi("Web url = $path")
                                return false
                            } else {
                                //系统
                                logi("系统 url = $path")
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                startActivity(intent)
                                return true
                            }
                        }
                    } else {
                        return false
                    }
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    logi("onPageStarted")
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    logi("onPageFinished")
                    super.onPageFinished(view, url)
                }

            }

            setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
                logi("download url = $url")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
//            val url = "http://test.xiaomaigongkao.com/Course/Index/article_html/id/2526"
//            val url = "https://www.xiaomaigongkao.com/Portal/major/index.html"
            val url = "https://www.xiaomaigongkao.com/index.php?s=/Portal/job/index&uid=914272"
//            val url = "http://test.xiaomaigongkao.com/Course/Index/article_html/id/2371"
//            val url = "https://test.xiaomaigongkao.com/Course/Index/course_html/course_id/563"
//            val url = "https://test.xiaomaigongkao.com/Course/Index/course_html/course_id/504"
            loadUrl(url)
        }


    }
}