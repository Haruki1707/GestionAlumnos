import controllers.CourseController
import controllers.StudentController
import models.Course
import services.Menu

fun main() {
    //Menu.addOption("Test", TestController::test)
    println("GESTIÓN DE ALUMNOS")
    Menu.addOption("Listar alumnos", StudentController::index)
    Menu.addOption("Añadir alumnos",StudentController::create)


    println("GESTIÓN DE CURSOS")
    Menu.addOption("Listar cursos", CourseController::index)
    Menu.addOption("Mostrar datos de un curso", CourseController::show)
    Menu.addOption("Agregar un curso nuevo", CourseController::create)
    Menu.addOption("Actualizar un curso", CourseController::update)
    Menu.addOption("Eliminar un curso", CourseController::delete)

    runApp()
}