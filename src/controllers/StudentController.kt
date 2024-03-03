package controllers

import models.Student
import stores.StudentStore

object StudentController {
    fun index(){
        println("ALUMNOS REGISTRADOS")
        println("Codigo\tNombre")
        StudentStore.students.forEach { student: Student -> println("${student.id}\t\t${student.name}\t") }
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

