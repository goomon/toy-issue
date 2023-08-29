package issue.userservice.service

import issue.userservice.domain.entity.User
import issue.userservice.domain.repository.UserRepository
import issue.userservice.exception.UserExistsException
import issue.userservice.model.SingUpRequest
import issue.userservice.utils.BCryptUtils
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    suspend fun signUp(signUpRequest: SingUpRequest) {
        with(signUpRequest) {
            userRepository.findByEmail(email)?.let {
                throw UserExistsException()
            }

            val user = User(
                email = email,
                password = BCryptUtils.hash(password),
                username = username,
            )
            userRepository.save(user)
        }
    }
}