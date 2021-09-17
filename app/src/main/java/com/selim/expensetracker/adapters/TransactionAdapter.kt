package com.selim.expensetracker.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.ExpenseActivity
import com.selim.expensetracker.activities.ExpenseDetailActivity
import com.selim.expensetracker.activities.IncomeDetailActivity
import com.selim.expensetracker.activities.TransferDetailActivity
import com.selim.expensetracker.models.Transactions

class TransactionAdapter(val transactionsList: ArrayList<Transactions>) : RecyclerView.Adapter<TransactionAdapter.TransactionVH>() {

    class TransactionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val trTitle = itemView.findViewById<TextView>(R.id.trTitle)
        private val trDescription = itemView.findViewById<TextView>(R.id.trDescription)
        private val trHistory = itemView.findViewById<TextView>(R.id.trHistory)
        private val trExpense = itemView.findViewById<TextView>(R.id.trExpense)
        private val transactionItemRow = itemView.findViewById<ConstraintLayout>(R.id.transactionItemRow)

        fun bind(transaction: Transactions) {
            trTitle.text = transaction.title
            trDescription.text = transaction.description
            trHistory.text = transaction.history
            trExpense.text = transaction.expense
            transactionItemRow.setOnClickListener {
                val intent = Intent(itemView.context, TransferDetailActivity::class.java)
               itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item_row, parent, false)
        return TransactionVH(itemView)
    }

    override fun onBindViewHolder(holder: TransactionVH, position: Int) {
        holder.bind(transactionsList[position])
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }
}