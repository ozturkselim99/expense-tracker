package com.selim.expensetracker.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.selim.expensetracker.databinding.ActivityFinancialReportBinding
import com.selim.expensetracker.financial.screens.ExpenseReport
import com.selim.expensetracker.financial.screens.FinancialQuote
import com.selim.expensetracker.financial.screens.IncomeReport
import com.selim.expensetracker.financial.screens.TransferReport
import com.selim.expensetracker.onboarding.OnboardingViewPagerAdapter
import kotlin.math.abs


class FinancialReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinancialReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinancialReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val fragmentList = arrayListOf(
            ExpenseReport(),
            IncomeReport(),
            TransferReport(),
            FinancialQuote()
        )

        val adapter =
                OnboardingViewPagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)
        binding.financialReportVP.setPageTransformer { page, position ->
            //Helps us in animation view
            page.cameraDistance = 20000f
            //Page is too left to be seen [-infinity,-1]
            when {
                position < -1 -> {
                    page.alpha = 0f
                    //Page is in between [-1,0]
                }
                position <= 0 -> {
                    page.alpha = 1f
                    page.pivotX = (page.width).toFloat()
                    page.rotationY = -90 * abs(position)
                    //Page is between (0,1]
                }
                position <= 1 -> {
                    page.alpha = 1f
                    page.pivotX = 0f
                    page.rotationY = 90 * abs(position)
                    //Page is between (1,infinity]
                }
                else -> {
                    page.alpha = 0f
                }
            }
        }
        binding.financialReportVP.adapter = adapter

    }
}