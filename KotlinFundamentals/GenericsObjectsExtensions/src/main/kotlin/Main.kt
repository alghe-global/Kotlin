/**
 * Not using generics.
 */
class FillInTheBlankQuestion(
    val questionText: String,
    val answer: String,
    val difficulty: String
)

class TrueOrFalseQuestion(
    val questionText: String,
    val answer: Boolean,
    val difficulty: String
)

class NumericQuestion(
    val questionText: String,
    val answer: Int,
    val difficulty: String
)

/**
 * Using generics.
 */
class SimpleQuestion<T>(
    val questionText: String,
    val answer: T,
    val difficulty: String
)

/**
 * Using generics and enum.
 */
enum class Difficulty {
    EASY, MEDIUM, HARD
}

class EnumQuestion<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

/**
 * Data class.
 */
data class DataEnumQuestion<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

/**
 * Singleton object.
 */
object SimpleStudentProgress {
    var total: Int = 10
    var answered: Int = 3
}

/**
 * Class with singleton object within.
 */
class Quiz {
    val question10 = DataEnumQuestion<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question11 = DataEnumQuestion<Boolean>(
        "The sky is green. True or false",
        false,
        Difficulty.EASY
    )
    val question12 = DataEnumQuestion<Int>(
        "How many days are there between full moons?",
        28,
        Difficulty.HARD
    )

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
}

/**
 * Extension property.
 */
val Quiz.StudentProgress.progressText: String
    get() = "$answered of $total answered."

/**
 * Extension function.
 */
fun Quiz.StudentProgress.printProgressBar() {
    repeat(Quiz.answered) { print("▓") }
    repeat(Quiz.total - Quiz.answered) { print("▒") }
    println()
    println(Quiz.progressText)
}

/**
 * Interfaces.
 */
interface ProgressPrintable {
    val progressText: String

    fun printProgressBar()
}

class InterfaceQuiz : ProgressPrintable {
    override val progressText: String
        get() = "$answered of $total answered."

    override fun printProgressBar() {
        repeat(InterfaceQuiz.answered) { print("▓") }
        repeat(InterfaceQuiz.total - InterfaceQuiz.answered) { print("▒") }
        println()
        println(progressText)
    }

    val question10 = DataEnumQuestion<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question11 = DataEnumQuestion<Boolean>(
        "The sky is green. True or false",
        false,
        Difficulty.EASY
    )
    val question12 = DataEnumQuestion<Int>(
        "How many days are there between full moons?",
        28,
        Difficulty.HARD
    )

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
}

/**
 * Scope functions.
 */
class ScopeQuiz : ProgressPrintable {
    override val progressText: String
        get() = "$answered of $total answered."

    override fun printProgressBar() {
        repeat(InterfaceQuiz.answered) { print("▓") }
        repeat(InterfaceQuiz.total - InterfaceQuiz.answered) { print("▒") }
        println()
        println(progressText)
    }

    fun printQuiz() {
        question10.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question11.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        question12.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
    }

    val question10 = DataEnumQuestion<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question11 = DataEnumQuestion<Boolean>(
        "The sky is green. True or false",
        false,
        Difficulty.EASY
    )
    val question12 = DataEnumQuestion<Int>(
        "How many days are there between full moons?",
        28,
        Difficulty.HARD
    )

    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
}

fun main() {
    val question1 = SimpleQuestion<String>("Quoth the raven ___", "nevermore", "medium")
    val question2 = SimpleQuestion<Boolean>("The sky is green. True or false", false, "easy")
    val question3 = SimpleQuestion<Int>(
        "How many days are there between full moons?",
        28,
        "hard"
    )

    val question4 = EnumQuestion<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question5 = EnumQuestion<Boolean>(
        "The sky is green. True or false",
        false,
        Difficulty.EASY
    )
    val question6 = EnumQuestion<Int>(
        "How many days are there between full moons?",
        28,
        Difficulty.HARD
    )
    println(question4.toString())  // SimpleQuestion@eed1f14

    val question7 = DataEnumQuestion<String>("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question8 = DataEnumQuestion<Boolean>(
        "The sky is green. True or false",
        false,
        Difficulty.EASY
    )
    val question9 = DataEnumQuestion<Int>(
        "How many days are there between full moons?",
        28,
        Difficulty.HARD
    )
    /**
     * DataEnumQuestion(questionText=Quoth the raven ___, answer=nevermore, difficulty=MEDIUM)
     */
    println(question7.toString())

    println("\n****************************\n")

    /**
     * Accessing a singleton object.
     */
    println("${SimpleStudentProgress.answered} of ${SimpleStudentProgress.total} answered.")

    println("\n****************************\n")

    /**
     * Accessing a companion singleton object.
     */
    println("${Quiz.answered} of ${Quiz.total} answered.")

    println("\n****************************\n")

    /**
     * Extending classes with new properties and methods.
     */
    println(Quiz.progressText)
    Quiz.printProgressBar()

    println("\n****************************\n")

    /**
     * Calling method within class that extends an interface.
     */
    InterfaceQuiz().printProgressBar()

    println("\n****************************\n")

    /**
     * Scope functions.
     */
    val quiz = ScopeQuiz()
    quiz.printQuiz()

    println("\n****************************\n")

    /**
     * Call an object's methods without a variable using apply()
     */
    ScopeQuiz().apply {
        printQuiz()
    }
}