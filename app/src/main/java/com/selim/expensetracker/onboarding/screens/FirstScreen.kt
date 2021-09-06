package com.selim.expensetracker.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.selim.expensetracker.R

class FirstScreen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_first_screen, container, false)

        val viewPager=activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.findViewById<TextView>(R.id.next1).setOnClickListener {
            viewPager?.currentItem=1
        }
        return view
    }

}