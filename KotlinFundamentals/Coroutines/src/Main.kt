import kotlin.system.*
import kotlinx.coroutines.*

fun main() {
    /** Synchronous code example */
    println("Weather forecast")
    println("Sunny")

    println("\n\n--------------------------\n\n")

    runBlocking {
        /** With a delay */
        println("Weather forecast")
        delay(1000)
        println("Sunny")
    }

    println("\n\n--------------------------\n\n")

    /** Suspending functions */
    runBlocking {
        println("Weather forecast")
        printForecast()
    }

    println("\n\n--------------------------\n\n")

    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            printForecast()
            printTemperature()
        }
    }
    println("Execution time: ${time / 1000.0} seconds")

    println("\n\n--------------------------\n\n")

    /** Concurrent execution */
    val concurrencyTime = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            launch {
                printForecast()
            }
            launch {
                printTemperature()
            }
        }
    }
    println("Execution time: ${concurrencyTime / 1000.0} seconds")

    println("\n\n--------------------------\n\n")

    runBlocking {
        println("Weather forecast")
        launch {
            printForecast()
        }
        launch {
            printTemperature()
        }
        println("Have a good day!")
    }

    println("\n\n--------------------------\n\n")

    /** async usage */
    runBlocking {
        println("Weather forecast")
        val forecast: Deferred<String> = async {
            getForecast()
        }
        val temperature: Deferred<String> = async {
            getTemperature()
        }
        println("${forecast.await()} ${temperature.await()}")
        println("Have a good day!")
    }

    println("\n\n--------------------------\n\n")

    /** Parallel decomposition */
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport())
        println("Have a good day!")
    }

    println("\n\n--------------------------\n\n")

    /** Exceptions */
    runBlocking {
        println("Weather forecast")
        try {
            println(getWeatherReportWithException())
        } catch (e: AssertionError) {
            println("Caught exception in runBlocking(): $e")
            println("Report unavailable at this time")
        }
        println("Have a good day!")
    }

    /** With exception handled within coroutine */
    runBlocking {
        println("Weather forecast")
        println(getWeatherReportWithHandledException())
        println("Have a good day!")
    }

    println("\n\n--------------------------\n\n")

    /** Cancellation */
    runBlocking {
        println("Weather forecast")
        println(getWeatherReportWithCancellation())
        println("Have a good day!")
    }

    println("\n\n--------------------------\n\n")

    /** Coroutine dispatcher */
    runBlocking {
        launch {
            withContext(Dispatchers.Default) {
                delay(1000)
                println("10 results found.")
            }
        }
        println("Loading...")
    }

    println("\n\n----------thread----------\n\n")

    /** print thread */
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking function")
        launch {
            println("${Thread.currentThread().name} - launch function")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - withContext function")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }
}

suspend fun printForecast() {
    delay(1000)
    println("Sunny")
}

suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}

/** Asynchronous (proper) functions */
suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(1000)
    return "30\u00b0C"
}

/** Parallel decomposition */
suspend fun getWeatherReport() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}

/** Exceptions */
suspend fun getTemperatureWithException(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}

suspend fun getWeatherReportWithException() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperatureWithException() }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getWeatherReportWithHandledException() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async {
        try {
            getTemperatureWithException()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{ No temperature found }"
        }
    }

    "${forecast.await()} ${temperature.await()}"
}

/** Cancellation */
suspend fun getWeatherReportWithCancellation() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }

    delay(200)
    temperature.cancel()

    "${forecast.await()}"
}