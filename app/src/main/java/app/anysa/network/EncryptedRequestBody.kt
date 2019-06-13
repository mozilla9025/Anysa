package app.anysa.network

import android.os.Parcelable
import app.anysa.crypto.*
import app.anysa.util.extensions.logd
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.OutputStream

class EncryptedRequestBody(request: Parcelable, keyFromServer: String) {

    @SerializedName("c")
    var encryptedBody: String
    @SerializedName("p")
    var encryptKey: String

    init {
        val gson = Gson()
        val jsonBody = gson.toJson(request)
        logd("EncryptedRequestBody: $jsonBody")

        val randomPrivateKey = CryptoUtils.getRandomString(8)
        this.encryptKey = RsaEncryptor.encrypt(randomPrivateKey, keyFromServer)
        this.encryptedBody = AESencryptor.encrypt(randomPrivateKey.md5(), jsonBody)!!
    }
}