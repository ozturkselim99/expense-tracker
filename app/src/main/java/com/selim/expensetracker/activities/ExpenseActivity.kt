package com.selim.expensetracker.activities

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.ActivityExpenseBinding
import com.selim.expensetracker.utils.showToastShort

class ExpenseActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var binding: ActivityExpenseBinding


    private var selectedCategory: String? = null
    private var description: String? = null
    private var selectedBank: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomSheetDialog = BottomSheetDialog(this, R.style.AttachmentBottomSheetDialogTheme)
        dialog = Dialog(this)

        setupBottomSheet()
        setupSpinners()

        binding.addAttachmentExpense.setOnClickListener {
            bottomSheetDialog.show()
        }

        binding.addExpenseButton.setOnClickListener {
            dialog.setContentView(R.layout.transaction_successfully_dialog)
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
                onBackPressed()
                finish()
            }, 1500)
        }
        binding.expenseBackButton.setOnClickListener {
            onBackPressed()
            finish()
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.red_100)
    }

    private fun setupBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_modal, null)
        bottomSheetDialog.setContentView(view)
    }

    private fun setupSpinners() {
        val expenseCategoryFilterSpinnerAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getCategories()
        )
        val expenseWalletFilterSpinnerAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getBanks()
        )
        binding.expenseCategoryFilterSpinner.setAdapter(expenseCategoryFilterSpinnerAdapter)
        binding.expenseWalletFilterSpinner.setAdapter(expenseWalletFilterSpinnerAdapter)

        binding.expenseWalletFilterSpinner
            .setOnItemClickListener { _, _, position, _ ->
                showToastShort(
                    "${
                        expenseWalletFilterSpinnerAdapter.getItem(position).toString()
                    } selected"
                )
                selectedBank = expenseWalletFilterSpinnerAdapter.getItem(position).toString()
            }

        binding.expenseCategoryFilterSpinner.setOnItemClickListener { _, _, position, _ ->
            showToastShort(
                "${
                    expenseCategoryFilterSpinnerAdapter.getItem(position).toString()
                } selected"
            )
            selectedCategory = expenseCategoryFilterSpinnerAdapter.getItem(position).toString()
        }
    }
}