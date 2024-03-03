package helpers

fun readInt(): Int {
    var value: Int? = null

    while (value == null) {
        try {
            value = readln().toInt()
        } catch (e: NumberFormatException) {
            print("Por favor, ingrese un número entero: ")
        }
    }

    return value
}