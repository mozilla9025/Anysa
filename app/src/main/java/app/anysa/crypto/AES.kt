package app.anysa.crypto

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object AES {
    var AES_ALGORITHM = "AES"
    var AES_TRANSFORMATION = "AES/ECB/PKCS5Padding"


    private var secretKey: SecretKeySpec? = null
    private var key: ByteArray? = null

    fun setKey(myKey: String) {
        var sha: MessageDigest? = null
        try {
            key = myKey.toByteArray(charset("UTF-8"))
            sha = MessageDigest.getInstance("SHA-1")
            key = sha!!.digest(key)
            key = Arrays.copyOf(key, 16)
            secretKey = SecretKeySpec(key, AES_ALGORITHM)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

    fun encrypt(strToEncrypt: String, secret: String): String? {
        try {
            setKey(secret)
            val cipher = Cipher.getInstance(AES_TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            val inputBytes = cipher.doFinal(strToEncrypt.toByteArray())
            return android.util.Base64.encodeToString(inputBytes, android.util.Base64.DEFAULT)
        } catch (e: Exception) {
            println("Error while encrypting: $e")
        }

        return null
    }

    fun decrypt(strToDecrypt: String/*, String secret*/): String? {
        try {

            // setKey(secret); // this used to get encrypted key if doing data decryption without encrypted key knowledg
            // or if don't know encryption key used to encrypted data previously

            val cipher = Cipher.getInstance(AES_TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, secretKey)

//            val inputBytes = cipher.doFinal(android.util.Base64.decode(strToDecrypt))
            return null

        } catch (e: Exception) {
            println("Error while decrypting: $e")
        }

        return null
    }
}