package io.memorix.user

import io.ktor.util.logging.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository(
    val database: Database
) {

    object Users : Table() {
        val id = integer("id").autoIncrement()
        val name = varchar("name", length = 50)
        val email = varchar("email", length = 50)
        val password = varchar("password", length = 50)
        override val primaryKey = PrimaryKey(id)
    }

    val logger = KtorSimpleLogger("repo")

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    private val users = mutableListOf(
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),
        User("T1", "t@r.bl", "Fiets12!"),

        )

    fun allUsers(query: String, limit: Int): List<User> {
        return Users.selectAll().map { User(it[Users.name], it[Users.email], it[Users.password]) }

    }

    fun allUsers(): List<User> = users

    fun addUser(user: User) {

        Users.insert {
            it[name] = user.name
            it[email] = user.email
            it[password] = user.password
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
    // Add your repository methods here
}
