package jwt.auth.ktor

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.authenticate
import io.ktor.auth.jwt.jwt
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.request.path
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import controllers.user.UserModel
import jwt.auth.ktor.payloads.AuthPayload
import controllers.user.UserRepository
import jwt.auth.ktor.services.JwtService
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) { gson { } }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(Authentication) {
        jwt {
            verifier(JwtService.verifier)
            realm = "realm"
            validate {
                it.payload.getClaim("id").asString()?.let { id ->
                    UserRepository.getUserById(id)
                }
            }
        }
    }

    routing {
        post("auth") {
            println(call.receive<AuthPayload>())
            //get user from DB
            //TODO return token
            call.respondText(
                JwtService.genToken(UserModel(id = "id", name = "name")),
                contentType = ContentType.Text.Plain
            )
        }

        //TODO remove this
        get("token") {
            call.respondText(
                JwtService.genToken(UserModel(id = "id", name = "name")),
                contentType = ContentType.Text.Plain
            )
        }

        authenticate {
            get("data") {
                call.respondText("it's a secret")
            }
        }
    }
}
