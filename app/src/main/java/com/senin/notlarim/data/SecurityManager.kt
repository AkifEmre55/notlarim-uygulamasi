package com.senin.notlarim.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.MessageDigest

/**
 * PIN kodunu telefonun donanım destekli şifreleme anahtarıyla
 * korunan EncryptedSharedPreferences içine, düz metin değil
 * SHA-256 hash olarak kaydeder.
 */
class SecurityManager(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "notlarim_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun isPinSet(): Boolean = prefs.contains("pin_hash")

    fun setPin(pin: String) {
        prefs.edit().putString("pin_hash", hash(pin)).apply()
    }

    fun checkPin(pin: String): Boolean {
        val saved = prefs.getString("pin_hash", null) ?: return false
        return saved == hash(pin)
    }

    private fun hash(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
