package com.selim.expensetracker.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R

class IncomeActivity : AppCompatActivity() {

    private val incomeBackButton by lazy { findViewById<ImageView>(R.id.incomeBackButton) }
    private val addAttachmentIncome by lazy { findViewById<LinearLayout>(R.id.addAttachmentIncome) }
    private val addIncomeButton by lazy { findViewById<Button>(R.id.addIncomeButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

        val bottomSheetDialog= BottomSheetDialog(this)
        val view=layoutInflater.inflate(R.layout.bottom_sheet_modal,null)
        var dialog= Dialog(this)
        bottomSheetDialog.setContentView(view)

        addAttachmentIncome.setOnClickListener {
            bottomSheetDialog.show()
        }
        addIncomeButton.setOnClickListener {
            dialog.setContentView(R.layout.transaction_successfully_dialog)
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
                onBackPressed()
                finish()
            },1500)
        }
        incomeBackButton.setOnClickListener {
            onBackPressed()
            finish()
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
    }
}