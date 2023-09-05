package ru.acts.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.acts.webview.network.SSEClient
import ru.acts.webview.notification.NotificationHelper
import ru.acts.webview.screens.MyWebView
import ru.acts.webview.screens.WelcomeScreen


class MainActivity : ComponentActivity() {

    private var selectedCity by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
         //   WelcomeScreen(onConfirm = ::onConfirm, onCancel = ::onCancel)
                    // MyWebView("https://lc.itprorab-test.ru/") //https://google.com
            App()
         //   val notificationHelper = NotificationHelper(this)
         //   SSEClient("https://hub.itprorab-test.ru/sse.php").startListening(notificationHelper)
        }
    }

//    private fun onConfirm(city: String) {
//        selectedCity = city
//
//    }

//    private fun onCancel() {
//
//        // Выполнение другого действия
//        finish()
//    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }
        composable("webview/{data}") {
            it.arguments?.getString("data")?.let { city ->
                MyWebView(urlScheme = "https", urlAddress = "limecity.ru", city = city)
            }
        }
    }
}


//@SuppressLint("SetJavaScriptEnabled")
//@Composable
//fun MyWebView(url: String) {
//    AndroidView(
//        modifier = Modifier.fillMaxSize(),
//        factory = { context ->
//            WebView(context).apply {
//                settings.javaScriptEnabled = true
//                webViewClient = WebViewClient()
//                loadUrl(url)
//            }
//        }
//    )
//}
