package com.selim.expensetracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selim.expensetracker.utils.FirebaseUtils

class AddNewAccountViewModel:ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loading.postValue(false)
        error.postValue(false)
    }

    fun addNewAccount(accountName:String,accountBalance:String,accountType:String,accountMainBank:String) {
        loading.postValue(true)
        FirebaseUtils.firebaseFirestore?.collection("Users")
            ?.document(FirebaseUtils.firebaseAuth.currentUser!!.uid)?.update(
                mapOf(
                    "accountName" to accountName,
                    "accountBalance" to accountBalance,
                    "accountType" to accountType,
                    "accountMainBank" to accountMainBank
                )
            )?.addOnSuccessListener {
                error.postValue(false)
                loading.postValue(false)
            }?.addOnFailureListener {
                error.postValue(true)
            }

    }
    /*
    private fun addNewAccount() {
        FirebaseUtils.firebaseFirestore?.collection("Users")
            ?.document(FirebaseUtils.firebaseAuth.currentUser!!.uid)?.update(
                mapOf(
                    "accountName" to binding.editTextAcountName.text.toString(),
                    "accountBalance" to binding.editTextAccountBalance.text.toString(),
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
     */
}