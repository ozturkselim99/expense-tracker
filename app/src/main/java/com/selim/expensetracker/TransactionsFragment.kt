package com.selim.expensetracker

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.models.Transactions

class TransactionsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_transactions, container, false)
        val recyclerViewTransaction =  view.findViewById<RecyclerView>(R.id.transactions)

        val filterTransaction=view.findViewById<CardView>(R.id.filterTransaction)
        val bottomSheetDialog= BottomSheetDialog(requireContext())
        val filterBottomModal=layoutInflater.inflate(R.layout.filter_bottom_sheet_modal,null)
        bottomSheetDialog.setContentView(filterBottomModal)

        filterTransaction.setOnClickListener {
            bottomSheetDialog.show()
        }
      filterBottomModal.findViewById<Button>(R.id.filterTransactionApply).setOnClickListener {
          bottomSheetDialog.dismiss()
      }

        val months= arrayListOf<String>("January","February","March","April","May","June","July","August","September","October","November","December")
        val transactionList= arrayListOf<Transactions>(
                Transactions("Shopping","Buy some grocery","10:00 AM","-120$"),
                Transactions("Subscription","Disney+ Annual..","03:30 PM","-80\$"),
                Transactions("Food","Buy a ramen","07:30 PM","-30$"),
        )
        val spinnerMonths=view.findViewById<Spinner>(R.id.spinnerMonthsTransactions)

        val spinnerAdapter= ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item,months)

        spinnerMonths.adapter=spinnerAdapter

        val layoutManager= LinearLayoutManager(requireContext())
        recyclerViewTransaction.layoutManager=layoutManager

        val adapter= TransactionAdapter(transactionList)
        recyclerViewTransaction.adapter=adapter


        return view
    }


}