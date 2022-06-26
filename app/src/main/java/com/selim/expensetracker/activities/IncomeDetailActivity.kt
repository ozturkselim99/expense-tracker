package com.selim.expensetracker.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.ActivityIncomeDetailBinding
import com.selim.expensetracker.models.Transactions
import com.selim.expensetracker.utils.FirebaseUtils
import com.selim.expensetracker.utils.showToastShort

class IncomeDetailActivity : AppCompatActivity() {

    private val binding: ActivityIncomeDetailBinding by lazy {
        ActivityIncomeDetailBinding.inflate(
            layoutInflater
        )
    }
    private lateinit var dialog: Dialog
    private lateinit var transaction: Transactions
    private lateinit var bottomSheetDialog:BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bottomSheetDialog= BottomSheetDialog(this)
        dialog= Dialog(this)

        val bottomSheetDialogView=layoutInflater.inflate(R.layout.delete_transaction_bottom_sheet_modal,null)
        bottomSheetDialog.setContentView(bottomSheetDialogView)

        binding.imageView20.setOnClickListener {
            bottomSheetDialog.show()
        }
        bottomSheetDialogView.findViewById<Button>(R.id.removeTransactionNoButton).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialogView.findViewById<Button>(R.id.removeTransactionYesButton).setOnClickListener {
            deleteIncomeTransaction(intent.getStringExtra("transactionId").toString())
        }
        initViews()
        window.statusBarColor = ContextCompat.getColor(this, R.color.green_100)
        getIncomeDetail(intent.getStringExtra("transactionId").toString())
    }

    private fun initViews() {
        binding.incomeTransactionDetailBackButton.setOnClickListener {
            onBackPressed()
        }
        binding.editIncomeButton.setOnClickListener {
            val updateIntent = Intent(this, IncomeActivity::class.java)
            updateIntent.putExtra("IncomeTransaction", transaction)
            startActivity(updateIntent)
        }
    }

    private fun getIncomeDetail(transactionId: String) {
        //TODO:xml içindeki wallet kısmı değişcek yeni bir field eklenip controlü orda sağalanacak
        FirebaseUtils.firebaseAuth.currentUser?.uid?.let { userId ->
            FirebaseUtils.firebaseFirestore?.collection("Users")?.document(userId)
                ?.collection("Transactions")?.document(transactionId)?.get()
                ?.addOnSuccessListener { incomeDetail ->
                    incomeDetail.data?.let {
                       transaction = Transactions(
                            transactionId,
                            it["selectedCategory"].toString(),
                            it["description"].toString(),
                            it["createdAt"].toString(),
                            it["amount"].toString(),
                            it["transactionType"].toString(),
                            it["imageUrl"].toString(),
                            it["selectedBank"].toString(),
                        )
                        binding.transactionDetail=transaction
                    }
                }
                ?.addOnFailureListener { exception ->
                    showToastShort("$exception")
                }
        }
    }

    private fun deleteIncomeTransaction(transactionId:String){
        FirebaseUtils.firebaseAuth.currentUser?.uid?.let {userId->
            FirebaseUtils.firebaseFirestore?.collection("Users")?.document(userId)
                ?.collection("Transactions")?.document(transactionId)?.delete()
                ?.addOnSuccessListener {
                    dialog.setContentView(R.layout.transaction_successfully_dialog)
                    dialog.show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        dialog.dismiss()
                        onBackPressed()
                        finish()
                    },1500)
                }
                ?.addOnFailureListener { exception ->
                    showToastShort("$exception")
                }
        }
    }

}