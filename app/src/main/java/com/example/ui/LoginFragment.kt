package com.example.ui

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BottomNavigationActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_welcome.title_tx

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAppTitle()

        forgot_username_ly.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotUsernameFragment)
        }

        forgot_password_ly.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        //Login-logic
        login_btn.setOnClickListener {

        }

        register_btn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        singIn_without_login.setOnClickListener {
            val intent = Intent(activity, BottomNavigationActivity::class.java)
            startActivity(intent)
        }

    }


    fun setAppTitle(){
        title_tx.typeface = ResourcesCompat.getFont(requireContext(),
            R.font.title_one)
        title_tx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40.toFloat())
    }


}