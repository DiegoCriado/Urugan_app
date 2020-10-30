package com.example.ui

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment() {

    private lateinit var navigationController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAppTitle()

        lifecycleScope.launch {
            delay(3000)
            progressBar.visibility = View.GONE
            title_tx.visibility = View.GONE
            sub_title_tx.visibility = View.GONE
           findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

    }

    fun setAppTitle(){
        title_tx.typeface = ResourcesCompat.getFont(requireContext(),
            R.font.title_one
        )
        title_tx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40.toFloat())
    }

}