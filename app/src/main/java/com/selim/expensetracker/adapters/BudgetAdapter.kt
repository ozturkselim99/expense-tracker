package com.selim.expensetracker.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.BudgetDetailActivity
import com.selim.expensetracker.activities.TransferDetailActivity
import com.selim.expensetracker.models.Budget
import com.selim.expensetracker.models.Transactions

class BudgetAdapter(val budgetList: ArrayList<Budget>) : RecyclerView.Adapter<BudgetAdapter.BudgetVH>() {

    class BudgetVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val budgetCategory = itemView.findViewById<TextView>(R.id.budgetCategory)
        private val budgetLimit = itemView.findViewById<TextView>(R.id.budgetLimit)
        private val budgetItemRow = itemView.findViewById<ConstraintLayout>(R.id.budgetItemRow)


        fun bind(budget: Budget) {
            budgetCategory.text = budget.budgetCategory
          budgetLimit.text=budget.budgetLimit

            budgetItemRow.setOnClickListener {
                val intent = Intent(itemView.context, BudgetDetailActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.budget_item_row, parent, false)
        return BudgetVH(itemView)
    }

    override fun onBindViewHolder(holder: BudgetVH, position: Int) {
        holder.bind(budgetList[position])
    }

    override fun getItemCount(): Int {
        return budgetList.size
    }
}