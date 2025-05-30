fun printHello() {
    println("Hi there!")
}

fun someOtherFunction(
    callMeLater: () -> Unit
) {
    callMeLater()
}

fun main(args: Array<String>) {
    /**
     * Kotlin treats functions as first-class constructs.
     * For example, functions are treated as data types,
     * just like an Int or a String. This means that you
     * can store functions in variables and later use
     * this reference to call this function.
     */
    val callMeLater = ::printHello
    callMeLater()  // Hi there!

    /**
     * Functions can receive other functions as parameters,
     * and also return functions or variables that represent
     * functions.
     */
    someOtherFunction(callMeLater)  // Hi there!
}