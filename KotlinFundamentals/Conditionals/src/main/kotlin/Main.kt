fun main() {
    var trafficLightColor = "Black"

    /**
     * Classic if/else if/else branching.
     */
    if (trafficLightColor == "Red") {
        println("Stop")
    } else if (trafficLightColor == "Yellow") {
        println("Slow")
    } else if (trafficLightColor == "Green" ) {
        println("Go")
    }
    else {
        println("Invalid traffic-light color")
    }

    trafficLightColor = "Yellow"
    /**
     * Kotlin `when' branching.
     * When a body is executed, Kotlin ignores the rest of branches and
     * leaves the `when' statement.
     */
    when (trafficLightColor) {
        "Red" -> println("Stop")
        "Yellow" -> println("Slow")
        "Green" -> println("Go")
        else -> println("Invalid traffic-light color")
    }

    /**
     * `,', `in' and `is' keywords to form more complex `when' conditions
     */
    var x = 3
    when (x) {
        2 -> println("x is a prime number between 1 and 10")
        3 -> println("x is a prime number between 1 and 10")
        5 -> println("x is a prime number between 1 and 10")
        7 -> println("x is a prime number between 1 and 10")
        else -> println("x isn't a prime number between 1 and 10")
    }

    /**
     * `,' keyword demonstration
     */
    when (x) {
        2, 3, 5, 7 -> println("x is a prime number between 1 and 10")
        else -> println("x isn't a prime number between 1 and 10")
    }

    /**
     * `in' keyword for a range of conditions
     */
    x = 4
    when (x) {
        2, 3, 5, 7 -> println("x is a prime number between 1 and 10")
        in 1..10 -> println("x is a number between 1 and 10, but not a prime number")
        else -> println("x isn't a prime number between 1 and 10")
    }

    /**
     * Usage of the `is' keyword to check data type
     */
    var y: Any = 20
    when (y) {
        2, 3, 5, 7 -> println("x is a prime number between 1 and 10")
        in 1..10 -> println("x is a number between 1 and 10, but not a prime number")
        is Int -> println("x is an integer number, but not between 1 and 10")
        else -> println("x isn't an integer number")
    }

    trafficLightColor = "Amber"
    when (trafficLightColor) {
        "Red" -> println("Stop")
        "Yellow", "Amber" -> println("Slow")
        "Green" -> println("Go")
        else -> println("Invalid traffic-light color")
    }

    println("\n=======\nif/else and when as expressions\n=======\n")

    /**
     * `if/else' and `when' as expressions
     */
    var message =
        if (trafficLightColor == "Red") "Stop"
        else if (trafficLightColor == "Yellow") "Slow"
        else if (trafficLightColor == "Green") "Go"
        else "Invalid traffic-light color"
    println(message)

    message = when (trafficLightColor) {
        "Red" -> "Stop"
        "Yellow", "Amber" -> "Slow"
        "Green" -> "Go"
        else -> "Invalid traffic-light color"
    }
    println(message)
}