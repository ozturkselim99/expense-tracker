package com.selim.expensetracker.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.selim.expensetracker.utils.FirebaseUtils.storageRef


class FirebaseStorageManager {


    private lateinit var mProgressDialog:ProgressDialog

    fun uploadImage(mContext:Context,imageURI:Uri){
        mProgressDialog= ProgressDialog(mContext)
        mProgressDialog.setMessage("Please wait, image being up")
       val uploadTask= storageRef.child("users/profilePic.png").putFile(imageURI)

        uploadTask.addOnSuccessListener {
            Toast.makeText(
                mContext, "success",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener{
            Toast.makeText(
                mContext, "fail",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}