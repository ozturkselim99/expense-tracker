package com.selim.expensetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selim.expensetracker.R
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.FragmentAddNewAccountBinding
import com.selim.expensetracker.utils.setVisible
import com.selim.expensetracker.utils.showToastShort
import com.selim.expensetracker.viewmodel.AddNewAccountViewModel


class AddNewAccountFragment : Fragment() {

    private var _binding: FragmentAddNewAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddNewAccountViewModel by viewModels()
    private var accountMainBank: String = ""
    private var accountType: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddNewAccountBinding.inflate(inflater, container, false)

        initViews()
        setupSpinners()

        return binding.root

    }

    private fun initViews() {
        binding.backToSetupAccount.setOnClickListener {
            findNavController().navigate(R.id.action_addNewAccountFragment_to_setupAccountFragment)
        }
        binding.addNewAccountContinueButton.setOnClickListener {
            if (accountMainBank != "" && accountType != "" && binding.editTextAcountName.text.toString()
                    .isNotEmpty() && binding.editTextAccountBalance.text.toString().isNotEmpty()
            ) {
                viewModel.addNewAccount(
                    binding.editTextAcountName.text.toString(),
                    binding.editTextAccountBalance.text.toString(),
                    accountType,
                    accountMainBank
                )
                observeLiveData()
            } else {
                showToastShort("Enter all fields")
            }
        }
    }

    private fun observeLiveData() {
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                binding.addNewAccountError.setVisible(it)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                binding.addNewAccountLoading.setVisible(it)
                if (!it) {
                    findNavController().navigate(R.id.action_addNewAccountFragment_to_signUpSuccessFragment)
                }
            }
        }
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

        binding.accountTypesSpinner.setAdapter(accountTypesSpinnerAdapter)
        binding.banksSpinner.setAdapter(banksSpinnerAdapter)

        binding.accountTypesSpinner
            .setOnItemClickListener { _, _, position, _ ->
                when (accountTypesSpinnerAdapter.getItem(position)) {
                    "Bank" -> {
                        binding.banksContainer.setVisible(true)
                    }
                    "Wallet" -> {
                        binding.banksContainer.setVisible(false)
                    }
                }
                showToastShort(
                    "${
                        accountTypesSpinnerAdapter.getItem(position).toString()
                    } selected"
                )
                accountType = accountTypesSpinnerAdapter.getItem(position).toString()
            }
        binding.banksSpinner
            .setOnItemClickListener { _, _, position, _ ->
                showToastShort("${banksSpinnerAdapter.getItem(position).toString()} selected")
                accountMainBank = banksSpinnerAdapter.getItem(position).toString()
            }
    }
}