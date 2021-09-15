package com.selim.expensetracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.selim.expensetracker.R
import com.selim.expensetracker.financial.screens.ExpenseReport
import com.selim.expensetracker.financial.screens.FinancialQuote
import com.selim.expensetracker.financial.screens.IncomeReport
import com.selim.expensetracker.financial.screens.TransferReport
import com.selim.expensetracker.onboarding.OnboardingViewPagerAdapter

class FinancialReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financial_report)

        val fragmentList = arrayListOf(
                ExpenseReport(),
                IncomeReport(),
                TransferReport(),
                FinancialQuote()
        )

        val adapter =
                OnboardingViewPagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)
        val financialReportVP = findViewById<ViewPager2>(R.id.financialReportVP)
        financialReportVP.adapter = adapter

    }
}