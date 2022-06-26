package com.selim.expensetracker.financial.screens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.FinancialReportDetailActivity
import com.selim.expensetracker.activities.IncomeActivity

class FinancialQuote : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_financial_quote, container, false)

        view.findViewById<Button>(R.id.financialReportDetail).setOnClickListener {
            val intent = Intent(requireContext(), FinancialReportDetailActivity::class.java)
            startActivity(intent)
        }

        return view

    }


}