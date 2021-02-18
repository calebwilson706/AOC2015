
object Day4 {
    private const val input = "iwrupvqb"

    fun part1() {
        var shouldContinue = true
        var num = 0

        while (shouldContinue) {
            if (md5(input + "$num").subSequence(0,5) == "00000") {
                println(num)
                shouldContinue = false
            } else {
                num++
            }
        }
    }
    fun part2() {
        var shouldContinue = true
        var num = 346386

        while (shouldContinue) {
            if (md5(input + "$num").subSequence(0,6) == "000000") {
                println(num)
                shouldContinue = false
            } else {
                num++
            }
        }
    }
    fun allPartsSolution(num : Int) : Int {

        var counter  = if (num == 1) {
            1
        } else {
            val temp = allPartsSolution(num - 1)
            val extraFactor = "1" + "0".repeat(numberOfDigits(temp/2))

            temp + extraFactor.toInt()
        }

        while (true) {
            if (md5(input + "$counter").subSequence(0,num) == "0".repeat(num)) {
                println(counter)
                return(counter)
            } else {
                counter++
            }
        }
    }
}