package models

import helpers.Model
import stores.AssessmentStore
import stores.StudentStore

data class Course(var name: String, var students_ids: List<String>): Model() {
    val assessments: List<Assessment>
        get() = AssessmentStore.assessments.filter { it.course_id == this.id.toString() }

    val students: List<Student>
        get() = StudentStore.students.filter { it.courses.contains(this) }
}