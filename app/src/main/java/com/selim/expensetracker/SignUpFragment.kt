package com.selim.expensetracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController


class SignUpFragment : Fragment() {

    class mockUser(
        var name:String,
        var email:String,
        var password:String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_sign_up, container, false)


        /*
        view.findViewById<TextView>(R.id.loginGuide).setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

         */
        var user=mockUser("selim","selim@gmail.com","12345")


        view.findViewById<Button>(R.id.signUpButton).setOnClickListener {
            var nameTxt=view.findViewById<EditText>(R.id.signUpTextPersonName).text.toString()
            var mailTxt=view.findViewById<EditText>(R.id.signUpTextPersonMail).text.toString()
            var passwordTxt=view.findViewById<EditText>(R.id.signUpTextPassword).text.toString()
            if(nameTxt==user.name)
            {
                Toast.makeText(requireContext(),"Kayıt Başarılı",Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
            }
        }




        return view

    }

}