package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.FinancialReportActivity
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.FragmentTransactionsBinding
import com.selim.expensetracker.models.Transactions

class TransactionsFragment : Fragment() {

    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!
    private val adapter = TransactionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)

        val bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.FilterBottomSheetDialogTheme)
        val filterBottomModal = layoutInflater.inflate(R.layout.filter_bottom_sheet_modal, null)
        bottomSheetDialog.setContentView(filterBottomModal)

        //TODO:yine aynı viewbinding muhabbeti
        binding.filterTransaction.setOnClickListener {
            bottomSheetDialog.show()
        }
        filterBottomModal.findViewById<Button>(R.id.filterTransactionApply).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        binding.seeFinancialReport.setOnClickListener {
            val intent = Intent(requireContext(), FinancialReportActivity::class.java)
            startActivity(intent)
        }
        setupRecyclerview()
        setupSpinner()

        return binding.root
    }

    private fun setupRecyclerview() {
        val temp= emptyList<Transactions>()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.transactions.layoutManager = layoutManager
        binding.transactions.adapter = adapter
        adapter.items=temp
    }

    private fun setupSpinner() {
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getMonths()
        )
        binding.datesFilterSpinner.setAdapter(spinnerAdapter)
    }

}