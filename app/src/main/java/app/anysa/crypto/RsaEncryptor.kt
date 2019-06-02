package app.anysa.crypto

import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import android.util.Base64
import app.anysa.util.extensions.logd
import java.nio.charset.StandardCharsets
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import javax.crypto.Cipher


class RsaEncryptor {

    companion object {

        const val CRYPTO_METHOD = "RSA"
        const val CRYPTO_TRANSFORM = "RSA/ECB/PKCS1Padding"

        fun encrypt(key: String, publicKey: String): String {
            val clearPubKey = publicKey.replace("-----BEGIN PUBLIC KEY-----\n", "").replace("\n-----END PUBLIC KEY-----", "")
            logd("clearPubKey:::  $clearPubKey")

            val encryptedBytes: ByteArray
            val pubKey: PublicKey? = clearPubKey.toPublicKey()
            val cipher: Cipher = Cipher.getInstance(CRYPTO_TRANSFORM)

            cipher.init(Cipher.ENCRYPT_MODE, pubKey)
            encryptedBytes = cipher.doFinal(key.toByteArray(StandardCharsets.UTF_8))

            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
        }


        fun String.toPublicKey(): PublicKey {
            val keyBytes: ByteArray = Base64.decode(this, Base64.DEFAULT)
            val spec = X509EncodedKeySpec(keyBytes)
            val keyFactory = KeyFactory.getInstance(CRYPTO_METHOD)

            return keyFactory.generatePublic(spec)
        }

        fun String.toPrivateKey(): PrivateKey {
            val keyBytes: ByteArray = Base64.decode(this, Base64.DEFAULT)
            val spec = PKCS8EncodedKeySpec(keyBytes)
            val keyFactory = KeyFactory.getInstance(CRYPTO_METHOD)

            return keyFactory.generatePrivate(spec)
        }
    }
}