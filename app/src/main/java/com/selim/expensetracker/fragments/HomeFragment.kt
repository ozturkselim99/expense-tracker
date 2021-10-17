package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.NotificationActivity
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

        val spinnerMonths = view.findViewById<AutoCompleteTextView>(R.id.datesFilterSpinner)

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, MockData.getMonths())

        spinnerMonths.setAdapter(spinnerAdapter)

        view.findViewById<ImageView>(R.id.notifications).setOnClickListener {
            val intent= Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
        }
        return view
    }


}