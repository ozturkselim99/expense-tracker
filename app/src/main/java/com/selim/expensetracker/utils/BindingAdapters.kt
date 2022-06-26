package com.selim.expensetracker.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.selim.expensetracker.R

@BindingAdapter("downloadImageFromUrl")
fun ImageView.downloadImageFromUrl(url: String?){
    if(!url.isNullOrEmpty()){
        FirebaseUtils.storageRef.child(url).downloadUrl.addOnSuccessListener {Uri->
            Glide.with(this)
                .load(Uri)
                .into(this)
        }
    }
}