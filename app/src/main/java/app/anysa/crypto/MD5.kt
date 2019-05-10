package app.anysa.crypto

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


fun String.md5(): String {
    val messageDigest: MessageDigest?
    var digest = ByteArray(0)

    try {
        messageDigest = MessageDigest.getInstance("MD5")
        messageDigest!!.reset()
        messageDigest.update(this.toByteArray())
        digest = messageDigest.digest()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

    val bigInt = BigInteger(1, digest)
    var md5Hex = bigInt.toString(16)

    while (md5Hex.length < 32) {
        md5Hex = "0$md5Hex"
    }
    return md5Hex
}