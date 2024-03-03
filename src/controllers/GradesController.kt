package controllers

import helpers.clearScreen
import helpers.readDouble
import helpers.readInt
import models.Grade
import stores.GradeStore
import stores.StudentStore

object GradesController {
    fun update() {
        println("ACTUALIZACIÓN DE NOTAS")
        println("Digite el estudiante al que quiere actualizar las notas:")
        StudentStore.students.forEach {
            println("${it.id} - ${it.name}")
        }
        val studentId = readInt()

        clearScreen()
        StudentStore.students.find { it.id == studentId }?.let { student ->
            println("Digite el curso al que pertenece la nota:")
            student.courses.forEach {
                println("${it.id} - ${it.name}")
            }
            val courseId = readInt()

            clearScreen()
            student.courses.find { it.id == courseId }?.let { course ->
                println("ESTUDIANTE: ${student.name}")
                println("CURSO: ${course.name}")
                println("EVALUACIONES:")
                println("ID\tNombre\t\tNota")
                course.assessments.forEach { assessment ->
                    val grade = GradeStore.grades.find { it.student_id == student.id && it.assessment_id == assessment.id }
                    println("${assessment.id}\t${assessment.name}\t\t${grade?.grade ?: "N/A"}")
                }

                println("Digite la evaluación a la que pertenece la nota:")
                val assessmentId = readInt()
                clearScreen()

                course.assessments.find { it.id == assessmentId }?.let { assessment ->
                    println("Digite la nota del estudiante ${student.name} en el curso ${course.name} para la evaluación ${assessment.name}:")
                    val grade = readDouble()

                    GradeStore.grades.find { it.student_id == student.id && it.assessment_id == assessment.id }?.let {
                        it.grade = grade
                    } ?: GradeStore.add(Grade(student.id, assessment.id, grade))
                    println("Nota actualizada con éxito.")
                    GradeStore.saveFile()
                } ?: println("La evaluación no existe en el registro.")
            } ?: println("El curso no existe en el registro.")
        } ?: println("El estudiante no existe en el registro.")

        readln()
    }
}