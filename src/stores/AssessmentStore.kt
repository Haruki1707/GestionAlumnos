package stores

import helpers.FileBase
import helpers.Model
import models.Assessment
import java.io.File


object AssessmentStore: FileBase() {
    override val file = File("resources/assessments.txt")
    val assessments = data as ArrayList<Assessment>

    init {
        baseInit()
    }

    override fun lineToModel(data: List<String>): Model {
        return Assessment(data[0], data[1], data[2].toInt())
    }

    override fun modelToLine(model: Model): String {
        val assessment = model as Assessment
        return "${assessment.name},${assessment.course_id},${assessment.percentage}"
    }
}