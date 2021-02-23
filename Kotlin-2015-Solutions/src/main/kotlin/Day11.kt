object Day11 {
    private const val input = "hepxcrrq"


    private fun incrementString(str : String) : String {
        val unchanged = str.dropLast(1)


        val newLast = str.last().toInt() + 1

        return if (newLast > 'z'.toInt()){
            incrementString(unchanged) + 'a'
        } else {
            unchanged + newLast.toChar()
        }
    }

    private fun incrementOptimised(str : String) : String {
        //println(str)
        var indexOfNotAllowed : Int? = null

        var currIndex = 0

        for (character in str) {
            if (character == 'i' || character == 'o' || character == 'l') {
                indexOfNotAllowed = currIndex
                break
            }
            currIndex++
        }

        return if (indexOfNotAllowed != null && indexOfNotAllowed != str.length - 1){
            val left = str.subSequence(0,indexOfNotAllowed + 1)
            var temp = incrementString(left as String)
            temp.padEnd(8,'a')
        } else {
            incrementString(str)
        }

    }

    fun part1() {
        var currentPassword = input

        while (true) {
            if(meetsRule1(currentPassword)){
                if (meetsRule2(currentPassword)){
                    if(meetsRule3(currentPassword)){
                        println(currentPassword)
                        return
                    }
                }
            }

            currentPassword = incrementString(currentPassword)
        }
    }
    fun bothPartsOptimised(part : Int = 1) {
        var currentPassword = input
        var total = 0

        while (true) {
            if(meetsRule1(currentPassword)){
                if (meetsRule2(currentPassword)){
                    if(meetsRule3(currentPassword)){
                        total++
                        if (total == part) {
                            println(currentPassword)
                            return
                        }
                    }
                }
            }

            currentPassword = incrementOptimised(currentPassword)
        }
    }

    fun part2() {
        var currentPassword = input
        var counter = 0
        while (true) {
            if(meetsRule1(currentPassword)){
                if (meetsRule2(currentPassword)){
                    if(meetsRule3(currentPassword)){
                        if (counter == 1){
                            println(currentPassword)
                            return
                        } else {
                            counter++
                        }
                    }
                }
            }

            currentPassword = incrementString(currentPassword)
        }
    }

    fun part1optimised() {
        bothPartsOptimised()
    }
    fun part2optimised() {
        bothPartsOptimised(2)
    }

    private fun meetsRule1(str : String) : Boolean {
        var current = str.first().toInt()

        (0 until str.length - 2).forEach { i ->
            val next = str[i + 1].toInt()
            val after = str[i + 2].toInt()

            if ( current == (next - 1) && current == (after - 2) ) {
                return true
            }

            current = next
        }

        return false
    }
    private fun meetsRule2(str : String) : Boolean {
        return !(str.contains('l')  || str.contains('i') || str.contains('o'))
    }

    private fun meetsRule3(str: String) = str.containsTwoDoubleLetters()
}