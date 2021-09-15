package com.selim.expensetracker.financial.screens

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R


class ExpenseReport : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_expense_report, container, false)

        return view
    }

    override fun onResume() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.red)
        super.onResume()
    }

}