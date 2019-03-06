package jwt.auth.ktor.payloads

data class AuthPayload(val login:String, val password:String)