package controllers.user

import jwt.auth.ktor.repositories.Repository

object UserRepository : Repository {
    fun getUserById(id: String): UserModel {
        return UserModel(id = "id", name = "name")
    }
}