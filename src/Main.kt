import controllers.AssessmentController
import controllers.CourseController
import controllers.StudentController
import services.Menu
import services.Menu.addSubOption

fun main() {
    // Gestión de alumnos
    Menu.addSubMenu("Gestión de alumnos").apply {
        addSubOption("Listar alumnos", StudentController::index)
        addSubOption("Añadir alumnos", StudentController::create)
    }


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