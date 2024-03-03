package models

import helpers.Model
import stores.CourseStore
import stores.GradeStore

data class Assessment(var course_id: Int, var name: String): Model() {
    val grades: List<Grade>
        get() = GradeStore.grades.filter { it.assessment_id == this.id }

    val course: Course
        get() = CourseStore.courses.find { it.id == this.course_id }!!
}
