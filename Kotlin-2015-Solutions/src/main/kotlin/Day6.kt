import java.io.File


class Day6 {
    private var myListOfInstructions = mutableListOf<Day6Instruction>()

    init {
        val myStr = File("/Users/calebjw/Documents/AdventOfCode/2015/inputs/Day6Input.txt").readText()
        val componentsOfInput = myStr.split("\n")

        componentsOfInput.forEach { statement ->
            val tmpInstruction = when {
                statement.contains("off") -> {
                    INSTRUCTIONS.OFF
                }
                statement.contains("on") -> {
                    INSTRUCTIONS.ON
                }
                else -> {
                    INSTRUCTIONS.TOGGLE
                }
            }
            val numbersWithSplits = statement.filter { !it.isLetter() }.split(" ").filter { it != "" }
            val lowerNotParsed = numbersWithSplits[0].split(",")
            val upperNotParsed = numbersWithSplits[1].split(",")

            val lowerPoint = Point(lowerNotParsed[0].toInt(), lowerNotParsed[1].toInt())
            val upperPoint = Point(upperNotParsed[0].toInt(), upperNotParsed[1].toInt())

            myListOfInstructions.add(Day6Instruction(lowerPoint,upperPoint,tmpInstruction))
            //println(myListOfInstructions.last())
        }
    }

    fun part1() {
        val grid = mutableMapOf<Point, Boolean>()
        println(myListOfInstructions[3])

        fun followThis(instruction: Day6Instruction) {
            var counter = 0

            for (x in instruction.lowerPoint.x..instruction.upperPoint.x){
                for (y in instruction.lowerPoint.y .. instruction.upperPoint.y){
                    when (instruction.instruction){
                        INSTRUCTIONS.TOGGLE -> {
                            grid[Point(x, y)] = !(grid[Point(x,y)] ?: false)
                        }
                        INSTRUCTIONS.OFF -> {
                            grid[Point(x,y)] = false
                        }
                        INSTRUCTIONS.ON -> {
                            grid[Point(x,y)] = true
                        }
                    }
                }
            }

        }

        myListOfInstructions.forEach {
            followThis(it)
        }

        print(grid.count { it.value })


    }

    fun part2(optimized : Boolean) {

        val grid = if (optimized){
            mutableMapOf<Point, Int>()
        } else {
            HashMap<Point, Int>()
        }

        fun followThis(instruction: Day6Instruction) {
            
            for (x in instruction.lowerPoint.x..instruction.upperPoint.x){
                for (y in instruction.lowerPoint.y .. instruction.upperPoint.y){
                    grid[Point(x, y)] = when (instruction.instruction){
                        INSTRUCTIONS.TOGGLE -> {
                             (grid[Point(x,y)] ?: 0) + 2
                        }
                        INSTRUCTIONS.OFF -> {
                            (grid[Point(x,y)] ?: 0) - 1
                        }
                        INSTRUCTIONS.ON -> {
                            (grid[Point(x,y)] ?: 0) + 1
                        }
                    }

                    if (grid[Point(x, y)] ?: -1 < 0){
                        grid[Point(x, y)] = 0
                    }

                }
            }

        }

        myListOfInstructions.forEach {
            followThis(it)
        }

        println(grid.values.sum())
    }



}

private enum class INSTRUCTIONS {
    ON,OFF,TOGGLE
}
private data class Day6Instruction(val lowerPoint: Point, val upperPoint: Point, val instruction: INSTRUCTIONS)
private data class Point(val x : Int, val y : Int)

