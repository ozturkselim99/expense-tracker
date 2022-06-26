package com.selim.expensetracker.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow_20)
        val navController = findNavController(R.id.fragment2)
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }

    private fun initViews(){
        binding.fabAdd.setOnClickListener {
            onAddButtonClicked()
        }
        binding.fabExpense.setOnClickListener {
            val intent = Intent(this, ExpenseActivity::class.java)
            startActivity(intent)
        }
        binding.fabTransfer.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }
        binding.fabIncome.setOnClickListener {
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
            binding.fabIncome.visibility = View.VISIBLE
            binding.fabExpense.visibility = View.VISIBLE
            binding.fabTransfer.visibility = View.VISIBLE
        } else {
            binding.fabIncome.visibility = View.INVISIBLE
            binding.fabExpense.visibility = View.INVISIBLE
            binding.fabTransfer.visibility = View.INVISIBLE
        }

    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.fabIncome.startAnimation(fromBottom)
            binding.fabExpense.startAnimation(fromBottom)
            binding.fabTransfer.startAnimation(fromBottom)
            binding.fabAdd.startAnimation(rotateOpen)
        } else {
            binding.fabIncome.startAnimation(toBottom)
            binding.fabExpense.startAnimation(toBottom)
            binding.fabTransfer.startAnimation(toBottom)
            binding.fabAdd.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked) {
            binding.fabIncome.isClickable = true
            binding.fabExpense.isClickable = true
            binding.fabTransfer.isClickable = true
        } else {
            binding.fabIncome.isClickable = false
            binding.fabExpense.isClickable = false
            binding.fabTransfer.isClickable = false
        }
    }
}