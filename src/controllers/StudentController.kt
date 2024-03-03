package controllers

import helpers.readInt
import models.Student
import stores.StudentStore

object StudentController {
    fun index(showIndex: Boolean = true){
        if (showIndex) println("ALUMNOS REGISTRADOS")
        println("Codigo\tNombre")
        StudentStore.students.forEach { student: Student -> println("${student.id}\t\t${student.name}\t") }
        readln()
    }

    fun show() {
        println("MOSTRAR EXPEDIENTE ACADÉMICO DE UN ALUMNO")
        index(false)
        println("Ingrese el código del estudiante:")
        val studentId = readInt()
        StudentStore.students.find { studentId == it.id }?.let { student ->
            println("EXPEDIENTE ACADÉMICO DE ${student.name}")
            student.courses.forEach {course ->
                println("CURSO: ${course.id} ${course.name}")
                println("Actividad\tNota")
                course.assessments.forEach { assessment ->
                    student.grades.find { assessment.id == it.assessment_id }?.let {
                        println("${assessment.name}\t${it.grade}")
                    }
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

