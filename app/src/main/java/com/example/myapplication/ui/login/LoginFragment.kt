package com.example.myapplication.ui.login

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build

import android.os.Bundle
import android.os.CancellationSignal
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getMainExecutor
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BottomNavigationActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_welcome.title_tx
import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    //initialization of viewBinding
    lateinit var binding: FragmentLoginBinding

    lateinit var username : String

    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        setAppTitle()

        //chequea si el device tiene soporte de biometric
        checkBiometricSupport()

        //biometric
        binding.fingerprintLy.setOnClickListener {
            val biometricPrompt = BiometricPrompt.Builder(context)
                    .setTitle("Huella digital")
                    .setSubtitle("Ingrese su huella digital para ingresar sesion")
                    .setDescription("Coloque su dedo en el sensor")
                    .setNegativeButton("Cancel", getMainExecutor(context), DialogInterface.OnClickListener { dialogInterface, which ->
                        notifyUser("Autenticacion de huella digital cancelada")
                    }).build()
            biometricPrompt.authenticate(getCancellationSignal(), getMainExecutor(context), authenticationCallback)
        }

        binding.forgotUsernameLy.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotUsernameFragment)
        }

        binding.forgotPasswordLy.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        //Login-btn-logic
        binding.loginBtn.setOnClickListener {

            var user = username_login_tx.text.toString()
            var password = password_login_tx.text.toString()

            if (user.isNullOrEmpty()) {
                Toast.makeText(context, "Ingrese usuario valido", Toast.LENGTH_SHORT).show()
            } else if (!isValidPassword(password)) {
                Toast.makeText(context, "Contrasenia invalida", Toast.LENGTH_SHORT).show()
            } else {
                binding.usernameLoginTx.setText("")
                binding.passwordLoginTx.setText("")

               // findNavController().navigate(R.id.action_loginFragment_to_mobile_navigation2)
                val intent = Intent(activity, BottomNavigationActivity::class.java)
                startActivity(intent)
            }
        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.singInWithoutLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mobile_navigation2)
//            val intent = Intent(activity, BottomNavigationActivity::class.java)
//            startActivity(intent)
        }

    }


    //Biometric functions
    private fun notifyUser(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal!!.setOnCancelListener {
            notifyUser("EL usuario cancelo la auteticacion")
        }
        return cancellationSignal as CancellationSignal
    }

    private fun checkBiometricSupport(): Boolean {
        val keyguardManager = requireContext().getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyguardManager.isKeyguardLocked) {
            notifyUser("Puede autenticarse con huella digital")
            return false
        }

        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            notifyUser("La autenticacion con huella dactilar no esta permitida")
            return false
        }

        return if (requireContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }

    private fun isValidPassword(s: String?): Boolean {
        val PASSWORD_PATTERN: Pattern = Pattern.compile(
                "[a-zA-Z0-9!@#$]{8,24}")
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s!!).matches()
    }

    fun setAppTitle() {
        title_tx.typeface = ResourcesCompat.getFont(requireContext(),
                R.font.title_one)
        title_tx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40.toFloat())
    }

    fun String.sha256(): String {
        val md = MessageDigest.getInstance("SHA-256")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }


}