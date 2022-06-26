package com.selim.expensetracker.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.expensetracker.R
import com.selim.expensetracker.adapters.NotificationAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.ActivityNotificationBinding
import com.selim.expensetracker.utils.showToastShort

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerview()
        initViews()
    }

    private fun initViews(){
        binding.notificationBackButton.setOnClickListener {
            showMenu(it,R.menu.notification_menu)
        }
        binding.notificationBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(this, v)
         popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.option_1 -> {
                    showToastShort(item.title.toString())
                }
                R.id.option_2 -> {
                    showToastShort(item.title.toString())
                }
            }
            true
        }

        popup.setOnDismissListener {
          showToastShort("Menu kapandi")
        }
        popup.show()
    }

    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(this)
        binding.notificationsRW.layoutManager = layoutManager

        val adapter = NotificationAdapter(MockData.getNotifications())
        binding.notificationsRW.adapter = adapter
    }
    /*
    private fun setListTypeIcon(imageView: ImageView) {
        val icon = when (viewModel.getListType()) {
            ListType.GridLayout -> R.drawable.grid_list
            else -> R.drawable.ic_list_icon
        }
        imageView.setImageResource(icon)

    }

     */
}

