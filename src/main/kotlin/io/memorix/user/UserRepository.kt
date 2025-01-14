package io.memorix.user

import org.jetbrains.exposed.sql.Database

class UserRepository(
    val database: Database
) {

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

    fun allUsers():List<User> = users

    fun addUser(user: User) {
        //password encryption Bcrypt??

        users.add(user)
    }
    // Add your repository methods here
}
