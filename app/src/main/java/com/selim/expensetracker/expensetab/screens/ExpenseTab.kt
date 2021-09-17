package com.selim.expensetracker.expensetab.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData

class ExpenseTab : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_expense_tab, container, false)

        val financialReportExpenseRW = view.findViewById<RecyclerView>(R.id.financialReportExpenseRW)

        val layoutManager = LinearLayoutManager(requireContext())
        financialReportExpenseRW.layoutManager = layoutManager

        val adapter = TransactionAdapter(MockData.getTransactions())
        financialReportExpenseRW.adapter = adapter

        return  view
    }

}