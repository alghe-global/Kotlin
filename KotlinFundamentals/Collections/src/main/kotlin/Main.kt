/**
 * Arrays.
 */
val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")
val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")
val arraySolarSystem = rockPlanets + gasPlanets
val newArraySolarSystem = arrayOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto")

/**
 * List
 */
val listSolarSystem = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
val mutableListSolarSystem = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")

/**
 * Sets
 */
val mutableSetSolarSystem = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")

/**
 * Map
 */
val mutableMapSolarSystem = mutableMapOf(
    "Mercury" to 0,
    "Venus" to 0,
    "Earth" to 1,
    "Mars" to 2,
    "Jupiter" to 79,
    "Saturn" to 82,
    "Uranus" to 27,
    "Neptune" to 14
)

/**
 * Higher-order functions with collections.
 */
class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)

/**
 * Task 2
 */
enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

data class EventWithEnum(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

/**
 * Task 7
 */
val EventWithEnum.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

fun main() {
    /**
     * Arrays.
     */
    println(arraySolarSystem[0])
    println(arraySolarSystem[1])
    println(arraySolarSystem[2])
    println(arraySolarSystem[3])
    println(arraySolarSystem[4])
    println(arraySolarSystem[5])
    println(arraySolarSystem[6])
    println(arraySolarSystem[7])

    arraySolarSystem[3] = "Little Earth"  // modifying an element of the array
    println(arraySolarSystem[3])  // and printing it

    // arraySolarSystem[8] = "Pluto"  // java.lang.ArrayIndexOutOfBoundsException: Index 8 out of bounds for length 8

    println(newArraySolarSystem[8])

    println("\n*******************\n")

    /**
     * Lists.
     */
    println(listSolarSystem.size)  // print size

    println(listSolarSystem[2])  // subscript method
    println(listSolarSystem.get(3))  // get method method

    println(listSolarSystem.indexOf("Earth"))  // fetch index of element
    println(listSolarSystem.indexOf("Pluto"))  // -1 for non existing element

    for (planet in listSolarSystem) {
        println(planet)  // iterate over elements in `listSolarSystem' and print each one
    }

    mutableListSolarSystem.add("Pluto")  // first type add method: element only without index
    mutableListSolarSystem.add(3, "Theia")  // second type add method: element with index

    mutableListSolarSystem[3] = "Future Moon"  // update existing elements with subscript syntax

    println(mutableListSolarSystem[3])
    println(mutableListSolarSystem[9])

    mutableListSolarSystem.removeAt(9)  // Remove Pluto from the list
    mutableListSolarSystem.remove("Future Moon")

    println(mutableListSolarSystem.contains("Pluto"))
    println("Future Moon" in mutableListSolarSystem)

    /**
     * Sets.
     */
    println(mutableSetSolarSystem.size)  // print size
    mutableSetSolarSystem.add("Pluto")  // there's no index for sets! LinkedHashSet implementation
    println(mutableSetSolarSystem.size)

    println(mutableSetSolarSystem.contains("Pluto"))
    println("Pluto" in mutableSetSolarSystem)  // using `in' operator

    mutableSetSolarSystem.add("Pluto")
    println(mutableSetSolarSystem.size)  // sets can't contain duplicates!

    mutableSetSolarSystem.remove("Pluto")  // again, no indexing so only the element as param
    println(mutableSetSolarSystem.size)
    println("Pluto" in mutableSetSolarSystem)

    /**
     * Map
     */
    println(mutableMapSolarSystem.size)  // print number of key-value pairs

    mutableMapSolarSystem["Pluto"] = 5
    println(mutableMapSolarSystem.size)

    println(mutableMapSolarSystem["Pluto"])  // print number of moons for the key "Pluto"
    println(mutableMapSolarSystem.get("Theia"))  // print number of moons for "Theia": null

    mutableMapSolarSystem.remove("Pluto")
    println(mutableMapSolarSystem.size)

    mutableMapSolarSystem["Jupiter"] = 78  // or use `put()' method
    println(mutableMapSolarSystem["Jupiter"])

    /**
     * Higher-order functions with collections
     * forEach(): loop over each element in a collection
     */
    cookies.forEach {
        println("Menu item: $it")
    }

    cookies.forEach {
        println("Menu item: $it.name")  // cannot combine property with dot operator
    }

    cookies.forEach {
        println("Menu item: ${it.name}")  // expression usage is valid
    }

    /**
     * map(): format the items in a collection (often as a collection of another data type)
     */
    val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }
    fullMenu.forEach {
        println(it)
    }

    /**
     * filter(): generate a subset of a collection
     */
    val softBakedMenu = cookies.filter {
        it.softBaked
    }
    println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }

    /**
     * groupBy(): splits a collection based on a function's return value
     * NOTE: for splitting a list in two, an alternative is the partition() function
     */
    val groupedMenu = cookies.groupBy { it.softBaked }
    println(groupedMenu)  // return type: Map<Boolean, List<Cookie>>
    val groupedSoftBakedMenu = groupedMenu[true] ?: listOf()
    val groupedCrunchyMenu = groupedMenu[false] ?: listOf()

    println("Soft cookies:")
    groupedSoftBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
    println("Crunchy cookies:")
    groupedCrunchyMenu.forEach {
        println("${it.name} - $${it.price}")
    }

    /**
     * fold(): turns a collection into a single value
     * NOTE: Kotlin also has its own function called reduce(), where the accumulator
     *       starts with the first element in the collection, rather than an initial
     *       value passed as an argument.
     * NOTE: Kotlin collections also have a sum() function for numeric types, as well
     *       as a higher-order sumOf() function.
     */
    val totalPrice = cookies.fold(0.0) {total, cookie ->
        total + cookie.price
    }
    println("Total price: $${totalPrice}")

    /**
     * sortedBy(): sort a collection by a specified property; lets one specify a lambda
     *             that returns the property one'd like to sort by
     * For example: if one would like to sort by price, the lambda would return it.price
     */
    val alphabeticalMenu = cookies.sortedBy {
        it.name  // resulting list is of type List<Cookie> but sorted based on `name'
    }
    println("Alphabetical menu:")
    alphabeticalMenu.forEach {
        println(it.name)
    }

    println("\n******************\n")

    /**
     * Practice: Classes and Collections
     */
    /**
     * Task 1
     */
    data class EventWithoutEnum(
        val title: String,
        val description: String?,
        val daypart: String,
        val durationInMinutes: Int
    )

    val study = EventWithoutEnum(
        title = "Study Kotlin",
        description = "Commit to studying Kotlin at least 15 minutes per day.",
        daypart = "Evening",
        durationInMinutes = 15
    )
    println(study)

    /**
     * Task 3
     */
    val events = mutableListOf<EventWithEnum>()

    events.add(
        EventWithEnum(
            title = "Wake up",
            description = "Time to get up",
            daypart = Daypart.MORNING,
            durationInMinutes = 0
        )
    )
    events.add(
        EventWithEnum(
            title = "Eat breakfast",
            daypart = Daypart.MORNING,
            durationInMinutes = 15
        )
    )
    events.add(
        EventWithEnum(
            title = "Learn about Kotlin",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 30
        )
    )
    events.add(
        EventWithEnum(
            title = "Practice Compose",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 60
        )
    )
    events.add(
        EventWithEnum(
            title = "Watch latest DevBytes video",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 10
        )
    )
    events.add(
        EventWithEnum(
            title = "Check out latest Android Jetpack library",
            daypart = Daypart.EVENING,
            durationInMinutes = 45
        )
    )

    /**
     * Task 4
     */
    val eventsSummarized = events.filter {
        it.durationInMinutes < 60
    }
    println("You have ${eventsSummarized.size} short events.")

    /**
     * Task 5
     */
    val daypartSummarized = events.groupBy {
        it.daypart
    }
    daypartSummarized.forEach {
        println("${it.key}: ${it.value.size} events")
    }

    /**
     * Task 6
     */
    println("Last event of the day: ${events.last().title}")

    /**
     * Task 7
     */
    println("Duration of first event of the day: ${events[0].durationOfEvent}")
}