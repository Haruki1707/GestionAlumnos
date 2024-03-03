import controllers.StudentController
import services.Menu
import stores.StudentStore

fun main() {
    //Menu.addOption("Test", TestController::test)
    Menu.addOption("Listar alumnos", StudentController::index)
    Menu.addOption("Añadir alumno",StudentController::create)


    runApp()
}