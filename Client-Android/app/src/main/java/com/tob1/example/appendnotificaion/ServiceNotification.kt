package com.tob1.example.appendnotificaion

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class ServiceNotification : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val socket = SocketIo.getSocket()
        socket.connect()
        createChannelNotification()
        // TODO; add them notification
        socket.on("test1"){
            Log.d("check","socket is test: ${it[0]}")
            with(NotificationManagerCompat.from(this)){
                notify(1,createNotification(it[0].toString()).build())
            }
        }

        return START_STICKY
    }

    private fun createNotification(content: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, "test_tear")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentText(content)
            .setContentTitle(content + "1")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    private fun createChannelNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("test_tear","teart_test_notification",NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "lmao"
            }

            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }
}