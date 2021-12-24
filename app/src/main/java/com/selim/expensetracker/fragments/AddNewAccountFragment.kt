package com.selim.expensetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.FragmentAddNewAccountBinding
import com.selim.expensetracker.utils.FirebaseUtils


class AddNewAccountFragment : Fragment() {

    private var binding: FragmentAddNewAccountBinding? = null
    private var accountMainBank: String? = null
    private var accountType: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNewAccountBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this

        binding!!.addNewAccountContinueButton.setOnClickListener {
            if (accountMainBank != null && accountType != null && binding!!.editTextAcountName.text.toString()
                    .isNotEmpty() && binding!!.editTextAccountBalance.text.toString().isNotEmpty()
            ) {
                addNewAccount()
            } else {
                Toast.makeText(requireContext(), "Alan doldur", Toast.LENGTH_SHORT).show()
            }
        }
        binding!!.backToSetupAccount.setOnClickListener {
            findNavController().navigate(R.id.action_addNewAccountFragment_to_setupAccountFragment)
        }
        setupSpinners()


        return binding!!.root

    }

    private fun setupSpinners() {
        val accountTypesSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getAccountTypes()
        )
        val banksSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getBanks()
        )

        binding!!.accountTypesSpinner.setAdapter(accountTypesSpinnerAdapter)
        binding!!.banksSpinner.setAdapter(banksSpinnerAdapter)

        binding!!.accountTypesSpinner
            .setOnItemClickListener { _, _, position, _ ->
                when (accountTypesSpinnerAdapter.getItem(position)) {
                    "Bank" -> {
                        binding!!.banksContainer.visibility = View.VISIBLE
                    }
                    "Wallet" -> {
                        binding!!.banksContainer.visibility = View.GONE
                    }
                }
                Toast.makeText(
                    requireContext(),
                    "${accountTypesSpinnerAdapter.getItem(position).toString()} selected",
                    Toast.LENGTH_SHORT
                ).show()
                accountType = accountTypesSpinnerAdapter.getItem(position).toString()
            }
        binding!!.banksSpinner
            .setOnItemClickListener { _, _, position, _ ->
                Toast.makeText(
                    requireContext(),
                    "${banksSpinnerAdapter.getItem(position).toString()} selected",
                    Toast.LENGTH_SHORT
                ).show()
                accountMainBank = banksSpinnerAdapter.getItem(position).toString()
            }

    }

    private fun addNewAccount() {
        FirebaseUtils.firebaseFirestore?.collection("Users")
            ?.document(FirebaseUtils.firebaseAuth.currentUser!!.uid)?.update(
                mapOf(
                    "accountName" to binding!!.editTextAcountName.text.toString(),
                    "accountBalance" to binding!!.editTextAccountBalance.text.toString(),
                    "accountType" to accountType,
                    "accountMainBank" to accountMainBank
                )
            )?.addOnSuccessListener {
                Toast.makeText(
                    requireContext(), "Account creation successful",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_addNewAccountFragment_to_signUpSuccessFragment)
            }?.addOnFailureListener {
                Toast.makeText(
                    requireContext(), "Account creation failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }


}