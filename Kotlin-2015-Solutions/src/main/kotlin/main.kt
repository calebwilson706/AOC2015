import Day1.part1
import kotlin.system.measureTimeMillis

fun main(args: Array<String>){
    val time1 = measureTimeMillis {
        Day4.part1()
    }
    val time2 = measureTimeMillis {
        Day4.allPartsSolution(5)
    }

    println("$time1 vs $time2")
}