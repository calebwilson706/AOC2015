object Day24 {
    private val baseNumbers = listOf(
        1,
        2,
        3,
        5,
        7,
        13,
        17,
        19,
        23,
        29,
        31,
        37,
        41,
        43,
        53,
        59,
        61,
        67,
        71,
        73,
        79,
        83,
        89,
        97,
        101,
        103,
        107,
        109,
        113
    )


    fun part1() {
        var sumOfAll = baseNumbers.sum()

        val batchOnes = subsetSumPaths(baseNumbers, baseNumbers.size - 1, sumOfAll/3).groupBy { it.size }
        val oneAsLong : Long = 1
        println(
            batchOnes[batchOnes.keys.minOrNull()]!!.map { it.fold(oneAsLong) {acc, next -> acc*next } }.minOrNull()
        )
    }

    fun part2() {
        var sumOfAll = baseNumbers.sum()


        val batchOnes = subsetSumPaths(baseNumbers, baseNumbers.size - 1, sumOfAll/4).groupBy { it.size }
        val oneAsLong : Long = 1
        println(
            batchOnes[batchOnes.keys.minOrNull()]!!.map { it.fold(oneAsLong) {acc, next -> acc*next } }.minOrNull()
        )
    }

}

fun subsetSumPaths(forPath : List<Int>, n : Int, sum : Int) : List<List<Int>> {
    val combos = mutableListOf<List<Int>>()
    var currentMaxLength = 100

    fun containsSubsetSum(inputArr : List<Int>, innerN : Int, sum : Int, curr : List<Int>) {
        if (sum == 0) {
            currentMaxLength = curr.size
            combos.add(curr)
            return
        }

        if (sum < 0 || innerN < 0 || curr.size > currentMaxLength) {
            return
        }

        containsSubsetSum(inputArr,innerN - 1, sum, curr)

        val working = curr.toMutableList()
        working.add(inputArr[innerN])
        containsSubsetSum(inputArr,innerN - 1, sum - inputArr[innerN],working)

    }

    containsSubsetSum(forPath,n,sum,mutableListOf<Int>())

    return (combos)
}