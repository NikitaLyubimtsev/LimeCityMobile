package ru.acts.webview.screens

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MyWebView(urlScheme: String, urlAddress: String, city: String) {

    val url = "$urlScheme://$city.$urlAddress"
    Log.d("My--", url)

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }
    )
}