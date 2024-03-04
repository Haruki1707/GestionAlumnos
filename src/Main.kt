import controllers.AssessmentController
import controllers.CourseController
import controllers.StudentController
import controllers.GradesController
import services.Menu
import services.Menu.addSubOption

fun main() {
    // Gestión de alumnos
    Menu.addSubMenu("Gestión de alumnos").apply {
        addSubOption("Listar alumnos", StudentController::index)
        addSubOption("Añadir alumnos", StudentController::create)
        addSubOption("Mostrar expediente de un alumno", StudentController::show)
    }


    // Gestión de cursos
    Menu.addSubMenu("Gestión de cursos").apply {
        addSubOption("Listar cursos", CourseController::index)
        addSubOption("Mostrar datos de un curso", CourseController::show)
        addSubOption("Agregar un curso nuevo", CourseController::create)
        addSubOption("Actualizar un curso", CourseController::update)
        addSubOption("Eliminar un curso", CourseController::delete)
    }

    // Gestión de evaluaciones
    Menu.addSubMenu("Gestión de evaluaciones").apply {
        addSubOption("Listar evaluaciones", AssessmentController::index)
        addSubOption("Agregar asignaciones a un curso", AssessmentController::create)
        addSubOption("Actualizar asignaciones de un curso", AssessmentController::update)
        addSubOption("Eliminar asignaciones de un curso", AssessmentController::delete)
    }

    // Gestión de notas
    Menu.addOption("Actualizar notas", GradesController::update)

    runApp()
}