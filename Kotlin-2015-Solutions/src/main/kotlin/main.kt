import Day1.part1
import kotlin.reflect.typeOf
import kotlin.system.measureTimeMillis

fun main(args: Array<String>){
    val today = Day6()

    val time1 = measureTimeMillis {
        today.part2(false)
    }
    val time2 = measureTimeMillis {
        today.part2(true)
    }

    println("$time1 vs $time2")

}