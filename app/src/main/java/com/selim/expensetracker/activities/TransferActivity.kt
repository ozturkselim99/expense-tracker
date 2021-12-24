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
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.ActivityExpenseBinding
import com.selim.expensetracker.databinding.ActivityTransferBinding

class TransferActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog
    private lateinit var bottomSheetDialog:BottomSheetDialog
    private var binding: ActivityTransferBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_transfer)

        bottomSheetDialog=BottomSheetDialog(this,R.style.AttachmentBottomSheetDialogTheme)
        dialog=Dialog(this)

        setupBottomSheet()

        binding!!.addAttachmentTransfer.setOnClickListener {
            bottomSheetDialog.show()
        }
        binding!!.addTransferButton.setOnClickListener {
            dialog.setContentView(R.layout.transaction_unsuccessfully_dialog)
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
                onBackPressed()
                finish()
            },1500)
        }
        binding!!.transferBackButton.setOnClickListener {
            onBackPressed()
            finish()
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_100)
    }
    private fun setupBottomSheet(){
        val view=layoutInflater.inflate(R.layout.bottom_sheet_modal,null)
        bottomSheetDialog.setContentView(view)
    }

}