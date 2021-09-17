package com.selim.expensetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R

class IncomeDetailActivity : AppCompatActivity() {
    private val incomeTransactionDetailBackButton by lazy { findViewById<ImageView>(R.id.incomeTransactionDetailBackButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_detail)
        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
        incomeTransactionDetailBackButton.setOnClickListener {
            onBackPressed()
        }
    }

}