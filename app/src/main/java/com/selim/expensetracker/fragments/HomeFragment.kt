package com.selim.expensetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.selim.expensetracker.R
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.models.Transactions


class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerViewTransaction = view.findViewById<RecyclerView>(R.id.transactionRW)

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerViewTransaction.layoutManager = layoutManager

        val adapter = TransactionAdapter(MockData.getTransactions())
        recyclerViewTransaction.adapter = adapter

        val spinnerMonths = view.findViewById<Spinner>(R.id.spinnerMonths)

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, MockData.getMonths())

        spinnerMonths.adapter = spinnerAdapter

        var today = view.findViewById<TextView>(R.id.textView34)
        var week = view.findViewById<TextView>(R.id.textView33)
        var month = view.findViewById<TextView>(R.id.textView32)
        var year = view.findViewById<TextView>(R.id.textView31)

// TODO: 15.09.2021 change chip choice
        today.setOnClickListener {
            today.setBackgroundResource(R.drawable.custom_textview)
            today.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextTimeColor))
            week.background = null
            week.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
            month.background = null
            month.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
            year.background = null
            year.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
        }

        week.setOnClickListener {
            week.setBackgroundResource(R.drawable.custom_textview)
            week.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextTimeColor))
            today.background = null
            today.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
            month.background = null
            month.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
            year.background = null
            year.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
        }
        month.setOnClickListener {
            month.setBackgroundResource(R.drawable.custom_textview)
            month.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextTimeColor))
            today.background = null
            today.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
            week.background = null
            week.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
            year.background = null
            year.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
        }
        year.setOnClickListener {
            year.setBackgroundResource(R.drawable.custom_textview)
            year.setTextColor(ContextCompat.getColor(requireContext(), R.color.selectedTextTimeColor))
            today.background = null
            today.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
            month.background = null
            month.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
            week.background = null
            week.setTextColor(ContextCompat.getColor(requireContext(), R.color.textTimeColor))
        }

        return view
    }


}