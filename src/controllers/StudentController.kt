package controllers

import helpers.readInt
import models.Student
import stores.GradeStore
import stores.StudentStore

object StudentController {
    fun index(showTitle: Boolean = true){
        if (showTitle) println("ALUMNOS REGISTRADOS")
        println("Codigo\tNombre")
        StudentStore.students.forEach { student: Student -> println("${student.id}\t\t${student.name}\t") }
        if (showTitle)
            readln()
    }

    fun show() {
        println("MOSTRAR EXPEDIENTE ACADÉMICO DE UN ALUMNO")
        index(false)
        println("Ingrese el código del estudiante:")
        val studentId = readInt()
        StudentStore.students.find { studentId == it.id }?.let { student ->
            println("\nEXPEDIENTE ACADÉMICO DE ${student.name}")
            student.courses.forEach {course ->
                println("\nCURSO: ${course.name}")
                println("Actividad\tNota")
                course.assessments.forEach { assessment ->
                    val grade = GradeStore.grades.find { it.student_id == student.id && it.assessment_id == assessment.id }
                    println("${assessment.name}\t${grade?.grade ?: "N/A"}")
                }
            }
        } ?: println("No existe un estudiante registrado con el código proporcionado.")

        readln()
    }

    fun create() {
        println("CREACIÓN DE UN NUEVO ALUMNO")
        println("Ingrese los nombres de los nuevos alumnos, separados por comas:")
        val students = readln().split(",").map { it.trim() }

        students.forEach {
            StudentStore.add(Student(it))
            println("Alumnos añadido correctamente.")
        }

        readln()
    }
}

