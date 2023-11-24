fun main() {
    val firstFavoriteActor = null
    println("val firstFavoriteActor = null: $firstFavoriteActor")

    var secondFavoriteActor: String = "Florence Pugh"
    /**
     * Kotlin: Null can not be a value of a non-null type String
     */
    // secondFavoriteActor = null

    var thirdFavoriteActor: String? = "Florence Pugh"
    println("var thirdFavoriteActor: String? = \"Florence Pugh\": $thirdFavoriteActor")

    thirdFavoriteActor = null  /* Allowed due to `?' operator declaring nullable variable */
    println("thirdFavoriteActor = null: $thirdFavoriteActor")

    var favoriteNumber: Int? = 4
    println("var favoriteNumber: Int? = 4: $favoriteNumber")

    favoriteNumber = null
    println("favoriteNumber = null: $favoriteNumber")

    /**
     * Handling of nullable variables
     */
    val fourthFavoriteActor: String = "Florence Pugh"
    println("fourthFavoriteActor.length: ${fourthFavoriteActor.length}")

    var fifthFavoriteActor: String? = "Florence Rose Pugh"
    /**
     * Kotlin: Only safe (?.) or non-null asserted (!!.) calls are allowed on a
     * nullable receiver of type String?
     */
    // println("fifthFavoriteActor.length: ${fifthFavoriteActor.length}")

    /**
     * Usage of `?.' safe-call operator
     */
    println("fifthFavoriteActor?.length: ${fifthFavoriteActor?.length}")

    fifthFavoriteActor = null
    println("(after null re-assignment) fifthFavoriteActor?.length: ${fifthFavoriteActor?.length}")

    /**
     * Usage of not-null assertion operator
     */
    fifthFavoriteActor = "Florence R. Pugh"
    println("(after String re-assignment) fifthFavoriteActor!!.length: ${fifthFavoriteActor!!.length}")

    var sixthFavoriteActor: String? = null
    /**
     * Exception in thread "main" java.lang.NullPointerException
     *
     */
    // println(sixthFavoriteActor!!.length)

    /**
     * Null checks
     */
    var seventhFavoriteActor: String? = null

    /**
     * Direct access to `length' property due to null check
     * in below if condition. No need for ?. safe-call operator.
     */
    if (seventhFavoriteActor != null) {
        println("seventhFavoriteActor.length: ${seventhFavoriteActor.length}")
    } else {
        println("seventhFavoriteActor: You didn't input a name.")
    }

    /**
     * If/else expressions.
     */
    var eighthFavoriteActor: String? = "FP"

    val lengthOfName = if (eighthFavoriteActor != null) {
        eighthFavoriteActor.length
    } else {
        0
    }
    println("eighthFavoriteActor.length (lengthOfName): $lengthOfName")

    /**
     * The ?: Elvis operator
     */
    var ninthFavoriteActor: String? = "FRP"
    val nameLength = ninthFavoriteActor?.length ?: 0
    println("ninthFavoriteActor.length ?: 0 (nameLength): $nameLength")
}