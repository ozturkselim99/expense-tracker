package com.selim.expensetracker.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selim.expensetracker.databinding.ActivityExportDataBinding

class ExportDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExportDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExportDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.exportDataBackButton.setOnClickListener {
            onBackPressed()
        }
        binding.exportDataButton.setOnClickListener {
            val intent = Intent(this, ExportDataSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}