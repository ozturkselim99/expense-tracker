package com.selim.expensetracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.selim.expensetracker.R

class ExportDataSuccessActivity : AppCompatActivity() {

    private val backToHomeButton by lazy { findViewById<Button>(R.id.backToHomeButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_data_success)
        backToHomeButton.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}