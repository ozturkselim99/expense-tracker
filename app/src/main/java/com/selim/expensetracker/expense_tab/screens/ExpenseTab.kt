package com.selim.expensetracker.expense_tab.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.FragmentExpenseTabBinding
import com.selim.expensetracker.models.Transactions

class ExpenseTab : Fragment() {

    private var _binding: FragmentExpenseTabBinding? = null
    private val binding get() = _binding!!
    private val adapter = TransactionAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenseTabBinding.inflate(inflater, container, false)

        val temp= emptyList<Transactions>()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.financialReportExpenseRW.layoutManager = layoutManager
        binding.financialReportExpenseRW.adapter = adapter
        adapter.items=temp

        return  binding.root
    }

}