package io.memorix.user

import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class ErrorResponse(val error: String)

@Serializable
data class AllUserResponse(val users:List<User>, val total: Int)

fun Route.user() {

    val repository: UserRepository by inject()
    // Add your routes here

    route("/users") {
        get {
            val query = call.parameters["query"]
            val limit = call.parameters["limit"]?.toIntOrNull()

            when {
                // Both query and limit are provided
                query != null && limit != null -> {
                    val filteredUsers = repository.allUsers().filter { it.name.contains(query) }
                    val limitedUsers = filteredUsers.take(limit)
                    call.respond(AllUserResponse(limitedUsers, limitedUsers.size))
                }

                // Only query is provided
                query != null -> {
                    val filteredUsers = repository.allUsers().filter { it.name.contains(query) }
                    call.respond(AllUserResponse(filteredUsers, filteredUsers.size))
                }
                limit != null -> {
                    val limitedUsers = repository.allUsers().take(limit)
                    call.respond(AllUserResponse(limitedUsers, limitedUsers.size))
                }

                // Neither query nor limit is provided
                else -> {
                    val allUsers = repository.allUsers()
                    call.respond(AllUserResponse(allUsers, allUsers.size))
                }
            }
        }
        post() {
            try {
                val request = call.receive<User>()
                val users = repository.allUsers()
                if(users.any{it.email == request.email}) {
                    call.respond(HttpStatusCode.BadRequest,ErrorResponse("Duplicate e-mail: ${request.email}"))
                }
                repository.addUser(User(request.name, request.email, BCrypt.hashpw(request.password,  BCrypt.gensalt())))
                call.respond(HttpStatusCode.Accepted)
            }
            catch (ex: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            }
            //TODO: Gek Ik zou zelf deze verwachten als de json niet correct is o
            catch (ex: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
            catch (ex: BadRequestException) {
                call.respond(HttpStatusCode.BadRequest)
            }

        }
    }
}
