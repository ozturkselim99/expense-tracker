package com.selim.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.ExpenseDetailActivity
import com.selim.expensetracker.activities.IncomeDetailActivity
import com.selim.expensetracker.activities.NotificationActivity
import com.selim.expensetracker.adapters.TransactionAdapter
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.FragmentHomeBinding
import com.selim.expensetracker.models.Transactions
import com.selim.expensetracker.utils.FirebaseUtils.firebaseAuth
import com.selim.expensetracker.utils.FirebaseUtils.firebaseFirestore
import com.selim.expensetracker.utils.showToastShort


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = TransactionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        adapter.onItemClicked = { it ->
            val intent = when (it.transactionType) {
                "Income" -> {
                    Intent(requireContext(), IncomeDetailActivity::class.java)
                }
                "Expense" ->{
                    Intent(requireContext(), ExpenseDetailActivity::class.java)
                }
                //TODO:farkli aktivite oluşturulacak
                else->{
                    Intent(requireContext(), ExpenseDetailActivity::class.java)
                }
            }
            intent.putExtra("transactionId", it.transactionId)
            startActivity(intent)
        }
        setupRecyclerview()
        setupSpinner()
        getAccountInfo()
        initViews()
        return binding.root
    }

    override fun onStart() {
        getAccountInfo()
        getRecentTransaction()
        super.onStart()
    }

    private fun initViews() {
        binding.notifications.setOnClickListener {
            val intent = Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerview() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.transactionRW.layoutManager = layoutManager
    }

    private fun loadTransactions(transactions: List<Transactions>) {
        adapter.items=transactions
        binding.transactionRW.adapter = adapter
    }

    private fun setupSpinner() {
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getMonths()
        )

        binding.datesFilterSpinner.setAdapter(spinnerAdapter)
    }

    private fun getAccountInfo() {
        firebaseAuth.currentUser?.uid?.let {
            firebaseFirestore?.collection("Users")?.document(it)
                ?.get()
                ?.addOnSuccessListener { document ->
                    binding.accountBalanceText.text = document.get("accountBalance").toString()+"$"
                }
                ?.addOnFailureListener { exception ->
                    showToastShort("$exception")
                }
        }
    }

    private fun getRecentTransaction() {
        firebaseAuth.currentUser?.uid?.let {
            firebaseFirestore?.collection("Users")?.document(it)
                ?.collection("Transactions")?.get()
                ?.addOnSuccessListener { result ->
                    var transactions = mutableListOf<Transactions>()
                    for (document in result) {
                        transactions.add(
                            Transactions(
                                document.id,
                                document.data["selectedCategory"].toString(),
                                document.data["description"].toString(),
                                document.data["createdAt"].toString(),
                                document.data["amount"].toString() + "$",
                                document.data["transactionType"].toString(),
                                document.data["imageUrl"].toString(),
                                document.data["selectedBank"].toString(),
                            )
                        )
                    }
                    loadTransactions(transactions)
                }
                ?.addOnFailureListener { exception ->
                    showToastShort("$exception")
                }
        }
    }
}