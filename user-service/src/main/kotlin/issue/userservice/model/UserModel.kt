package issue.userservice.model

import issue.userservice.domain.entity.User
import java.time.LocalDateTime

data class MeResponse(
    val id: Long,
    val email: String,
    val username: String,
    val profileUrl: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {
    companion object {
        operator fun invoke(user: User) = with(user) {
            MeResponse(
                id = id!!,
                email = email,
                username = username,
                profileUrl = profileUrl,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )
        }
    }
}

data class UserEditRequest(
    val username: String,
)
