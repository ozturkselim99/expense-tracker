package com.selim.expensetracker.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.NotificationActivity
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.FragmentHomeBinding
import com.selim.expensetracker.utils.FirebaseUtils.firebaseAuth
import com.selim.expensetracker.utils.FirebaseUtils.firebaseFirestore


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this


        setupRecyclerview()
        setupSpinner()
        getAccountInfo()

        binding!!.notifications.setOnClickListener {
            val intent = Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
        }
        return binding!!.root
    }

    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding!!.transactionRW.layoutManager = layoutManager

        val adapter = TransactionAdapter(MockData.getTransactions())
        binding!!.transactionRW.adapter = adapter
    }

    private fun setupSpinner() {
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getMonths()
        )

        binding!!.datesFilterSpinner.setAdapter(spinnerAdapter)
    }

    private fun getAccountInfo() {
        firebaseAuth.currentUser?.uid?.let {
            firebaseFirestore?.collection("Users")?.document(it)
                ?.get()
                ?.addOnSuccessListener { document ->
                    binding!!.accountBalanceText.text = document.get("accountBalance").toString()
                }
                ?.addOnFailureListener { exception ->
                    Toast.makeText(
                        requireContext(), "$exception",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }


}