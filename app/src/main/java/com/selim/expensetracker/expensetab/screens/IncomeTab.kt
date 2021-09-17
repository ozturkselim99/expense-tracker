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

class IncomeTab : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_income_tab, container, false)


        val layoutManager = LinearLayoutManager(requireContext())
        view.findViewById<RecyclerView>(R.id.financialReportIncomeRW).layoutManager = layoutManager

        val adapter = TransactionAdapter(MockData.getTransactions())
        view.findViewById<RecyclerView>(R.id.financialReportIncomeRW).adapter = adapter

        return view
    }


}