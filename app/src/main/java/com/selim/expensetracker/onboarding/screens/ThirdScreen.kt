package com.selim.expensetracker.onboarding.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R

class ThirdScreen : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        view.findViewById<TextView>(R.id.finish).setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
            onBoardingFinished()
        }

        return view

    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}