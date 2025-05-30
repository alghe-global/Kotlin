import kotlin.time.measureTimedValue

fun main() {
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)

    println("\n**************************\n")

    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")

    println("\n**************************\n")

    printFinalTemperature(27.0, "Celsius", "Fahrenheit") { 9.0 / 5.0 * it + 32.0 }
    printFinalTemperature(350.0, "Kelvin", "Celsius") { it - 273.15 }
    printFinalTemperature(
        10.0,
        "Fahrenheit",
        "Kelvin") { 5.0 / 9.0 * (it - 32.0) + 273.15 }

    println("\n**************************\n")

    val song = Song("Eine Kleine Nachtmusik", "Wolfgang Amadeus Mozart", 1787, 1_000_000)
    song.printSongDescription()
    println(" -- and is it is popular? ${song.isPopular}")

    println("\n**************************\n")

    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()

    println("\n**************************\n")

    val phone = FoldablePhone(false)
    phone.fold()
    phone.switchOn()
    phone.checkPhoneScreenLight()

    phone.unfold()
    phone.switchOn()
    phone.checkPhoneScreenLight()

    phone.switchOff()
    phone.checkPhoneScreenLight()

    println("\n**************************\n")

    val winningBid = Bid(5000, "Private Collector")

    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")

}

fun printNotificationSummary(numberOfMessages: Int) {
    if (numberOfMessages < 100) {
        println("You have $numberOfMessages notifications.")
    } else {
        println("Your phone is blowing up! You have 99+ notifications.")
    }
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    val children = 15
    var standard = 30
    val senior = 20

    /*if (age < 13) {
        return children
    } else if (age < 61) {
        if (isMonday) {
            standard = 25
        }
        return standard
    } else if (age < 101) {
        return senior
    } else {
        return -1
    }
     */

    return when(age) {
        in 0..12 -> 15
        in 13..60 -> if (isMonday) 25 else 30
        in 61..100 -> 20
        else -> -1
    }
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

class Song(val title: String, val artist: String, val year: Int, val playCount: Int) {
    val isPopular: Boolean
        get() = playCount >= 1000

    fun printSongDescription() {
        print("$title, performed by $artist, was released in $year.")
    }
}

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        println("Name: $name\nAge: $age")
        if (hobby != null) {
            print("Likes to $hobby.")

            if (referrer != null) {
                print(" Has a referrer named ${referrer.name}")
                if (referrer.hobby != null) {
                    print(", who likes to ${referrer.hobby}.\n")
                } else {
                    print(".\n")
                }
            } else {
                println(" Doesn't have a referrer.\n")
            }
        }
    }
}

open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isFolded: Boolean) : Phone() {
    override fun switchOn() {
        if (isFolded == false) {
            isScreenLightOn = true
        } else {
            isScreenLightOn = false
        }
    }

    fun fold() {
        isFolded = true
    }

    fun unfold() {
        isFolded = false
    }
}

class Bid(val amount: Int, val bidder: String)

fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    /*if (bid == null) {
        return minimumPrice
    } else {
        return bid.amount
    }
     */

    return bid?.amount ?: minimumPrice
}