package com.selim.expensetracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.expensetracker.adapters.AccountsAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerview()
        initViews()

    }

    private fun initViews(){
        binding.accountBackButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(this)
        binding.accountsRW.layoutManager = layoutManager

        val adapter = AccountsAdapter(MockData.getAccounts())
        binding.accountsRW.adapter = adapter
    }
}