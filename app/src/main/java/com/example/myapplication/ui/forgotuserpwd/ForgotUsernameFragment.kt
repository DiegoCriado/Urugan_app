package com.example.myapplication.ui.forgotuserpwd

import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentForgotUsernameBinding
import java.util.regex.Pattern


class ForgotUsernameFragment : Fragment() {

    private lateinit var binding: FragmentForgotUsernameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_username, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotUsernameBinding.bind(view)
        setAppTitle()

        binding.recoverUsernameBtn.setOnClickListener {
            var email = binding.emailNewUserTx.text.toString()
            var password = binding.forgotUsernamePwdTx.text.toString()

            if (!isEmailValid(email = email)) {
                Toast.makeText(context, "Escriba un email valido", Toast.LENGTH_SHORT).show()
            } else if (!isValidPassword(password)) {
                Toast.makeText(context, "Contrasenia incorrecta", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Se ha enviado un correo con su nuevo usuario", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun isValidPassword(s: String?): Boolean {
        val PASSWORD_PATTERN: Pattern = Pattern.compile(
                "[a-zA-Z0-9!@#$]{8,24}")
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s!!).matches()
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun setAppTitle() {
        binding.titleTx.typeface = ResourcesCompat.getFont(requireContext(),
                R.font.title_one)
        binding.titleTx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40.toFloat())
    }

}