package app.anysa.crypto

import android.util.Base64
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec

class KeyGen {

    enum class Algorithm {
        AES,
        RSA
    }

    companion object {
        fun getRsaPubKeyFromString(publicKey: String): RSAPublicKey {
            val localPK = publicKey.replace("\\n", "")
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")

            val kf = KeyFactory.getInstance("RSA")

            val keySpecX509 = X509EncodedKeySpec(Base64.decode(localPK, Base64.DEFAULT))
            return kf.generatePublic(keySpecX509) as RSAPublicKey
        }

        fun generate(algorithm: Algorithm) {
            TODO("implement method")
        }
    }
}