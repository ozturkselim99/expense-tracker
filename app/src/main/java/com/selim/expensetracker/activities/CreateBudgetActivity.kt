package com.selim.expensetracker.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import com.selim.expensetracker.R

class CreateBudgetActivity : AppCompatActivity() {
    private val createBudgetBackButton by lazy { findViewById<ImageView>(R.id.createBudgetBackButton) }
    private val addBudget by lazy { findViewById<Button>(R.id.addBudget) }
    private lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_budget)
        dialog= Dialog(this)

        createBudgetBackButton.setOnClickListener {
            onBackPressed()
        }
        addBudget.setOnClickListener {
            dialog.setContentView(R.layout.transaction_successfully_dialog)
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
                onBackPressed()
                finish()
            },1500)
        }
    }
}