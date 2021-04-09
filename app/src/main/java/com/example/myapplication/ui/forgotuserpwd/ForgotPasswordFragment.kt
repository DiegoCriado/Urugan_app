package com.example.myapplication.ui.forgotuserpwd

import android.os.Binder
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentForgotPasswordBinding
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.fragment_welcome.title_tx

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPasswordBinding.bind(view)
        setAppTitle()

        binding.recoverUsernameBtn.setOnClickListener {

            var email = binding.newEmailTx.text.toString()

            if (!isEmailValid(email = email)) {
                Toast.makeText(context, "Escriba un email valido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Se le ha enviado un correo a su nueva cuenta", Toast.LENGTH_SHORT).show()
            }
        }

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