package com.selim.expensetracker.activities

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.ActivityBudgetDetailBinding

class BudgetDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBudgetDetailBinding
    private lateinit var dialog: Dialog
    private lateinit var bottomSheetDialog:BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO:view binding muhabetti
        bottomSheetDialog= BottomSheetDialog(this)
        dialog= Dialog(this)

        val bottomSheetDialogView=layoutInflater.inflate(R.layout.delete_budget_bottom_sheet_modal,null)
        bottomSheetDialog.setContentView(bottomSheetDialogView)

        binding.detailBudgetBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.deleteBudgetButton.setOnClickListener {
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