package app.anysa.domain.pojo

class ApiResponse<out T> private constructor(val status: Status, val data: T?, val exception: Throwable?) {

    enum class Status { SUCCESS, ERROR, LOADING }

    companion object {
        fun <T> success(data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable? = null): ApiResponse<T> {
            return ApiResponse(Status.ERROR, null, exception)
        }

        fun <T> loading(data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.LOADING, data, null)
        }

        fun <T> loading(): ApiResponse<T> {
            return ApiResponse(Status.LOADING, null, null)
        }
    }
}
