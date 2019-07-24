package app.anysa.network

import android.os.Parcelable
import app.anysa.crypto.AESencryptor
import app.anysa.crypto.CryptoUtils
import app.anysa.crypto.RsaEncryptor
import app.anysa.crypto.md5
import app.anysa.util.extensions.logd
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class EncryptedRequestBody(request: Parcelable, keyFromServer: String = "",
                           method: Method = Method.ENCRYPT_AUTH,
                           session: String = "", password: String = "") {

    enum class Method {
        ENCRYPT_AUTH, ENCRYPT_MAIN
    }

    @SerializedName("c")
    var encryptedBody: String
    @SerializedName("p")
    var encryptKey: String

    init {
        val gson = Gson()
        val jsonBody = gson.toJson(request)
        logd("EncryptedRequestBody: $jsonBody")

        when (method) {
            Method.ENCRYPT_AUTH -> {
                val randomPrivateKey = CryptoUtils.getRandomString(8)
                this.encryptKey = RsaEncryptor.encrypt(randomPrivateKey, keyFromServer)
                this.encryptedBody = AESencryptor.encrypt(randomPrivateKey.md5(), jsonBody)!!
            }
            Method.ENCRYPT_MAIN -> {
                this.encryptKey = session
                this.encryptedBody = AESencryptor.encrypt(password.md5(), jsonBody)!!
            }
        }
    }
}