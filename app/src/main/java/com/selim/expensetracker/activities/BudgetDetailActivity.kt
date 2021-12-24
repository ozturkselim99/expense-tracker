package com.selim.expensetracker.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData

class BudgetDetailActivity : AppCompatActivity() {
    private val detailBudgetBackButton by lazy { findViewById<ImageView>(R.id.detailBudgetBackButton) }
    private val deleteBudgetButton by lazy { findViewById<ImageView>(R.id.deleteBudgetButton) }
    private lateinit var dialog: Dialog
    private lateinit var bottomSheetDialog:BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_detail)

        bottomSheetDialog= BottomSheetDialog(this)
        dialog= Dialog(this)

        val bottomSheetDialogView=layoutInflater.inflate(R.layout.delete_budget_bottom_sheet_modal,null)
        bottomSheetDialog.setContentView(bottomSheetDialogView)

        detailBudgetBackButton.setOnClickListener {
            onBackPressed()
        }

        deleteBudgetButton.setOnClickListener {
            bottomSheetDialog.show()
        }
        bottomSheetDialogView.findViewById<Button>(R.id.removeBudgetNoButton).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialogView.findViewById<Button>(R.id.removeBudgetYesButton).setOnClickListener {
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