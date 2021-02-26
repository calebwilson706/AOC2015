import kotlin.time.seconds

object Day15 {

    fun part1(){
        var current = 0

        for (f in 0..100) {
            for (c in 0..(100 - f)) {
                for (b in 0..(100 - f - c)){
                    val s = 100 - f - c - b

                    current = maxOf(current, getTotalForCombination(f,c,b,s))
                }
            }
        }

        println(current)

    }

    fun part2(){
        var current = 0

        for (f in 0..100) {
            for (c in 0..(100 - f)) {
                for (b in 0..(100 - f - c)){
                    val s = 100 - f - c - b

                    val totalAndCals = getTotalForPart2(f,c,b,s)

                    if (totalAndCals.second == 500) {
                        current = maxOf(current, totalAndCals.first)
                    }
                }
            }
        }

        println(current)

    }

    private fun getTotalForCombination(frosting : Int, candy : Int, butterscotch : Int, sugar : Int) : Int {
        val capacity = maxOf(4*frosting - butterscotch,0)
        val durability = maxOf(-2*frosting + 5*candy, 0)
        val flavor = maxOf(-candy + 5*butterscotch -2*sugar, 0)
        val texture = maxOf(2*sugar, 0)

        return capacity*durability*flavor*texture
    }

    private fun getTotalForPart2(frosting : Int, candy : Int, butterscotch : Int, sugar : Int) : Pair<Int, Int>{
        val calories = maxOf(5*frosting + 8*candy + 6*butterscotch + sugar, 0)

        return Pair(getTotalForCombination(frosting, candy, butterscotch, sugar),calories)
    }
}








