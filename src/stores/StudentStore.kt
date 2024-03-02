package stores

import helpers.FileBase
import helpers.Model
import models.Student
import java.io.File

object StudentStore: FileBase() {
    override val file = File("resources/students.txt")
    val students = data as ArrayList<Student>

    init {
        baseInit()
    }

    override fun lineToModel(data: List<String>): Model {
        return Student(data[0])
    }

    override fun modelToLine(model: Model): String {
        return (model as Student).name
    }
}