package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.CreateBudgetActivity
import com.selim.expensetracker.adapters.BudgetAdapter
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData
import java.util.*


class BudgetFragment : Fragment() {
    private var cal: Calendar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cal= Calendar.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_budget, container, false)
        val recyclerViewBudgets = view.findViewById<RecyclerView>(R.id.budgetsRW)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerViewBudgets.layoutManager = layoutManager

        val adapter = BudgetAdapter(MockData.getBudgets())
        recyclerViewBudgets.adapter = adapter

        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.violet_100)
        currentMonth(view)

        view.findViewById<ImageView>(R.id.nextMonth).setOnClickListener {
            nextMonth(view)
        }
        view.findViewById<ImageView>(R.id.prevMonth).setOnClickListener {
            previousMonth(view)
        }
        view.findViewById<Button>(R.id.createBudget).setOnClickListener {
            val intent=Intent(requireContext(),CreateBudgetActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun previousMonth(view: View) {
        cal?.add(Calendar.MONTH,-1)
        view.findViewById<TextView>(R.id.budgetMonth).text=cal?.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)
    }

    private fun nextMonth(view: View) {
        cal?.add(Calendar.MONTH,1)
        view.findViewById<TextView>(R.id.budgetMonth).text=cal?.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)
    }
    private fun currentMonth(view: View)
    {
        view.findViewById<TextView>(R.id.budgetMonth).text=cal?.getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.ENGLISH)
    }



}