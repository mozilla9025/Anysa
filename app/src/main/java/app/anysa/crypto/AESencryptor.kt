package app.anysa.crypto

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.security.MessageDigest


class AESencryptor {
    companion object {
        fun encrypt(key: String, value: String): String? {
            try {
                val initVector = "0000000000000000"
                val iv = IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
                val skeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")

                val cipher = Cipher.getInstance("AES/CBC/NoPadding")
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)

                val toByteArray = value.toByteArray()
                var dataLength = toByteArray.size
                dataLength = if (dataLength % 16 != 0) (dataLength / 16 + 1) * 16 else dataLength
                val newBytesArray = ByteArray(dataLength)
                toByteArray.forEachIndexed { index, byte -> newBytesArray[index] = byte }

                val encrypted = cipher.doFinal(newBytesArray)
                System.out.println("encrypted string: " + Base64.encodeToString(encrypted, Base64.DEFAULT))

                return Base64.encodeToString(encrypted, Base64.DEFAULT)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return null
        }

    }
}
