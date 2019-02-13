package jwt.auth.ktor.models

import io.ktor.auth.Principal

class UserModel(val id: String, val name: String) : Principal