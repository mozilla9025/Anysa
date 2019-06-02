package app.anysa.domain.pojo

data class Token(val token: String, var tokenValidityInMillis: Long) {

    fun isEmpty() = this.token.isEmpty() && tokenValidityInMillis == EMPTY_INSTANCE_ID

    companion object {
        private const val EMPTY_INSTANCE_ID = -1996L
        var EMPTY_INSTANCE: Token = Token("", EMPTY_INSTANCE_ID)
    }
}