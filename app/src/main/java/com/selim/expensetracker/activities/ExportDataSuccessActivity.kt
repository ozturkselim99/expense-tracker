package com.selim.expensetracker.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.ActivityExportDataSuccessBinding

class ExportDataSuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExportDataSuccessBinding

    private val backToHomeButton by lazy { findViewById<Button>(R.id.backToHomeButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExportDataSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }
    private fun initViews(){
        backToHomeButton.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}