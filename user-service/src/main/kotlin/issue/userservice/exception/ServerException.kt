package issue.userservice.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message)

data class UserExistsException(
    override val message: String = "That User Id Has Already Been Used"
) : ServerException(409, message)

