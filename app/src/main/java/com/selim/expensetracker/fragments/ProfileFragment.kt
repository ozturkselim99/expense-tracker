package com.selim.expensetracker.fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.activities.AccountActivity
import com.selim.expensetracker.activities.CreateBudgetActivity
import com.selim.expensetracker.activities.ExportDataActivity
import com.selim.expensetracker.activities.NotificationActivity


class ProfileFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_profile, container, false)
        val bottomSheetDialog= BottomSheetDialog(requireContext())
        val bottomSheetDialogView=layoutInflater.inflate(R.layout.logout_bottom_sheet_modal,null)
        bottomSheetDialog.setContentView(bottomSheetDialogView)
        var dialog= Dialog(requireContext())

        view.findViewById<LinearLayout>(R.id.account).setOnClickListener {
            val intent= Intent(requireContext(), AccountActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<LinearLayout>(R.id.logout).setOnClickListener {
            bottomSheetDialog.show()
        }

        view.findViewById<LinearLayout>(R.id.exportData).setOnClickListener {
            val intent= Intent(requireContext(), ExportDataActivity::class.java)
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
            },1500)
        }



        return view

    }


}