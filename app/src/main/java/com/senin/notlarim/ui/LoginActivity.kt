package com.senin.notlarim.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.senin.notlarim.data.SecurityManager
import com.senin.notlarim.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var securityManager: SecurityManager
    private var pinBeingCreated: String? = null // ilk kurulumda "önce gir sonra tekrar gir" akışı için

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        securityManager = SecurityManager(this)

        if (securityManager.isPinSet()) {
            setupLoginMode()
        } else {
            setupPinCreationMode()
        }
    }

    // ---------- İlk kurulum: kullanıcı kendi PIN'ini belirliyor ----------
    private fun setupPinCreationMode() {
        binding.tvTitle.text = "Bir PIN Belirle"
        binding.tvSubtitle.text = "Notlarını korumak için 4-6 haneli bir PIN oluştur"
        binding.btnBiometric.visibility = android.view.View.GONE

        binding.btnConfirm.setOnClickListener {
            val entered = binding.etPin.text.toString()
            if (entered.length < 4) {
                Toast.makeText(this, "PIN en az 4 haneli olmalı", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pinBeingCreated == null) {
                // İlk giriş: PIN'i geçici tut, tekrar girmesini iste
                pinBeingCreated = entered
                binding.etPin.text?.clear()
                binding.tvSubtitle.text = "Aynı PIN'i onaylamak için tekrar gir"
            } else {
                // İkinci giriş: eşleşiyor mu kontrol et
                if (entered == pinBeingCreated) {
                    securityManager.setPin(entered)
                    Toast.makeText(this, "PIN oluşturuldu", Toast.LENGTH_SHORT).show()
                    goToNotes()
                } else {
                    Toast.makeText(this, "PIN'ler eşleşmedi, tekrar dene", Toast.LENGTH_SHORT).show()
                    pinBeingCreated = null
                    binding.etPin.text?.clear()
                    binding.tvSubtitle.text = "Notlarını korumak için 4-6 haneli bir PIN oluştur"
                }
            }
        }
    }

    // ---------- Normal giriş: PIN veya yüz/parmak izi ----------
    private fun setupLoginMode() {
        binding.tvTitle.text = "Hoş Geldin"
        binding.tvSubtitle.text = "Devam etmek için PIN gir ya da yüzünü göster"

        binding.btnConfirm.setOnClickListener {
            val entered = binding.etPin.text.toString()
            if (securityManager.checkPin(entered)) {
                goToNotes()
            } else {
                Toast.makeText(this, "PIN yanlış", Toast.LENGTH_SHORT).show()
                binding.etPin.text?.clear()
            }
        }

        val canUseBiometric = BiometricManager.from(this)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)

        if (canUseBiometric == BiometricManager.BIOMETRIC_SUCCESS) {
            binding.btnBiometric.visibility = android.view.View.VISIBLE
            binding.btnBiometric.setOnClickListener { showBiometricPrompt() }
        } else {
            // Telefonda yüz/parmak izi tanımlı değilse buton gizlenir,
            // kullanıcı Telefon Ayarları'ndan kendi yüzünü tanıtmalı.
            binding.btnBiometric.visibility = android.view.View.GONE
        }
    }

    private fun showBiometricPrompt() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    goToNotes()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(this@LoginActivity, errString, Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(this@LoginActivity, "Tanınmadı, tekrar dene", Toast.LENGTH_SHORT).show()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Kimliğini Doğrula")
            .setSubtitle("Yüzünü göster ya da parmak izini kullan")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)
            .setNegativeButtonText("PIN kullan")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun goToNotes() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
