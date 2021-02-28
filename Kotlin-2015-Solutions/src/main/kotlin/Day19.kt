import java.io.File
import java.lang.StringBuilder

object Day19 {
    private val myStrDay19 = File("/Users/calebjw/Documents/AdventOfCode/2015/inputs/Day19InputString.txt").readText()
    private val transformations = getTransformations()

    fun parseInputString(str : String = myStrDay19) : List<String> {
        var results = mutableListOf<String>()

        var currentElement = StringBuilder()
        currentElement.append(str.first())

        (1..str.lastIndex).forEach { index ->
            val it = str[index]

            if (it.isUpperCase()) {
                results.add(currentElement.toString())
                currentElement.clear()
            }
            currentElement.append(it)
        }

        results.add(currentElement.toString())
        return results
    }

    fun part1() {
        val base : List<String> = parseInputString()
        val newStrings = mutableListOf<String>()

        transformations.forEach { transformation ->
            base.forEachIndexed { index, element ->
                if (element == transformation.original){
                    val originalTotalString = base.toMutableList()
                    originalTotalString[index] = transformation.new
                    newStrings.add(originalTotalString.joinToString(separator = ""))
                }
            }
        }

        println(newStrings.toSet().count())
    }



    fun part2() {
        val inp = parseInputString()
        println(inp.size - 1 - inp.count { it == "Rn" } - inp.count { it == "Ar" } - 2*inp.count { it == "Y" } + 1)
        println(inp.size)
    }

}

data class Chemical(val item : String, val mutations : Int = 0)
data class Transformation(val original : String, val new : String)

fun getTransformations(part : Int = 1): MutableList<Transformation> {
    val myStrDay19TransformationsLines =
        File("/Users/calebjw/Documents/AdventOfCode/2015/inputs/Day19InputTransformations.txt")
            .readText()
            .split("\n")
            .dropLast(3)

    val results = mutableListOf<Transformation>()
    val regexStatement = "([A-Za-z]+) => ([A-Za-z]+)".toRegex()

    myStrDay19TransformationsLines.forEach {
        val (first, second) = regexStatement.find(it)!!.destructured

        if (part == 1) {
            results.add(Transformation(first, second))
        } else {
            results.add(Transformation(second,first))
        }
    }

    return results
}

//between 194 and 200