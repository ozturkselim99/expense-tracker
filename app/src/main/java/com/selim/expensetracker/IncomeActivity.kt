package com.selim.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog

class IncomeActivity : AppCompatActivity() {
    private val incomeBackButton by lazy { findViewById<ImageView>(R.id.incomeBackButton) }
    private val addAttachmentIncome by lazy { findViewById<LinearLayout>(R.id.addAttachmentIncome) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)
        val bottomSheetDialog= BottomSheetDialog(this)
        val view=layoutInflater.inflate(R.layout.bottom_sheet_modal,null)

        bottomSheetDialog.setContentView(view)
        addAttachmentIncome.setOnClickListener {
            bottomSheetDialog.show()
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
        incomeBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}