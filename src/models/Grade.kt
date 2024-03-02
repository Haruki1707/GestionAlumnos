package models

import helpers.Model
import stores.AssessmentStore
import stores.StudentStore

data class Grade(var student_id: Int, var assessment_id: Int, var grade: Double): Model() {
    val student: Student
        get() = StudentStore.students.find { it.id == this.student_id }!!

    val assessment: Assessment
        get() = AssessmentStore.assessments.find { it.id == this.assessment_id }!!
}
