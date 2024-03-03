import controllers.CourseController
import models.Course
import services.Menu

fun main() {
    //Menu.addOption("Test", TestController::test)

    println("MENÚ PARA CURSOS")
    Menu.addOption("Listar cursos", CourseController::index)
    Menu.addOption("Mostrar datos de un curso", CourseController::show)
    Menu.addOption("Agregar un curso nuevo", CourseController::create)
    Menu.addOption("Actualizar un curso", CourseController::update)
    Menu.addOption("Eliminar un curso", CourseController::delete)

    runApp()
}