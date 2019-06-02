package app.anysa.crypto

import app.anysa.util.extensions.logd
import kotlin.random.Random

class CryptoUtils {

    companion object {
        fun getRandomString(length: Int): String {
            val charPool = 'A'..'Z'
            val randomString = (1..length)
                    .map { i -> Random.nextInt(0, charPool.count()) }
                    .map { x -> charPool.elementAt(x) }
                    .joinToString("")
            logd("randomString: $randomString")
            return randomString
        }
    }
}