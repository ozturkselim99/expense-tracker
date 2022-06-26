package com.selim.expensetracker.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.selim.expensetracker.R
import com.selim.expensetracker.onboarding.screens.FirstScreen
import com.selim.expensetracker.onboarding.screens.SecondScreen
import com.selim.expensetracker.onboarding.screens.ThirdScreen

class OnboardingViewPagerFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
                FirstScreen(),
                SecondScreen(),
                ThirdScreen()
        )

        val adapter = OnboardingViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        view.findViewById<ViewPager2>(R.id.viewPager).adapter = adapter

        return view
    }

}