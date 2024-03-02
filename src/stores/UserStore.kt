package stores

import helpers.FileBase
import helpers.Model
import models.User
import java.io.File

object UserStore: FileBase() {
    override val file = File("resources/users.txt")
    val users = data as ArrayList<User>

    init {
        baseInit()
    }

    override fun lineToModel(data: List<String>): Model {
        return User(data[0], data[1])
    }

    override fun modelToLine(model: Model): String {
        val user = model as User
        return "${user.username},${user.password}"
    }
}