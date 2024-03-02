package models

import helpers.Model
import stores.CourseStore
import stores.GradeStore

data class Assessment(var course_id: String, var name: String, var percentage: Int): Model() {
    val grades: List<Grade>
        get() = GradeStore.grades.filter { it.assessment_id == this.id }

    val course: Course
        get() = CourseStore.courses.find { it.id.toString() == this.course_id }!!
}
