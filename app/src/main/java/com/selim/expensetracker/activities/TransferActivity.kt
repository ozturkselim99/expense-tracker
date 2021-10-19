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

class TransferActivity : AppCompatActivity() {

    private val transferBackButton by lazy { findViewById<ImageView>(R.id.transferBackButton) }
    private val addAttachmentTransfer by lazy { findViewById<LinearLayout>(R.id.addAttachmentTransfer) }
    private val addTransferButton by lazy { findViewById<Button>(R.id.addTransferButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)



        val bottomSheetDialog=BottomSheetDialog(this,R.style.AttachmentBottomSheetDialogTheme)
        val view=layoutInflater.inflate(R.layout.bottom_sheet_modal,null)
        var dialog=Dialog(this)
        bottomSheetDialog.setContentView(view)

        addAttachmentTransfer.setOnClickListener {
            bottomSheetDialog.show()
        }
        addTransferButton.setOnClickListener {
            dialog.setContentView(R.layout.transaction_unsuccessfully_dialog)
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
                onBackPressed()
                finish()
            },1500)
        }
        transferBackButton.setOnClickListener {
            onBackPressed()
            finish()
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_100)
    }
}