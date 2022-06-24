package com.example.imageuploadtest.data.network

sealed class NetworkError(override val message: String?) : Throwable(message) {
    class NotFound : NetworkError("Network Error: Not Found")
    class AccessDenied : NetworkError("Network Error: Access Denied")
    class ServiceUnavailable : NetworkError("Network Error: Service Unavailable")
    class EmptyBody : NetworkError("Network Error: Empty Body")
    class Unknown : NetworkError("Network Error: Unknown")
}

fun Int.toNetworkError() = when (this) {
    404 -> NetworkError.NotFound()
    401, 406 -> NetworkError.AccessDenied()
    500 -> NetworkError.ServiceUnavailable()
    else -> NetworkError.Unknown()
}