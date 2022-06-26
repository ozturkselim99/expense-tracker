package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.CreateBudgetActivity
import com.selim.expensetracker.adapters.BudgetAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.FragmentBudgetBinding
import java.util.*


class BudgetFragment : Fragment() {

    private var cal: Calendar? = null
    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cal = Calendar.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)

        setupRecyclerview()
        currentMonth()
        initViews()

        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.violet_100)

        return binding.root
    }

    private fun initViews() {
        binding.nextMonth.setOnClickListener {
            nextMonth()
        }
        binding.prevMonth.setOnClickListener {
            previousMonth()
        }
        binding.createBudget.setOnClickListener {
            val intent = Intent(requireContext(), CreateBudgetActivity::class.java)
            startActivity(intent)
        }
    }

    private fun previousMonth() {
        cal?.add(Calendar.MONTH, -1)
        binding.budgetMonth.text = cal?.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)
    }

    private fun nextMonth() {
        cal?.add(Calendar.MONTH, 1)
        binding.budgetMonth.text = cal?.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)
    }

    private fun currentMonth() {
        binding.budgetMonth.text = cal?.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)
    }

    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.budgetsRW.layoutManager = layoutManager

        val adapter = BudgetAdapter(MockData.getBudgets())
        binding.budgetsRW.adapter = adapter
    }

}