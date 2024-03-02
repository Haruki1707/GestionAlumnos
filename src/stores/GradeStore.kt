package stores

import helpers.FileBase
import helpers.Model
import models.Grade
import java.io.File

object GradeStore: FileBase() {
    override val file = File("resources/grades.txt")
    val grades = data as ArrayList<Grade>

    init {
        baseInit()
    }

    override fun lineToModel(data: List<String>): Model {
        return Grade(data[0].toInt(), data[1].toInt(), data[2].toDouble())
    }

    override fun modelToLine(model: Model): String {
        val grade = model as Grade
        return "${grade.student_id},${grade.assessment_id},${grade.grade}"
    }
}