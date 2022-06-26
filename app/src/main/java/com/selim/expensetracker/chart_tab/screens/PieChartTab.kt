package com.selim.expensetracker.chart_tab.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.FragmentPieChartTabBinding
import org.eazegraph.lib.models.PieModel


class PieChartTab : Fragment() {

    private var _binding: FragmentPieChartTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPieChartTabBinding.inflate(inflater, container, false)

        binding.pieChart.addPieSlice(
            PieModel(
                "Shopping",
                120f,
                ContextCompat.getColor(requireContext(), R.color.black)
            )
        )
        binding.pieChart.addPieSlice(
            PieModel(
                "Subcription", 80f, ContextCompat.getColor(
                    requireContext(),
                    R.color.violet_100
                )
            )
        )
        binding.pieChart.addPieSlice(
            PieModel(
                "Food",
                32f,
                ContextCompat.getColor(requireContext(), R.color.red_100)
            )
        )
        binding.pieChart.startAnimation()
        return binding.root
    }

}