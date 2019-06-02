package app.anysa.network

import android.os.Parcelable
import app.anysa.crypto.*
import app.anysa.util.extensions.logd
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.OutputStream

class EncryptedRequestBody(request: Parcelable, keyFromServer: String) {

    @SerializedName("c")
    private var encryptedBody: String
    @SerializedName("p")
    private var encryptKey: String

    init {
        logd("EncryptedRequestBody  $request     $keyFromServer")
        val gson = Gson()
        val jsonBody = gson.toJson(request)
        logd("EncryptedRequestBody  $jsonBody")

        val randomPrivateKey = CryptoUtils.getRandomString(8)



        this.encryptKey = RsaEncryptor.encrypt(randomPrivateKey, keyFromServer)
        this.encryptedBody = AES.encrypt(jsonBody, randomPrivateKey.md5())!!

        logd("EncryptedRequestBody  $encryptKey     $encryptedBody")
        encryptedBody = "j6dKKYdc3bLkncrAYxdDtbENwCQrAbo9QiWVLbsqXZqUxeZTAtMtc8IGOnYk/b8kaUxCqRa/nFydEG/qQQEkSVIgPILcEinlbMbmCmA7htW2IOtHpi3aATOiAGX0RcQo7ldi0t16WI0KHkdZ9BhJ+L1o8+jURBOltwGU4raxoTTWH/HoMMJix43jiVfY7FIspf0DbTFArTeozchHaTwDtHofrmLriyUO9r0ZGP29avkTiBZ+ydnNJYN4sSLyzmiw"
        encryptKey = "KjPcKWyobv0Gcp3g8WrUvPx5Y/9C9tPXS2NSzOYWXR4AlznStqlNcXOv5w0gd/jS0KEr6L6ZmupnXHbpAr+TA6lv4GgE3X+JhznvnyeDZxt6wGl6Voni8tQEqkmUhryFP3EC67fGfHvPDkTY91Yv8rackXGkDaDJvCqAYslNkcU="
    }
}