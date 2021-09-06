package com.selim.expensetracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.models.Transactions

class TransactionAdapter(val transactionsList:ArrayList<Transactions>): RecyclerView.Adapter<TransactionAdapter.TransactionVH>() {

    class TransactionVH(itemView: View):RecyclerView.ViewHolder(itemView){
        private val trTitle = itemView.findViewById<TextView>(R.id.trTitle)
        private val trDescription = itemView.findViewById<TextView>(R.id.trDescription)
        private val trHistory= itemView.findViewById<TextView>(R.id.trHistory)
        private val trExpense=itemView.findViewById<TextView>(R.id.trExpense)
        fun bind(transaction: Transactions)
        {
            trTitle.text=transaction.title
            trDescription.text=transaction.description
            trHistory.text=transaction.history
            trExpense.text=transaction.expense
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionVH {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.transaction_item_row,parent,false)
        return TransactionVH(itemView)
    }

    override fun onBindViewHolder(holder: TransactionVH, position: Int) {
            holder.bind(transactionsList[position])
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }
}