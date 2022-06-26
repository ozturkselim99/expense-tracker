package com.selim.expensetracker.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


object FirebaseUtils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseFirestore: FirebaseFirestore? = FirebaseFirestore.getInstance()
    var storageRef = FirebaseStorage.getInstance().reference
}