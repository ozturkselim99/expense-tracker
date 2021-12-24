package com.selim.expensetracker.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.AccountActivity
import com.selim.expensetracker.activities.ExportDataActivity
import com.selim.expensetracker.databinding.FragmentProfileBinding
import com.selim.expensetracker.utils.FirebaseUtils
import com.selim.expensetracker.utils.FirebaseUtils.firebaseAuth


class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetDialogView = layoutInflater.inflate(R.layout.logout_bottom_sheet_modal, null)
        bottomSheetDialog.setContentView(bottomSheetDialogView)
        var dialog = Dialog(requireContext())
        getAccountName()
        binding!!.account.setOnClickListener {
            val intent = Intent(requireContext(), AccountActivity::class.java)
            startActivity(intent)
        }

        binding!!.logout.setOnClickListener {
            bottomSheetDialog.show()
        }

        binding!!.exportData.setOnClickListener {
            val intent = Intent(requireContext(), ExportDataActivity::class.java)
            startActivity(intent)
        }
        bottomSheetDialogView.findViewById<Button>(R.id.logoutNoButton).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialogView.findViewById<Button>(R.id.logoutYesButton).setOnClickListener {
            dialog.setContentView(R.layout.logout_successfully_dialog)
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
                bottomSheetDialog.dismiss()
                logout()
            }, 1500)
        }
        return binding!!.root
    }

    private fun logout() {
        firebaseAuth.signOut()
    }
    private fun getAccountName() {
        firebaseAuth.currentUser?.uid?.let {
            FirebaseUtils.firebaseFirestore?.collection("Users")?.document(it)
                ?.get()
                ?.addOnSuccessListener { document ->
                    binding!!.accountName.text = document.get("accountName").toString()
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