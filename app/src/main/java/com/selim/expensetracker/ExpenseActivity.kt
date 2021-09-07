package com.selim.expensetracker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExpenseActivity : AppCompatActivity() {
    private val expenseBackButton by lazy { findViewById<ImageView>(R.id.expenseBackButton) }
    private val addAttachmentExpense by lazy { findViewById<LinearLayout>(R.id.addAttachmentExpense) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)
        val bottomSheetDialog= BottomSheetDialog(this)
        val view=layoutInflater.inflate(R.layout.bottom_sheet_modal,null)

        bottomSheetDialog.setContentView(view)
        addAttachmentExpense.setOnClickListener {
            bottomSheetDialog.show()
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.red)
        expenseBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}