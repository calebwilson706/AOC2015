import java.io.File

object Day8 {
    var inputs = File("/Users/calebjw/Documents/AdventOfCode/2015/inputs/Day8Input.txt")
        .readText()
        .split("\n")

    fun bothParts(operation : (str : String) -> Int){
        var total = 0

        inputs.forEach {
            total += operation(it)
        }

        print(total)
    }

    fun part1() {
        bothParts(::getStringTotalPart1)
    }
    fun part2() {
        bothParts(::getStringTotalPart2)
    }

    private fun getStringTotalPart1(str : String): Int {
        val new = str
            .replace("\\\\\\\\".toRegex(), "§")
            .replace("\\\\x[a-f0-9]{2}".toRegex(),"§")
            .replace("\\\"", "§")
            .filter { it != '"' }
        //println("$str -> $new")
        return (-new.length + str.length)
    }

    fun getStringTotalPart2(str : String): Int {

        var new = str.replace("\\\\","§§§§")
        new = new.replace("\\\\x[a-f0-9]{2}".toRegex(),"§§§§§")
        new = new.replace("\\\"","§§§§")
        return(new.length - str.length + 4)

    }
}

//  >2047   <2180

