package com.selim.expensetracker.activities

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.ActivityCreateBudgetBinding

class CreateBudgetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBudgetBinding
    private lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog= Dialog(this)

        binding.createBudgetBackButton.setOnClickListener {
            onBackPressed()
        }
        binding.addBudget.setOnClickListener {
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