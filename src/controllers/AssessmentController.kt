package controllers

import helpers.clearScreen
import helpers.readInt
import models.Assessment
import stores.AssessmentStore
import stores.CourseStore

object AssessmentController {

    fun index() {
        println("EVALUACIONES REGISTRADAS")
        println("Digite el curso del que quiere ver las evaluaciones:")
        CourseStore.courses.forEach {
            println("${it.id} - ${it.name}")
        }
        val courseId = readInt()
        clearScreen()
        CourseStore.courses.find { it.id == courseId }?.let { course ->
            println("Evaluaciones del curso ${course.name}")
            println("ID\tNombre")
            course.assessments.forEach {
                println("${it.id}\t${it.name}")
            }
        } ?: println("El curso no existe en el registro.")

        readln()
    }

    fun create() {
        println("CREACIÓN DE UNA EVALUACIÓN")
        println("Digite el curso al que pertenece la evaluación:")
        CourseStore.courses.forEach {
            println("${it.id} - ${it.name}")
        }
        val courseId = readInt()

        CourseStore.courses.find { it.id == courseId }?.let { course ->
            println("Digite el nombre de la evaluación:")
            val name = readln()
            AssessmentStore.add(Assessment(course.id, name))
            println("Evaluación creada con éxito.")
        } ?: println("El curso no existe en el registro.")

        readln()
    }

    fun update() {
        println("ACTUALIZACIÓN DE UNA EVALUACIÓN")
        println("Digite el curso al que pertenece la evaluación:")
        CourseStore.courses.forEach {
            println("${it.id} - ${it.name}")
        }
        val courseId = readInt()

        clearScreen()
        CourseStore.courses.find { it.id == courseId }?.let { course ->
            course.assessments.forEach {
                println("${it.id} - ${it.name}")
            }
            println("Digite el id de la evaluación que desea actualizar:")
            val assessmentId = readInt()
            course.assessments.find { it.id == assessmentId }?.let {assessment ->
                println("Digite el nuevo nombre de la evaluación:")
                val name = readln()
                assessment.name = name
                println("Evaluación actualizada con éxito.")
                AssessmentStore.saveFile()
            } ?: println("La evaluación no existe en el registro.")
        } ?: println("El curso no existe en el registro.")

        readln()
    }

    fun delete() {
        println("ELIMINACIÓN DE UNA EVALUACIÓN")
        println("Digite el curso al que pertenece la evaluación:")
        CourseStore.courses.forEach {
            println("${it.id} - ${it.name}")
        }
        val courseId = readInt()

        clearScreen()
        CourseStore.courses.find { it.id == courseId }?.let { course ->
            course.assessments.forEach {
                println("${it.id} - ${it.name}")
            }
            println("Digite el id de la evaluación que desea eliminar:")
            val assessmentId = readInt()
            AssessmentStore.assessments.find { it.id == assessmentId }?.let {
                AssessmentStore.assessments.remove(it)
                println("Evaluación eliminada con éxito.")
                AssessmentStore.saveFile()
            } ?: println("La evaluación no existe en el registro.")
        } ?: println("El curso no existe en el registro.")

        readln()
    }
}