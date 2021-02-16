import java.io.File



object Day1 {
    private val myStr = File("/Users/calebjw/Documents/AdventOfCode/2015/inputs/Day1Input.txt").readText()

    val myBracketValues = mapOf<Char, Int>(
        Pair('(', 1),
        Pair(')', -1)
    )

    fun part1() {
        val map = getFrequencyMap(myStr.toList())
        println(map['(']!! - map[')']!!)
    }

    fun part1Optimized() {
        fun helper() : Int{
            var lowerIndex = 0
            var upperIndex = myStr.lastIndex - 1
            var total = 0

            while (lowerIndex != upperIndex) {
                total += myBracketValues[myStr[lowerIndex]]!! + myBracketValues[myStr[upperIndex]]!!
                lowerIndex++
                upperIndex--
            }

            return total
        }
        println(helper())
    }
    fun part2() {
        var currentFloor = 0

        fun helper() : Int {
            myStr.forEachIndexed { index, it ->
                currentFloor += when (it) {
                    '(' -> 1
                    else -> -1
                }

                if (currentFloor == -1) {
                    return (index + 1)
                }
            }
            return 0
        }

        println(helper())
    }

    fun part2Optimized() {

        fun helper () : Int {
            var currentIndex = 0
            var currentTotal = 0

            while (currentIndex < myStr.length){
                if (currentTotal == -1) return currentIndex
                val currVal = myStr[currentIndex]

                if (currVal != myStr[currentIndex + 1]){
                    currentIndex += 2
                } else {
                    currentTotal += (myBracketValues[currVal] ?: 0)
                    currentIndex++
                }

            }

            return 0
        }

        println(helper())

    }
}