package com.selim.expensetracker.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.selim.expensetracker.R
import com.selim.expensetracker.charttab.ChartTabsViewPagerAdapter
import com.selim.expensetracker.charttab.screens.LineChartTab
import com.selim.expensetracker.charttab.screens.PieChartTab
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.expensetab.ExpenseTabsViewPagerAdapter
import com.selim.expensetracker.expensetab.screens.ExpenseTab
import com.selim.expensetracker.expensetab.screens.IncomeTab
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries


class FinancialReportDetailActivity : AppCompatActivity() {

    private val spinnerMonthsFinancialReport by lazy { findViewById<AutoCompleteTextView>(R.id.datesFilterSpinner) }
    /*
    private val pieChart by lazy { findViewById<PieChart>(R.id.pieChart) }
    private val lineChart by lazy { findViewById<ValueLineChart>(R.id.lineChart) }

     */
    private val tabExpenseViewPager by lazy { findViewById<ViewPager2>(R.id.tabExpenseVP) }
    private val tabExpenseLayout by lazy { findViewById<TabLayout>(R.id.tabExpenseLayout) }
    private val financialReportBackButton by lazy { findViewById<ImageView>(R.id.financialReportBackButton) }

    private val tabChartViewPager by lazy { findViewById<ViewPager2>(R.id.tabChartViewPager) }
    private val tabChartLayout by lazy { findViewById<TabLayout>(R.id.tabChartLayout) }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financial_report_detail)

        val spinnerMonths = spinnerMonthsFinancialReport.findViewById<AutoCompleteTextView>(R.id.datesFilterSpinner)

        val spinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, MockData.getMonths())

        spinnerMonths.setAdapter(spinnerAdapter)
        financialReportBackButton.setOnClickListener {
            onBackPressed()
        }
        setAdapters()
    }

    private fun setAdapters() {
        val fragmentList = arrayListOf(
            ExpenseTab(),
            IncomeTab(),
        )
        val fragmentList2 = arrayListOf(
            LineChartTab(),
            PieChartTab(),
        )

        val adapter =
            ExpenseTabsViewPagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)
        tabExpenseViewPager.adapter = adapter
        TabLayoutMediator(tabExpenseLayout, tabExpenseViewPager) { tab, position ->
            when (position) {
                0 -> { tab.text = "Expense"}
                1 -> { tab.text = "Income"}
            }
        }.attach()

        val adapter2 =
            ChartTabsViewPagerAdapter(fragmentList2, this.supportFragmentManager, lifecycle)
        tabChartViewPager.adapter = adapter2
        TabLayoutMediator(tabChartLayout, tabChartViewPager) { tab, position ->
            when (position) {
                0 -> { tab.icon = ContextCompat.getDrawable(this,R.drawable.ic_baseline_show_chart_24)}
                1 -> { tab.icon = ContextCompat.getDrawable(this,R.drawable.ic_baseline_pie_chart_24)}
            }
        }.attach()

    }
}