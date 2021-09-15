package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.FinancialReportActivity
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.models.Transactions

class TransactionsFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_transactions, container, false)

        val recyclerViewTransaction = view.findViewById<RecyclerView>(R.id.transactions)
        val filterTransaction = view.findViewById<CardView>(R.id.filterTransaction)
        val seeFinancialReport = view.findViewById<ConstraintLayout>(R.id.seeFinancialReport)

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val filterBottomModal = layoutInflater.inflate(R.layout.filter_bottom_sheet_modal, null)
        bottomSheetDialog.setContentView(filterBottomModal)

        filterTransaction.setOnClickListener {
            bottomSheetDialog.show()
        }
        filterBottomModal.findViewById<Button>(R.id.filterTransactionApply).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        seeFinancialReport.setOnClickListener {
            val intent = Intent(requireContext(), FinancialReportActivity::class.java)
            startActivity(intent)
        }

        val spinnerMonths = view.findViewById<Spinner>(R.id.spinnerMonthsTransactions)

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, MockData.getMonths())

        spinnerMonths.adapter = spinnerAdapter

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerViewTransaction.layoutManager = layoutManager

        val adapter = TransactionAdapter(MockData.getTransactions())
        recyclerViewTransaction.adapter = adapter


        return view
    }


}