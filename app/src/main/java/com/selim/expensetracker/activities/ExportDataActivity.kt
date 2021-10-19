package com.selim.expensetracker.activities

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.selim.expensetracker.R

class ExportDataActivity : AppCompatActivity() {

    private val exportDataBackButton by lazy { findViewById<ImageView>(R.id.exportDataBackButton) }
    private val exportDataButton by lazy { findViewById<Button>(R.id.exportDataButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_data)

        exportDataBackButton.setOnClickListener {
            onBackPressed()
        }
        exportDataButton.setOnClickListener {
            val intent=Intent(this,ExportDataSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}