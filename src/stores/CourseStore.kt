package stores

import helpers.FileBase
import helpers.Model
import models.Course
import java.io.File


object CourseStore: FileBase() {
    override val file = File("resources/courses.txt")
    val courses = data as ArrayList<Course>

    init {
        baseInit()
    }

    override fun lineToModel(data: List<String>): Model {
        return Course(data[0], ArrayList(data[1].split("|")))
    }

    override fun modelToLine(model: Model): String {
        val course = model as Course
        return "${course.name},${course.students_ids.joinToString("|")}"
    }
}