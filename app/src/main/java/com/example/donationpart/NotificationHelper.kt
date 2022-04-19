package com.example.donationpart

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationHelper(var co:Context,var msg:String) {
    private val CHANNEL_ID = "message id"
    private val NOTIFICATION_ID=123
    /**set Notification*/
    fun Notification(){
        createNotificationChannel()
        val senInt = Intent(co,Payment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingInt = PendingIntent.getActivities(co,0, arrayOf(senInt),0)
        /**set notification Dialog*/
        val icon = BitmapFactory.decodeResource(co.resources,R.drawable.receipt)
        val isnotification = NotificationCompat.Builder(co,CHANNEL_ID)
            .setSmallIcon(R.drawable.receipt)
            .setLargeIcon(icon)
            .setContentTitle("Bank Transaction")
            .setContentText(msg)
            .setContentIntent(pendingInt)
            .setStyle(NotificationCompat.BigTextStyle().bigText(msg))
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        NotificationManagerCompat.from(co)
            .notify(NOTIFICATION_ID,isnotification)
    }
    /**create createNotificationChannel*/
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val name = CHANNEL_ID
            val descrip = "Channel description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channels = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descrip
            }
            val notificationManger = co.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManger.createNotificationChannel(channels)
        }
    }
}