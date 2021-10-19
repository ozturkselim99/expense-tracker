package com.selim.expensetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.selim.expensetracker.R

class ExpenseDetailActivity : AppCompatActivity() {
    private val expenseTransactionDetailBackButton by lazy { findViewById<ImageView>(R.id.expenseTransactionDetailBackButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_detail)
       window.statusBarColor = ContextCompat.getColor(this, R.color.red_100)
        expenseTransactionDetailBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}