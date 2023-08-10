package issue.issueservice.domain

import jakarta.persistence.*
import org.hibernate.annotations.ManyToAny

@Entity
@Table
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var userId: Long,

    @ManyToOne
    @JoinColumn(name = "ISSUE_ID")
    var issue: Issue,

    @Column
    var username: String,

    @Column(length = 250)
    var body: String,
) : BaseEntity()

