import controllers.CourseController
import models.Course
import services.Menu

fun main() {
    //Menu.addOption("Test", TestController::test)

    Menu.addOption("Agregar un curso nuevo", CourseController::create)

    runApp()
}