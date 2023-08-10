package issue.issueservice.service

import issue.issueservice.domain.Issue
import issue.issueservice.domain.IssueRepository
import issue.issueservice.domain.enums.IssueStatus
import issue.issueservice.exception.NotFoundException
import issue.issueservice.model.IssueRequest
import issue.issueservice.model.IssueResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(
    private val issueRepository: IssueRepository,
) {

    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        val issue = Issue(
            userId = userId,
            summary = request.summary,
            description = request.description,
            type = request.type,
            priority = request.priority,
            status = request.status,
        )
        return IssueResponse(issueRepository.save(issue))
    }

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map { IssueResponse(it) }

    @Transactional(readOnly = true)
    fun get(id: Long) : IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("Issue Is Not Found")
        return IssueResponse(issue)
    }

    @Transactional
    fun edit(userID: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("Issue Is Not Found")

        return with(issue) {
            this.userId = userID
            summary = request.summary
            description = request.description
            type = request.type
            priority = request.priority
            status = request.status

            IssueResponse(issueRepository.save(this))
        }
    }

    fun delete(id: Long) {
        issueRepository.deleteById(id)
    }
}
