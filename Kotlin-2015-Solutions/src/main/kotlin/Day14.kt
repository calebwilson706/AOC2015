import java.io.File
import kotlin.math.min

object Day14 {
    private val reindeerList = getReindeerList()

    fun part1() {
        println(getReindeerList().map {it.getDistanceAfter(2503)}.maxOrNull())
    }
    fun part2() {
        val totals = hashMapOf<String, Int>()

        for ( time in 1..2503) {
            val inLead = reindeerList.maxByOrNull { it.getDistanceAfter(time) }!!

            if (totals[inLead.name] == null) {
                totals[inLead.name] = 1
            } else {
                totals[inLead.name] = totals[inLead.name]!! + 1
            }

        }

        println(totals.values.maxOrNull())
    }

}

private val myStrDay13 = File("/Users/calebjw/Documents/AdventOfCode/2015/inputs/Day14Input.txt").readText()

class Reindeer(var name : String, var speed : Int, var durationOfFlight : Int, var restPeriod : Int) {
    fun getDistanceAfter(time : Int) : Int {
        var total = 0
        var currentTime = 0

        while (currentTime < time) {
            val distanceRemaining = time - currentTime

            total += if ((time - currentTime) < durationOfFlight) {
                speed * distanceRemaining
            } else {
                speed * durationOfFlight
            }
            currentTime += durationOfFlight + restPeriod
        }

        return total
    }


}

fun getReindeerList() : List<Reindeer> {
    val components = myStrDay13
        .split(".")
        .dropLast(1)
    val regexStatement = "([A-Z][a-z]+) can fly ([0-9]+) km/s for ([0-9]+) seconds, but then must rest for ([0-9]+) seconds".toRegex()
    val deer = mutableListOf<Reindeer>()

    components.forEach {
        val (name, speed, durationFlight, rest) = regexStatement.find(it)!!.destructured
        //println("$name -> $speed -> $durationFlight -> $rest")
        deer.add(Reindeer(name,speed.toInt(),durationFlight.toInt(),rest.toInt()))
    }

    return deer
}