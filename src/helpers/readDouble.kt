package helpers

fun readDouble(): Double {
    var value: Double? = null

    while (value == null) {
        try {
            value = readln().toDouble()
        } catch (e: NumberFormatException) {
            println("El valor ingresado no es un número válido.")
        }
    }

    return value
}