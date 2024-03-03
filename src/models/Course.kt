package models

import helpers.Model
import stores.AssessmentStore
import stores.StudentStore

data class Course(var name: String, var students_ids: ArrayList<String>): Model() {
    val assessments: List<Assessment>
        get() = AssessmentStore.assessments.filter { it.course_id == this.id }

    val students: List<Student>
        get() = StudentStore.students.filter { it.courses.contains(this) }
}