package issue.userservice.utils

import issue.userservice.config.JWTProperties
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JWTUtilsTest {

    private val logger = KotlinLogging.logger {}

    @Test
    fun createToken() {
        val jwtClaim = JWTClaim(
            userId = 1,
            email = "test@gmail.com",
            profileUrl = "profile.jpg",
            username = "admin",
        )

        val jwtProperties = JWTProperties(
            issuer = "admin",
            subject = "auth",
            expiresTime = 3600,
            secret = "my-secret",
        )

        val token = JWTUtils.createToken(jwtClaim, jwtProperties)
        assertNotNull(token)

        logger.info { "token: $token" }
    }

    @Test
    fun decodeTest() {
        val jwtClaim = JWTClaim(
            userId = 1,
            email = "test@gmail.com",
            profileUrl = "profile.jpg",
            username = "admin",
        )

        val jwtProperties = JWTProperties(
            issuer = "admin",
            subject = "auth",
            expiresTime = 3600,
            secret = "my-secret",
        )

        val token = JWTUtils.createToken(jwtClaim, jwtProperties)

        val decode = JWTUtils.decode(token, secret = jwtProperties.secret, issuer = jwtProperties.issuer)
        with(decode) {
            logger.info { "claims: $claims" }

            val userId = claims["userId"]!!.asLong()
            assertEquals(userId, jwtClaim.userId)

            val email = claims["email"]!!.asString()
            assertEquals(email, jwtClaim.email)

            val profileUrl = claims["profileUrl"]!!.asString()
            assertEquals(profileUrl, jwtClaim.profileUrl)

            val username = claims["username"]!!.asString()
            assertEquals(username, jwtClaim.username)
        }
    }
}