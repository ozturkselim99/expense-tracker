package com.selim.expensetracker.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.UserProfileChangeRequest
import com.selim.expensetracker.utils.FirebaseUtils

class SignUpViewModel : ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loading.postValue(false)
        error.postValue(false)
    }

    fun signUp(email: String, password: String, name: String) {
        loading.postValue(true)
        FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (FirebaseUtils.firebaseAuth.currentUser?.uid != null) {
                        val request = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                        FirebaseUtils.firebaseAuth.currentUser?.updateProfile(request)
                            ?.addOnSuccessListener {
                                addUserToFirebase()
                            }?.addOnFailureListener {
                            error.postValue(true)
                        }
                    }
                } else {
                }
            }
    }

    private fun addUserToFirebase() {
        val user = hashMapOf(
            "userId" to FirebaseUtils.firebaseAuth.currentUser!!.uid,
            "name" to FirebaseUtils.firebaseAuth.currentUser!!.displayName,
            "email" to FirebaseUtils.firebaseAuth.currentUser!!.email,
        )
        FirebaseUtils.firebaseFirestore?.collection("Users")
            ?.document(FirebaseUtils.firebaseAuth.currentUser!!.uid)?.set(
                user
            )
            ?.addOnSuccessListener {
                error.postValue(false)
                loading.postValue(false)
                //sendEmailVerification()
            }
            ?.addOnFailureListener {
                error.postValue(true)
            }
    }

    // daha sonra bakılacak
    private fun sendEmailVerification() {
        FirebaseUtils.firebaseAuth.currentUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    /*
                    findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
                     */
                }
            }
        }
    }

}