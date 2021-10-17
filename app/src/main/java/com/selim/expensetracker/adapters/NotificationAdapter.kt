package com.selim.expensetracker.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.models.Notification

class NotificationAdapter(val notifactionList: ArrayList<Notification>) : RecyclerView.Adapter<NotificationAdapter.NotificationVH>() {

    class NotificationVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val notificationText = itemView.findViewById<TextView>(R.id.notification_text)
        private val notificationTime = itemView.findViewById<TextView>(R.id.notification_time)
        private val notificationItem = itemView.findViewById<ConstraintLayout>(R.id.notification_item)


        fun bind(notification: Notification) {
            notificationText.text = notification.notificationText
            notificationTime.text=notification.notificationTime

            notificationItem.setOnClickListener {
                /*
                val intent = Intent(itemView.context, BudgetDetailActivity::class.java)
                itemView.context.startActivity(intent)
                 */
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notification_item_row, parent, false)
        return NotificationVH(itemView)
    }

    override fun onBindViewHolder(holder: NotificationVH, position: Int) {
        holder.bind(notifactionList[position])
    }

    override fun getItemCount(): Int {
        return notifactionList.size
    }
}