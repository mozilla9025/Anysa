package app.anysa.network

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class EncryptedRequestBody {
    @SerializedName("c")
    private var requestJson: String
    @SerializedName("p")
    private var encryptKey: String

    constructor(requestJson: JsonObject, encryptKey: String) {
        this.requestJson = requestJson.asString
        this.encryptKey = encryptKey
    }
}