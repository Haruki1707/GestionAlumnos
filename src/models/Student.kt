package models

import helpers.Model
import stores.CourseStore
import stores.GradeStore

data class Student(var name: String): Model() {
    val courses: List<Course>
        get() = CourseStore.courses.filter { it.students_ids.contains(this.id.toString()) }

    val grades: List<Grade>
        get() = GradeStore.grades.filter { it.student_id == this.id }
}
