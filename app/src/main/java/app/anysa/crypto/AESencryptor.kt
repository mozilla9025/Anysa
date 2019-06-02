package app.anysa.crypto

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.security.MessageDigest


class AESencryptor {
    private enum class EncryptMode {
        ENCRYPT, DECRYPT
    }

    companion object {
//        private fun encryptDecrypt(inputText: String, encryptionKey: String,
//                                   mode: EncryptMode, initVector: String): String {
//            var out = ""
//            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
//            val key = ByteArray(16) //256 bit key space
//            val iv = ByteArray(16) //128 bit IV
//            var len = encryptionKey.toByteArray(charset("UTF-8")).size // length of the key	provided
//
//            if (encryptionKey.toByteArray(charset("UTF-8")).size > key.size)
//                len = key.size
//
//            var ivlen = initVector.toByteArray(charset("UTF-8")).size
//
//            if (initVector.toByteArray(charset("UTF-8")).size > iv.size)
//                ivlen = iv.size
//
//            System.arraycopy(encryptionKey.toByteArray(charset("UTF-8")), 0, key, 0, len)
//            System.arraycopy(initVector.toByteArray(charset("UTF-8")), 0, iv, 0, ivlen)
//
//            val keySpec = SecretKeySpec(key, "AES") // Create a new SecretKeySpec
//
//            val ivSpec = IvParameterSpec(iv)
//            if (mode.equals(EncryptMode.ENCRYPT)) {
//                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
//                val results = cipher.doFinal(inputText.toByteArray(charset("UTF-8"))) // Finish
//                out = Base64.encodeToString(results, Base64.DEFAULT) // ciphertext
//            }
//
//            if (mode.equals(EncryptMode.DECRYPT)) {
//                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)// Initialize this ipher instance
//
//                val decodedValue = Base64.decode(inputText.toByteArray(), Base64.DEFAULT)
//                val decryptedVal = cipher.doFinal(decodedValue) // Finish
//                out = String(decryptedVal)
//            }
//            return out
//        }
//
//        fun SHA256(text: String, length: Int): String {
//
//            val resultStr: String
//            val md = MessageDigest.getInstance("SHA-256")
//
//            md.update(text.toByteArray(charset("UTF-8")))
//            val digest = md.digest()
//
//            val result = StringBuffer()
//            for (b in digest) {
//                result.append(String.format("%02x", b)) //convert to hex
//            }
//
//            if (length > result.toString().length) {
//                resultStr = result.toString()
//            } else {
//                resultStr = result.toString().substring(0, length)
//            }
//
//            return resultStr
//        }
//
//        fun encrypt(plainText: String, key: String, iv: String): String {
//            return encryptDecrypt(plainText, key, EncryptMode.ENCRYPT, iv)
//        }
//
//        fun encryptSimple(plainText: String, key: String, iv: String): String {
//            return encryptDecrypt(plainText, SHA256(key, 32), EncryptMode.ENCRYPT, iv)
//        }
//
//        fun decrypt(encryptedText: String, key: String, iv: String): String {
//            return encryptDecrypt(encryptedText, key, EncryptMode.DECRYPT, iv)
//        }
//
//        fun decryptSimple(encryptedText: String, key: String, iv: String): String {
//            return encryptDecrypt(encryptedText, SHA256(key, 32), EncryptMode.DECRYPT, iv)
//        }


        fun encrypt(string: String, key: String): String {
//            val ivKey: String = "0000000000000000"
//            var data = string.toByteArray(Charsets.UTF_8)
//            val md5Key = key.md5()
//            var dataLength = data.size
//            dataLength = if (dataLength % 16 != 0) (dataLength / 16 + 1) * 16 else dataLength
//
//            data.size = dataLength
//
//
//            var encrypted = [UInt8]()
//            do {
//                encrypted = try AES(key: Array(md5Key.utf8), blockMode: CBC(iv: Array(ivKey.utf8)), padding: .noPadding).encrypt(Array(data))
//                } catch {
//                    print(error)
//                    return nil
//                }
//
//                return encrypted.toBase64()


            val secretKeySpec = SecretKeySpec(key.toByteArray(), "AES")
            val iv = ByteArray(16)
            val charArray = key.toCharArray()

            for (i in 0 until charArray.size - 1) {
                iv[i] = charArray[i].toByte()
            }
            val ivParameterSpec = IvParameterSpec(iv)

            val cipher = Cipher.getInstance("AES/CBC/NoPadding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

            val encryptedValue = cipher.doFinal(string.toByteArray())
            return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
        }
    }
}