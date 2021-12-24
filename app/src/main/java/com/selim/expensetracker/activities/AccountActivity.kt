package com.selim.expensetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selim.expensetracker.R
import com.selim.expensetracker.adapters.AccountsAdapter
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData

class AccountActivity : AppCompatActivity() {
    private val recyclerViewAccounts by lazy { findViewById<RecyclerView>(R.id.accountsRW) }
    private val accountBackButton by lazy { findViewById<ImageView>(R.id.accountBackButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        setupRecyclerview()

        accountBackButton.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(this)
        recyclerViewAccounts.layoutManager = layoutManager

        val adapter = AccountsAdapter(MockData.getAccounts())
        recyclerViewAccounts.adapter = adapter
    }
}