import java.lang.StringBuilder
import kotlin.math.roundToInt

object Day10 {
    private const val inputNumString = "1113122113"

    fun part1(input : String = inputNumString, amount : Int = 40) {
        var answer = input

        fun helper() {
            var currentDigit = answer.first()
            var amount = 0
            val builder = StringBuilder()
            answer.forEach {
                if (it == currentDigit) {
                    amount++
                } else {
                    builder.append(amount)
                    builder.append(currentDigit)
                    currentDigit = it
                    amount = 1
                }
            }
            builder.append(amount)
            builder.append(currentDigit)
            answer = builder.toString()

           // println(answer)
        }
        (0 until amount).forEach {
            helper()
            //println(it)
        }
        println(answer.length)
    }



    fun part2() {
        part1(amount = 50)
    }



}