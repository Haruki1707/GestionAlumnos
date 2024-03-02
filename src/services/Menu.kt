package services

/**
 * This object represents the Menu in the application.
 * It contains a list of options, each with a corresponding action.
 */
object Menu {
    private val options = ArrayList<Pair<String, () -> Unit>>()

    fun addOption(option: String, action: () -> Unit) {
        options.add(Pair(option, action))
    }

    fun show() {
        println("Bienvenido ${Auth.user!!.username}")
        println("¿Qué desea hacer?")

        options.forEachIndexed { index, option ->
            println("${index + 1}. ${option.first}")
        }
        println("${options.size + 1}. Cerrar sesión")
    }

    fun executeOption(option: String) {
        val index: Int = try {
            option.toInt() - 1
        } catch (e: Exception) {
            -1
        }

        if (index >= 0 && index < options.size) {
            options[index].second()
        } else if (index == options.size) {
            println("Sesión cerrada. Presione enter para continuar.")
            readln()
            Auth.logout()
        } else {
            println("Opción inválida. Presione enter para continuar.")
            readln()
            return
        }
    }
}