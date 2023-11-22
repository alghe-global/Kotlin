fun main(args: Array<String>) {
    /**
     * Kotlin provides a shorthand syntax for declaring
     * a function called a lambda expression.
     * A lambda expression can be stored in a variable
     * that refers to the function.
     */
    val helloLambda = {
        println("Hi there!")
    }
    helloLambda()
}