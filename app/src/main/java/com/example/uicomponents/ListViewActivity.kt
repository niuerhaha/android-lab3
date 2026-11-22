package com.example.uicomponents

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ListViewActivity : AppCompatActivity() {

    private val animals = listOf("Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant")
    private val icons = intArrayOf(
        R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
        R.drawable.dog, R.drawable.cat, R.drawable.elephant
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)
        createNotificationChannel()

        val data = animals.mapIndexed { index, name ->
            mapOf("icon" to icons[index], "name" to name)
        }

        val adapter = SimpleAdapter(
            this,
            data,
            R.layout.item_list,
            arrayOf("icon", "name"),
            intArrayOf(R.id.item_icon, R.id.item_name)
        )

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val name = animals[position]
            Toast.makeText(this, "You selected: $name", Toast.LENGTH_SHORT).show()
            sendNotification(name)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "列表通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(title: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                REQUEST_CODE
            )
            return
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle(title)
            .setContentText("You clicked list item: $title")
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).notify(1, notification)
    }

    companion object {
        private const val CHANNEL_ID = "list_channel"
        private const val REQUEST_CODE = 1
    }
}
