package issue.userservice.model

data class SingUpRequest(
    val email: String,
    val password: String,
    val username: String,
)

data class SignInRequest(
    val email: String,
    val password: String,
)

data class SignInResponse(
    val email: String,
    val username: String,
    val token: String,
)
