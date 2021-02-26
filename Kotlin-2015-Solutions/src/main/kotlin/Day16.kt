import java.io.File

private val myStrDay13 = File("/Users/calebjw/Documents/AdventOfCode/2015/inputs/Day16Input.txt").readText()

class Day16 {
    var aunties = mutableListOf<Sue>()
    val props = listOf("children","cats","samoyeds","pomeranians","akitas","vizslas","goldfish","trees","cars","perfumes")
    init {
        val lines = myStrDay13.split("\n")
        lines.dropLast(1)

        lines.forEach {
            val (number)  = "Sue ([0-9]+):.*".toRegex().find(it)!!.destructured
            val items = mutableMapOf<String, Int>()

            props.forEach { prop ->
                if (it.contains(prop)) {
                    val (temp) = ".* $prop: ([0-9]+)".toRegex().find(it)!!.destructured
                    items[prop] = temp.toInt()
                }
            }
            //println(number.toInt())
            //println(items)

            aunties.add(Sue(
                number.toInt(),
                items["children"],
                items["cats"],
                items["samoyeds"],
                items["pomeranians"],
                items["akitas"],
                items["vizslas"],
                items["goldfish"],
                items["trees"],
                items["cars"],
                items["perfumes"]
            )
            )
        }
    }

    fun part1() {
        val searchAuntie = Sue(0,3,7,2,3,0,0,5,3,2,1)

        val possibleSet1 = aunties.filter { it.children == searchAuntie.children || it.children == null }
        val possibleSet2 = possibleSet1.filter { it.cats == searchAuntie.cats || it.cats == null }
        val possibleSet3 = possibleSet2.filter {
            (it.samoyeds == searchAuntie.samoyeds || it.samoyeds == null) && (it.pomeranians == searchAuntie.pomeranians || it.pomeranians == null)
        }
        val possibleSet4 = possibleSet3.filter {
            (it.akitas == searchAuntie.akitas || it.akitas == null) && (it.vizslas == searchAuntie.vizslas || it.vizslas == null)
        }
        val possibleSet5 = possibleSet4.filter {
            (it.goldfish == searchAuntie.goldfish || it.goldfish == null) && (it.trees == searchAuntie.trees || it.trees == null)
        }
        val possibleSet6 = possibleSet5.filter {
            (it.cars == searchAuntie.cars || it.cars == null) && (it.perfumes == searchAuntie.perfumes || it.perfumes == null)
        }

        println(possibleSet6.first().number)
    }

    fun part1optimised() {
        for (item in aunties) {

            if (item.children != 3 && item.children != null){
                continue
            }
            if (item.cats != 7 && item.cats != null){
                continue
            }
            if (item.samoyeds != 2 && item.samoyeds != null){
                continue
            }
            if (item.pomeranians != 3 && item.pomeranians != null){
                continue
            }
            if (item.akitas != 0 && item.akitas != null){
                continue
            }
            if (item.vizslas != 0 && item.vizslas != null){
                continue
            }
            if (item.goldfish != 5 && item.goldfish != null){
                continue
            }
            if (item.trees != 3 && item.trees != null){
                continue
            }
            if (item.cars != 2 && item.cars != null){
                continue
            }
            if (item.perfumes != 1 && item.perfumes != null){
                continue
            }

            println(item.number)
            break
        }
    }

    fun part2() {
        val searchAuntie = Sue(0,3,7,2,3,0,0,5,3,2,1)

        val possibleSet1 = aunties.filter { it.children == searchAuntie.children || it.children == null }
        val possibleSet2 = possibleSet1.filter { (it.cats ?: 1000) > searchAuntie.cats!! }

        val possibleSet3 = possibleSet2.filter {
            (it.samoyeds == searchAuntie.samoyeds || it.samoyeds == null) && ((it.pomeranians  ?: -1) < searchAuntie.pomeranians!!)
        }
        val possibleSet4 = possibleSet3.filter {
            (it.akitas == searchAuntie.akitas || it.akitas == null) && (it.vizslas == searchAuntie.vizslas || it.vizslas == null)
        }
        val possibleSet5 = possibleSet4.filter {
            ((it.goldfish ?: -1) < searchAuntie.goldfish!!) && ((it.trees ?: 1000) > searchAuntie.trees!!)
        }
        val possibleSet6 = possibleSet5.filter {
            (it.cars == searchAuntie.cars || it.cars == null) && (it.perfumes == searchAuntie.perfumes || it.perfumes == null)
        }

        println(possibleSet6.first().number)
    }
    fun part2optimised() {
        for (item in aunties) {

            if (item.children != 3 && item.children != null){
                continue
            }
            if (item.cats != 7 && item.cats != null){
                continue
            }
            if (item.samoyeds != 2 && item.samoyeds != null){
                continue
            }
            if ((item.pomeranians ?: -1) > 3 && item.pomeranians != null){
                continue
            }
            if (item.akitas != 0 && item.akitas != null){
                continue
            }
            if (item.vizslas != 0 && item.vizslas != null){
                continue
            }
            if ((item.goldfish ?: -1) > 5 && item.goldfish != null){
                continue
            }
            if ((item.trees ?: 1000) < 3 && item.trees != null){
                continue
            }
            if ((item.cars ?: 1000) < 2 && item.cars != null){
                continue
            }
            if (item.perfumes != 1 && item.perfumes != null){
                continue
            }

            println(item.number)
            break
        }
    }
}

class Sue(
    var number : Int,
    var children : Int?,
    var cats : Int?,
    var samoyeds : Int?,
    var pomeranians : Int?,
    var akitas : Int?,
    var vizslas : Int?,
    var goldfish : Int?,
    var trees : Int?,
    var cars : Int?,
    var perfumes : Int?,

)