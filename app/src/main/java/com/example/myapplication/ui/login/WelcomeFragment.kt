package com.example.myapplication.ui.login

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
import com.example.myapplication.databinding.FragmentWelcomeBinding
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment() {

    private lateinit var binding : FragmentWelcomeBinding

    private lateinit var navigationController : NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)

        setAppTitle()

        lifecycleScope.launch {
            delay(3000)
            binding.progressBar.visibility = View.GONE
            binding.titleTx.visibility = View.GONE
            binding.subTitleTx.visibility = View.GONE
           findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

    }

    fun setAppTitle(){
        binding.titleTx.typeface = ResourcesCompat.getFont(requireContext(),
            R.font.title_one
        )
        binding.titleTx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40.toFloat())
    }

}