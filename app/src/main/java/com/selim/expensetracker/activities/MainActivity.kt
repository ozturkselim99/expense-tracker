package com.selim.expensetracker.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.selim.expensetracker.R


class MainActivity : AppCompatActivity() {

    private val fabAdd by lazy { findViewById<FloatingActionButton>(R.id.fabAdd) }
    private val fabExpense by lazy { findViewById<FloatingActionButton>(R.id.fabExpense) }
    private val fabIncome by lazy { findViewById<FloatingActionButton>(R.id.fabIncome) }
    private val fabTransfer by lazy { findViewById<FloatingActionButton>(R.id.fabTransfer) }
    private val homeLayout by lazy { findViewById<ConstraintLayout>(R.id.homeLayout) }

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
                this,
                R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
                this,
                R.anim.to_bottom_anim
        )
    }

    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.softYellow)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment2)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        fabAdd.setOnClickListener {
            onAddButtonClicked()
        }
        fabExpense.setOnClickListener {
            val intent = Intent(this, ExpenseActivity::class.java)
            startActivity(intent)
        }
        fabTransfer.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }
        fabIncome.setOnClickListener {
            val intent = Intent(this, IncomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            fabIncome.visibility = View.VISIBLE
            fabExpense.visibility = View.VISIBLE
            fabTransfer.visibility = View.VISIBLE
        } else {
            fabIncome.visibility = View.INVISIBLE
            fabExpense.visibility = View.INVISIBLE
            fabTransfer.visibility = View.INVISIBLE
        }

    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            fabIncome.startAnimation(fromBottom)
            fabExpense.startAnimation(fromBottom)
            fabTransfer.startAnimation(fromBottom)
            fabAdd.startAnimation(rotateOpen)
        } else {
            fabIncome.startAnimation(toBottom)
            fabExpense.startAnimation(toBottom)
            fabTransfer.startAnimation(toBottom)
            fabAdd.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked) {
            fabIncome.isClickable = true
            fabExpense.isClickable = true
            fabTransfer.isClickable = true
        } else {
            fabIncome.isClickable = false
            fabExpense.isClickable = false
            fabTransfer.isClickable = false
        }
    }

}