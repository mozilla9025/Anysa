package app.anysa.util.extensions

fun String.replaceExtraSpaces(): String {
    return this.trim { it <= ' ' }.trim { it <= ' ' }.replace(" +".toRegex(), " ")
}