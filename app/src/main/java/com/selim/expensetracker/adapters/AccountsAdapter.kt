package com.selim.expensetracker.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.AccountDetailActivity
import com.selim.expensetracker.models.Account

class AccountsAdapter(val accountsList: ArrayList<Account>) : RecyclerView.Adapter<AccountsAdapter.AccountsVH>() {

    class AccountsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val accountName = itemView.findViewById<TextView>(R.id.accountName)
        private val acccountBalance = itemView.findViewById<TextView>(R.id.acccountBalance)
        private val accountDetail = itemView.findViewById<LinearLayout>(R.id.account)


        fun bind(account: Account) {
            accountName.text = account.accountName
            acccountBalance.text=account.accountBalance
            accountDetail.setOnClickListener {
                val intent = Intent(itemView.context, AccountDetailActivity::class.java)
                itemView.context.startActivity(intent)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.account_item_row, parent, false)
        return AccountsVH(itemView)
    }

    override fun onBindViewHolder(holder: AccountsVH, position: Int) {
        holder.bind(accountsList[position])
    }

    override fun getItemCount(): Int {
        return accountsList.size
    }
}