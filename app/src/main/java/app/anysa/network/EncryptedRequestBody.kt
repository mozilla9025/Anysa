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
//        logd("EncryptedRequestBody  $jsonBody")

//        val jsonBody = "{\"loginname\":\"13322221313\", \"password\":\"d8578edf8458ce06fbc5bb76a58c5ca4\", \"device\":\"mobile\"}"
//
//        val keyFromServer = "-----BEGIN PUBLIC KEY-----\n" +
//                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVt2zuhHAMejV8syGsImaEwiME\n" +
//                "0hpUpHBWBz0ZGwG11aHollAuOjUEMxpVe85mii5ErGWILgBJ6wFNA5cJrshhrpz7\n" +
//                "EzoWVXR/FUZAvbQ0Y9GuxsXUNS7ZYKqyGmGwiMYdLSFVGltv6Gu5OoC8OVyWWgiF\n" +
//                "sb054PmHf0p5P3JBHwIDAQAB\n" +
//                "-----END PUBLIC KEY-----"

        val randomPrivateKey = CryptoUtils.getRandomString(8)
        this.encryptKey = RsaEncryptor.encrypt(randomPrivateKey, keyFromServer)
        this.encryptedBody = AESencryptor.encrypt(randomPrivateKey.md5(), jsonBody)!!
    }
}