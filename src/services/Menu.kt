package services

import helpers.clearScreen

/**
 * This object represents the Menu in the application.
 * It contains a list of options, each with a corresponding action.
 */
object Menu {
    private val options = ArrayList<Pair<String, () -> Unit>>()
    private val subOptions = ArrayList<Triple<Int, String, () -> Unit>>()

    fun addOption(option: String, action: () -> Unit) {
        options.add(Pair(option, action))
    }

    fun addSubMenu(name: String): SubMenu {
        val index = options.size
        options.add(Pair(name) { (this::showSubMenu)(index) })
        return SubMenu(index)
    }

    private fun showSubMenu(index: Int) {
        var returnToMain: Boolean = false
        while (!returnToMain) {
            clearScreen()
            show(index)
            val option = readln()
            clearScreen()
            returnToMain = executeOption(option, index)
        }
    }

    fun SubMenu.addSubOption(option: String, action: () -> Unit) {
        subOptions.add(Triple(index, option, action))
    }

    fun show(subMenuIndex: Int? = null) {
        println("Bienvenido ${Auth.user!!.username}")
        println("¿Qué desea hacer?")

        val options = if (subMenuIndex != null) {
            subOptions.filter { it.first == subMenuIndex }.map { Pair(it.second, it.third) }
        } else {
            options
        }

        options.forEachIndexed { index, option ->
            println("${index + 1}. ${option.first}")
        }
        if (subMenuIndex == null) println("${options.size + 1}. Cerrar sesión")
        else println("${options.size + 1}. Volver al menú principal")
    }

    fun executeOption(option: String, subMenuIndex: Int? = null): Boolean {
        val index: Int = try {
            option.toInt() - 1
        } catch (e: Exception) {
            -1
        }

        val options = if (subMenuIndex != null) {
            subOptions.filter { it.first == subMenuIndex }.map { Pair(it.second, it.third) }
        } else {
            options
        }

        if (index >= 0 && index < options.size) {
            options[index].second()
        } else if (index == options.size && subMenuIndex == null) {
            println("Sesión cerrada. Presione enter para continuar.")
            readln()
            Auth.logout()
        } else if(index == options.size) {
            println("Volviendo al menú principal. Presione enter para continuar.")
            readln()
        } else {
            println("Opción inválida. Presione enter para continuar.")
            readln()
            return false
        }

        return true
    }
}

class SubMenu(val index: Int)