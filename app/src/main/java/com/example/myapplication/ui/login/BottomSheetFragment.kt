package com.example.myapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_fragment.*

class BottomSheetFragment :BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_payment_btn.setOnClickListener {
            Toast.makeText(context, "Metodo de pago agregado", Toast.LENGTH_LONG).show()
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = BottomSheetFragment()
    }
}