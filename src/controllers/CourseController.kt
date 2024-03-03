package controllers

import models.Course
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
        println("MOSTRAR DETALLES DE UN CURSO")
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
        val courseId = readln()

        CourseStore.courses.find { it.id == courseId.toInt() }?. let { course ->
            println("1. Agregar alumnos al curso\n2. Eliminar alumnos del curso")
            println("Ingresa el número de la opción que desea realizar:")
            val option = readln()

            when (option.toInt()) {
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
                else -> {
                    println("La opción digitada no existe.")
                }
            }
        } ?: println("El curso solicitado no existe.")

        CourseStore.saveFile()
        readln()
    }

    fun delete() {
        println("ELIMINAR UN CURSO")
        println("Ingrese el código del curso que desea eliminar:")
        val courseID = readln()

        CourseStore.courses.find { it.id == courseID.toInt() }?.let {
            CourseStore.courses.remove(it)
            CourseStore.saveFile()
            println("El curso con el código ${it.id} se eliminó correctamente.")
        } ?: println("El id no corresponde a ningún curso.")
        readln()
    }
}