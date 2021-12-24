package com.selim.expensetracker.activities

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.adapters.AccountsAdapter
import com.selim.expensetracker.adapters.NotificationAdapter
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData

class NotificationActivity : AppCompatActivity() {
    private val notificationMenu by lazy { findViewById<ImageView>(R.id.notification_menu) }
    private val recyclerViewNotifications by lazy { findViewById<RecyclerView>(R.id.notificationsRW) }
    private val notificationBackButton by lazy { findViewById<ImageView>(R.id.notificationBackButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        setupRecyclerview()
        notificationMenu.setOnClickListener {
            showMenu(it,R.menu.notification_menu)
        }
        notificationBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(this, v)
         popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.option_1 -> {
                    Toast.makeText(v.context, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.option_2 -> {
                    Toast.makeText(v.context, item.title, Toast.LENGTH_SHORT).show()
                }
            }
            true
        })

        popup.setOnDismissListener {
           Toast.makeText(v.context,"Menu kapandi",Toast.LENGTH_SHORT).show()
        }
        popup.show()
    }
    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(this)
        recyclerViewNotifications.layoutManager = layoutManager

        val adapter = NotificationAdapter(MockData.getNotifications())
        recyclerViewNotifications.adapter = adapter
    }
}

