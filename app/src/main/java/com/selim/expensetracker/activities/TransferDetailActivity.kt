package com.selim.expensetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R

class TransferDetailActivity : AppCompatActivity() {
    private val transferTransactionDetailBackButton by lazy { findViewById<ImageView>(R.id.transferTransactionDetailBackButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_detail)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
        transferTransactionDetailBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}