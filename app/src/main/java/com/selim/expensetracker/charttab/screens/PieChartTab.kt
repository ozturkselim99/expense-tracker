package com.selim.expensetracker.charttab.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.selim.expensetracker.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.PieModel


class PieChartTab : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pie_chart_tab, container, false)
        var pieChart = view.findViewById<PieChart>(R.id.pieChart)

        pieChart.addPieSlice(
            PieModel(
                "Shopping",
                120f,
                ContextCompat.getColor(requireContext(), R.color.black)
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Subcription", 80f, ContextCompat.getColor(
                    requireContext(),
                    R.color.violett
                )
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Food",
                32f,
                ContextCompat.getColor(requireContext(), R.color.red)
            )
        )
        pieChart.startAnimation()
        return view
    }

}