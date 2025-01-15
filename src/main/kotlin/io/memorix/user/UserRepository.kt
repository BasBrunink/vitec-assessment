package io.memorix.user

import org.jetbrains.exposed.sql.Database

class UserRepository(
    val database: Database
) {

    private val users = mutableListOf(
        User("Bas", "t@r.bl", "Fiets12!"),
        User("Maaike", "t@r.bl", "Fiets12!"),
        User("Gwen", "t@r.bl", "Fiets12!"),
        User("Emily", "t@r.bl", "Fiets12!"),
        User("Ruud", "t@r.bl", "Fiets12!"),
        User("Rini", "t@r.bl", "Fiets12!"),
        User("Bennie", "t@r.bl", "Fiets12!"),
        User("Hetty", "t@r.bl", "Fiets12!"),
        User("Stef", "t@r.bl", "Fiets12!"),
        User("Suus", "t@r.bl", "Fiets12!"),
        User("admin", "t@r.bl", "Fiets12!"),

    )

    fun allUsers():List<User> = users

    fun addUser(user: User) {
        //password encryption Bcrypt??

        users.add(user)
    }
    // Add your repository methods here
}
