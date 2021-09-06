package com.selim.expensetracker.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.selim.expensetracker.R
import com.selim.expensetracker.onboarding.screens.FirstScreen
import com.selim.expensetracker.onboarding.screens.SecondScreen
import com.selim.expensetracker.onboarding.screens.ThirdScreen

class ViewPagerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_view_pager, container, false)


        val fragmentList= arrayListOf<Fragment>(
                FirstScreen(),
                SecondScreen(),
                ThirdScreen()
        )

        val adapter=ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)

        view.findViewById<ViewPager2>(R.id.viewPager).adapter=adapter


        return view
    }

}