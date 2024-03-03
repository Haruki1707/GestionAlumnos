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

    
}

