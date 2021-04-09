package com.example.myapplication.ui.registration

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRegistrationBinding
import com.example.myapplication.ui.login.BottomSheetFragment
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_welcome.title_tx

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
        setAppTitle()

        val bottomSheetFragment = BottomSheetFragment.newInstance()

        binding.choosePaymentMethodBtn.setOnClickListener {
            bottomSheetFragment.show(parentFragmentManager, "")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val bottomSheetFragment = BottomSheetFragment()

        choose_payment_method_btn.setOnClickListener {

        }

    }


    fun setAppTitle() {
        binding.titleTx.typeface = ResourcesCompat.getFont(requireContext(),
                R.font.title_one)
        binding.titleTx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40.toFloat())
    }
}