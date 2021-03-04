object Day25 {

    fun part1() {
        val column = 3083
        val row = 2978

        val max = addUpTo(column,1).toInt() + extraTotal(column,row)
        //println(max)

        val multiplier : Long = 252533
        val divider : Long = 33554393

        var lastAns : Long = 20151125
        
        (1 until max).forEach{ _ ->
            lastAns = (lastAns*multiplier) % divider
        }

        println(lastAns)
    }
}



fun addUpTo(n : Int, from : Int) : Double {
    return (n*0.5*(from + n))
}

fun extraTotal(start : Int, end : Int) : Int {
    var total = 0
    var index = 0

    while (index < end - 1) {
        total += start + index
        index += 1
    }

    return  total
}
