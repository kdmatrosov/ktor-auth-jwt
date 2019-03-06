package controllers.user

import io.ktor.auth.Principal

class UserModel(val id: String, val name: String) : Principal