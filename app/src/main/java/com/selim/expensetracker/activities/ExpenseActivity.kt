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

class ExpenseActivity : AppCompatActivity() {

    private val expenseBackButton by lazy { findViewById<ImageView>(R.id.expenseBackButton) }
    private val addAttachmentExpense by lazy { findViewById<LinearLayout>(R.id.addAttachmentExpense) }
    private val addExpenseButton by lazy { findViewById<Button>(R.id.addExpenseButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_modal, null)
        var dialog = Dialog(this)
        bottomSheetDialog.setContentView(view)

        addAttachmentExpense.setOnClickListener {
            bottomSheetDialog.show()
        }
        addExpenseButton.setOnClickListener {
            dialog.setContentView(R.layout.transaction_successfully_dialog)
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
                onBackPressed()
                finish()
            }, 1500)
        }
        expenseBackButton.setOnClickListener {
            onBackPressed()
            finish()
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.red)
    }
}