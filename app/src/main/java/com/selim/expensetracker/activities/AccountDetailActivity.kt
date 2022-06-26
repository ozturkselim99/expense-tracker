
package com.selim.expensetracker.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.core.Transaction
import com.selim.expensetracker.R
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.ActivityAccountDetailBinding
import com.selim.expensetracker.models.Transactions

class AccountDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountDetailBinding
    private lateinit var bottomSheetDialog:BottomSheetDialog
    private val adapter = TransactionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        bottomSheetDialog=BottomSheetDialog(this)

        setupBottomSheet()
        setupRecyclerview()
        setupSpinner()
        initViews()

        //TODO:burayada bak viewbinding için
        binding.filterTransaction.setOnClickListener {
            bottomSheetDialog.show()
        }

    }

    private fun initViews(){
        binding.accountDetailBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerview() {
        val temp= emptyList<Transactions>()
        val layoutManager = LinearLayoutManager(this)
        binding.transactionsDetailAccount.layoutManager = layoutManager
        binding.transactionsDetailAccount.adapter=adapter
        adapter.items=temp
    }

    private fun setupSpinner(){
        val spinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, MockData.getMonths())
        binding.datesFilterSpinner.setAdapter(spinnerAdapter)
    }

    private fun setupBottomSheet(){
        val filterBottomModal = layoutInflater.inflate(R.layout.filter_bottom_sheet_modal, null)
        bottomSheetDialog.setContentView(filterBottomModal)
    }

}