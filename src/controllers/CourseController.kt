package controllers

import models.Course
import models.Student
import stores.CourseStore
import stores.StudentStore

object CourseController {
    fun index() {
        println("CURSOS REGISTRADOS")
        println("Código\tNombre\t\tAlumnos inscritos")
        CourseStore.courses.forEach {
            println("${it.id}\t\t${it.name}\t\t${it.students.count()}")
        }
        readln()
    }

    fun show() {
        println("MOSTRAR UN CURSO")
        println("Ingrese el código del curso que desea mostrar:")
        val courseId = readln()

        CourseStore.courses.find { it.id == courseId.toInt()}?.let { course ->
            println("Código\tNombre\tAlumnos inscritos")
            println("${course.id}\t\t${course.name}\t\t${course.students.count()}")
            println("ID\tAlumnos inscritos")
            course.students.forEach {
                println("${it.id}\t${it.name}")
            }
        } ?: println("El curso no existe en el registro.")

        readln()
    }

    fun create() {
        println("CREACIÓN DE UN CURSO NUEVO")
        println("Ingrese el nombre del curso:")
        val courseName = readln()
        println("Ingrese los ids de los estudiantes que pertenecerán al curso, separados por comas:")
        val studentsIds = readln().split(",").map { it.trim() }
        val verifiedStudents: MutableList<String> = mutableListOf()

        studentsIds.forEach {
            if (it !in StudentStore.students.map { it.id.toString() }) {
                println("El id $it no corresponde a ningún estudiante.")
            } else {
                studentsIds.drop(studentsIds.indexOf(it))
                verifiedStudents.addFirst(it)
            }
        }

        if (verifiedStudents.isNotEmpty()) {
            CourseStore.add(Course(courseName, verifiedStudents))
            println("Curso creado con éxito y alumnos añadidos correctamente.")
        } else {
            CourseStore.add(Course(courseName, verifiedStudents))
            println("Curso creado con éxito y sin añadir alumnos.")
        }
        readln()
    }
}