package ru.acts.webview.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import ru.acts.webview.notification.NotificationHelper
import java.util.concurrent.TimeUnit


class SSEClient(private var url: String) {

    private var client: OkHttpClient? = null
    private var eventSource: EventSource? = null

    private val tag = "My_SSE"

    fun startListening(notificationHelper: NotificationHelper) {
        val request = Request.Builder()
            .url(url)
            .build()

        Log.d(tag, "Request: $request")

        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(false)

        client = clientBuilder.build()
        Log.d(tag, "Client: $client")

        client?.let {
            eventSource = EventSources.createFactory(it).newEventSource(request, SSEListener(notificationHelper))
        } ?: throw Error("Ошибка склиента!")
    }

    inner class SSEListener(private val notificationHelper: NotificationHelper) : EventSourceListener() {

        override fun onOpen(eventSource: EventSource, response: Response) {
            Log.d("My_SSE", "Is open: $response")
            //super.onOpen(eventSource, response)
        }

        override fun onClosed(eventSource: EventSource) {
            Log.d("My_SSE", "Закрыто $eventSource")
        }

        override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
            //super.onEvent(eventSource, id, type, data)
            notificationHelper.showNotification("Message on LimeCity", data)
            Log.d("My_SSE", "Event: $type")
            Log.d("My_SSE", "Data: $data")
        }

        override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
            //super.onFailure(eventSource, t, response)
            Log.d("My_SSE", "event: $eventSource")
            Log.d("My_SSE", "Error: $t")
            Log.d("My_SSE", "Response: $response")
        }
    }

    fun stopListening() {
        eventSource?.let {
            it.cancel()
        }
    }
}




//    private lateinit var eventSource: EventSource
//
//    fun startListening() {
//        val client = OkHttpClient.Builder()
//            .readTimeout(0, TimeUnit.MILLISECONDS)
//            .retryOnConnectionFailure(false)
//            .build()
//
//        val request = Request.Builder()
//            .url(url)
//            .build()
//
//
//        val listener = object : EventSourceListener() {
//            override fun onEvent(
//                eventSource: EventSource,
//                id: String?,
//                event: String?,
//                data: String
//            ) {
//                Log.d("MY_SSE", event ?: "No event")
//                Log.d("MY_SSE", data)
//            }
//
//            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
//                Log.d("MY_SSE", t.toString())
//            }
//        }
//
//        eventSource = Factory(request, listener)
//    }
//
//    fun stopListening() {
//        eventSources.
//    }
//}