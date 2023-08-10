package issue.issueservice.model

import issue.issueservice.domain.Comment

data class CommentRequest(
    val body: String,
)

data class CommentResponse(
    val id: Long,
    val userId: Long,
    val issueId: Long,
    val username: String? = null,
    val body: String,
)

fun Comment.toResponse() =
    with(this) {
        CommentResponse(
            id = id!!,
            userId = userId,
            issueId = issue.id!!,
            username = username,
            body = body,
        )
    }
