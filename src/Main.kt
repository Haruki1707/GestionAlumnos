import controllers.AssessmentController
import controllers.CourseController
import controllers.StudentController
import models.Course
import services.Menu

fun main() {
    // Gestión de alumnos
    Menu.addOption("Listar alumnos", StudentController::index)
    Menu.addOption("Añadir alumnos",StudentController::create)
    Menu.addOption("Mostrar un expediente académico", StudentController::show)


    // Gestión de cursos
    Menu.addOption("Listar cursos", CourseController::index)
    Menu.addOption("Mostrar datos de un curso", CourseController::show)
    Menu.addOption("Agregar un curso nuevo", CourseController::create)
    Menu.addOption("Actualizar un curso", CourseController::update)
    Menu.addOption("Eliminar un curso", CourseController::delete)

    // Gestión de evaluaciones
    Menu.addOption("Listar evaluaciones", AssessmentController::index)
    Menu.addOption("Agregar asignaciones a un curso", AssessmentController::create)
    Menu.addOption("Actualizar asignaciones de un curso", AssessmentController::update)
    Menu.addOption("Eliminar asignaciones de un curso", AssessmentController::delete)

    // Gestión de notas
    Menu.addOption("Actualizar notas", controllers.GradesController::update)

    runApp()
}