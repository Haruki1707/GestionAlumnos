import controllers.CourseController
import models.Course
import services.Menu

fun main() {
    //Menu.addOption("Test", TestController::test)

    Menu.addOption("Listar cursos", CourseController::index)
    Menu.addOption("Mostrar un curso", CourseController::show)
    Menu.addOption("Agregar un curso nuevo", CourseController::create)

    runApp()
}