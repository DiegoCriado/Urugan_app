package com.example.ui

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getMainExecutor
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BottomNavigationActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_welcome.title_tx
import java.util.concurrent.Executor

class LoginFragment : Fragment() {

    private var cancellationSignal :CancellationSignal? = null
    private val authenticationCallback : BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notifyUser("Algo esta mal $errorCode")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    notifyUser("Ingreso con exitoso!")
                    startActivity(Intent(activity, BottomNavigationActivity::class.java))
                }
            }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




       // getCancellationSignal()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAppTitle()

        //chequea si el device tiene soporte de biometric
        checkBiometricSupport()

        //biometric
        fingerprint_ly.setOnClickListener {
            val biometricPrompt  = BiometricPrompt.Builder(context)
                .setTitle("Huella digital")
                .setSubtitle("Ingrese su huella digital para ingresar sesion")
                .setDescription("Coloque su dedo en el sensor")
                .setNegativeButton("Cancel", getMainExecutor(context), DialogInterface.OnClickListener { dialogInterface, which ->
                    notifyUser("Autenticacion de huella digital cancelada")
                }).build()

            biometricPrompt.authenticate(getCancellationSignal(), getMainExecutor(context), authenticationCallback)
        }

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

    private fun notifyUser(message : String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCancellationSignal() : CancellationSignal{
        cancellationSignal = CancellationSignal()
        cancellationSignal!!.setOnCancelListener {
            notifyUser("EL usuario cancelo la auteticacion")
        }
        return cancellationSignal as CancellationSignal
    }

    private fun checkBiometricSupport(): Boolean {
       val keyguardManager = requireContext().getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if(!keyguardManager.isKeyguardLocked){
            notifyUser("Puede autenticarse con huella digital")
            return false
        }

        if(ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED){
            notifyUser("La autenticacion con huella dactilar no esta permitida")
        return false
        }

        return if (requireContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        }else true
    }

    fun setAppTitle(){
        title_tx.typeface = ResourcesCompat.getFont(requireContext(),
            R.font.title_one)
        title_tx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40.toFloat())
    }



}