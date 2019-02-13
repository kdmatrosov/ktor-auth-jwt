package jwt.auth.ktor.repositories

import jwt.auth.ktor.models.UserModel

object UserRepository : Repository {
    fun getUserById(id: String): UserModel {
        return UserModel(id = "id", name = "name")
    }
}