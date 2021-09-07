package com.selim.expensetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class TransferActivity : AppCompatActivity() {
    private val transferBackButton by lazy { findViewById<ImageView>(R.id.transferBackButton) }
    private val addAttachmentTransfer by lazy { findViewById<LinearLayout>(R.id.addAttachmentTransfer) }
    /*
    private val myBottomSheet by lazy { findViewById<ConstraintLayout>(R.id.myBottomSheet) }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)


        val bottomSheetDialog=BottomSheetDialog(this)
        val view=layoutInflater.inflate(R.layout.bottom_sheet_modal,null)

        bottomSheetDialog.setContentView(view)
        addAttachmentTransfer.setOnClickListener {
            bottomSheetDialog.show()
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)

        transferBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}