package controllers

import helpers.clearScreen
import helpers.readInt
import models.Course
import stores.CourseStore
import stores.StudentStore
import kotlin.math.roundToLong

object CourseController {
    fun index(showTitle: Boolean = true) {
        if (showTitle)
            println("CURSOS REGISTRADOS")
        println("Código\tNombre\t\tAlumnos inscritos")
        CourseStore.courses.forEach {
            println("${it.id}\t\t${it.name}\t\t${it.students.count()}")
        }
        if (showTitle)
            readln()
    }

    fun show() {
        println("MOSTRAR DETALLES DE UN CURSO")
        index(false)
        println("Ingrese el código del curso que desea mostrar:")
        val courseId = readInt()
        clearScreen()

        CourseStore.courses.find { it.id == courseId }?.let { course ->
            println("DETALLES DEL CURSO")
            println("Código\tNombre\tAlumnos inscritos")
            println("${course.id}\t\t${course.name}\t\t${course.students.count()}")
            println("INFORME DE CALIFICACIONES")
            println("ID\tAlumnos inscritos\tPromedio")

            course.students.forEach { student ->
                val average = course.assessments.map { assessment ->
                    student.grades.find { it.assessment_id == assessment.id }?.grade ?: 0.0
                }.average().toBigDecimal().setScale(2, 0).toDouble()
                if (!average.isNaN()) println("${student.id}\t${student.name}\t\t${average}")
                else println("${student.id}\t${student.name}\t\tNo calificado")
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
        val verifiedStudents: ArrayList<String> = ArrayList()

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

    fun update() {
        println("ACTUALIZAR ALUMNOS INSCRITOS")
        println("Ingrese el código del curso a actualizar:")
        val courseId = readInt()

        CourseStore.courses.find { it.id == courseId }?. let { course ->
            println("1. Agregar alumnos al curso\n2. Eliminar alumnos del curso")
            println("Ingresa el número de la opción que desea realizar:")
            val option = readInt()

            when (option) {
                1 -> {
                    println("Ingrese los ids de los estudiantes que inscribirá al curso, separados por comas:")
                    val studentsIds = readln().split(",").map { it.trim() }

                    studentsIds.forEach { studentId ->
                        if (studentId !in StudentStore.students.map { it.id.toString() } || studentId in course.students_ids)
                            println("El id $studentId no corresponde a ningún estudiante o ya fue añadido al curso.")
                        else {
                            course.students_ids.addLast(studentId)
                            println("Alumno $studentId agregados al curso exitosamente.")
                        }
                    }
                }
                2 -> {
                    println("Ingrese los ids de los estudiantes que eliminará del curso, separados por comas:")
                    val studentsIds = readln().split(",").map { it.trim() }

                    studentsIds.forEach { studentId ->
                        if (studentId !in course.students_ids.map { it })
                            println("El id $studentId no corresponde a ningún estudiante registrado en el curso.")
                        else {
                            course.students_ids.remove(studentId)
                            println("Alumno $studentId eliminado del curso exitosamente.")
                        }
                    }
                }
                else -> println("La opción digitada no existe.")
            }
        } ?: println("El curso solicitado no existe.")

        CourseStore.saveFile()
        readln()
    }

    fun delete() {
        println("ELIMINAR UN CURSO")
        index(false)
        println("Ingrese el código del curso que desea eliminar:")
        val courseID = readInt()

        CourseStore.courses.find { it.id == courseID }?.let {
            CourseStore.courses.remove(it)
            CourseStore.saveFile()
            println("El curso con el código ${it.id} se eliminó correctamente.")
        } ?: println("El id no corresponde a ningún curso.")
        readln()
    }
}