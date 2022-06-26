package com.selim.expensetracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selim.expensetracker.utils.FirebaseUtils

class LoginViewModel : ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loading.postValue(false)
        error.postValue(false)
    }

    fun login(email: String, password: String) {
        loading.postValue(true)
        FirebaseUtils.firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    error.postValue(false)
                    loading.postValue(false)
                } else {
                    error.postValue(true)
                }
            }
    }
}