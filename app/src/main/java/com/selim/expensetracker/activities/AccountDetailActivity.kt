
package com.selim.expensetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData

class AccountDetailActivity : AppCompatActivity() {
    private val transactionsDetailAccount by lazy { findViewById<RecyclerView>(R.id.transactionsDetailAccount) }
    private val accountDetailBackButton by lazy { findViewById<ImageView>(R.id.accountDetailBackButton) }
    private val spinnerMonths by lazy { findViewById<AutoCompleteTextView>(R.id.datesFilterSpinner) }
    private val filterTransaction by lazy{findViewById<CardView>(R.id.filterTransaction)}
    private lateinit var bottomSheetDialog:BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_detail)

        bottomSheetDialog=BottomSheetDialog(this)

        setupBottomSheet()
        setupRecyclerview()
        setupSpinner()

        filterTransaction.setOnClickListener {
            bottomSheetDialog.show()
        }
        accountDetailBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(this)
        transactionsDetailAccount.layoutManager = layoutManager

        val adapter = TransactionAdapter(MockData.getTransactions())
        transactionsDetailAccount.adapter = adapter
    }

    private fun setupSpinner(){
        val spinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, MockData.getMonths())
        spinnerMonths.setAdapter(spinnerAdapter)
    }

    private fun setupBottomSheet(){
        val filterBottomModal = layoutInflater.inflate(R.layout.filter_bottom_sheet_modal, null)
        bottomSheetDialog.setContentView(filterBottomModal)
    }


}