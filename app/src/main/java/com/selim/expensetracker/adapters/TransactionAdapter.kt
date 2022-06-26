package com.selim.expensetracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.databinding.TransactionItemRowBinding
import com.selim.expensetracker.models.Transactions

class TransactionAdapter(var onItemClicked: ((Transactions) -> Unit) = {}) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    var items: List<Transactions> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class TransactionViewHolder(private val binding: TransactionItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transactions) {
            binding.transactionItem = transaction
            binding.transactionItemRow.setOnClickListener {
                onItemClicked.invoke(transaction)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            TransactionItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}