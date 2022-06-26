package com.selim.expensetracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.ActivityTransferDetailBinding

class TransferDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_100)

    }
    private fun initViews(){
        binding.transferTransactionDetailBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}