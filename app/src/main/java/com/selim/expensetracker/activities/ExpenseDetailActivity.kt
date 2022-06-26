package com.selim.expensetracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.ActivityExpenseDetailBinding

class ExpenseDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpenseDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.red_100)
    }

    private fun initViews() {
        binding.expenseTransactionDetailBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}