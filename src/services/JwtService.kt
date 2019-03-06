package jwt.auth.ktor.services

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import controllers.user.UserModel
import java.util.*

object JwtService {
    private const val secret = "someSecretKey"
    private const val issuer = "issuer"
    private const val validityInMs = 36_000_00  // 1 hours
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun genToken(user: UserModel): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.id)
        .withClaim("name", user.name)
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}