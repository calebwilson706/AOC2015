import java.io.File
private val myStrDay13 = File("/Users/calebjw/Documents/AdventOfCode/2015/inputs/Day13Input.txt").readText()

object Day13 {

    fun part1() {
        var currentAnswer = 0
        val possibleCombos = myNames.permutations()
        println(relationships)


        for (list in possibleCombos){
            currentAnswer = maxOf(currentAnswer, calculateTotal(list))
        }

        print(currentAnswer)
    }

    fun part2() {
        val possibleCombos = myNamesPart2.permutations()

        var currentAnswer = 0
        println(relationships)


        for (list in possibleCombos){
            currentAnswer = maxOf(currentAnswer, calculateTotalWithMe(list))
        }

        print(currentAnswer)

    }
    private fun calculateTotalWithMe(forList : List<String>) : Int {
        var totalChange = 0



        (0..8).forEach { index ->
            if (forList[index] != "Caleb") {
                val connections = relationships.filter { it.person == forList[index] }

                val connectionBefore = if (index != 0) {
                    val next = forList[index - 1]

                    if (next != "Caleb") {
                        connections.first { it.theOtherPerson == next }
                    } else {
                        Relationship("",0,"")
                    }

                } else {
                    if (forList.last() != "Caleb") {
                        connections.first { it.theOtherPerson == forList.last() }
                    } else {
                        Relationship("",0,"")
                    }
                }

                val connectionAfter = if (index != 8) {
                    val next = forList[index + 1]

                    if (next != "Caleb") {
                        connections.first { it.theOtherPerson == next }
                    } else {
                        Relationship("",0,"")
                    }
                } else {
                    if (forList.first() != "Caleb") {
                        connections.first { it.theOtherPerson == forList.first() }
                    } else {
                        Relationship("",0,"")
                    }
                }

                totalChange += (connectionAfter.impact + connectionBefore.impact)
            }
        }

        return totalChange
    }

    private fun calculateTotal(forList : List<String>) : Int {
        var totalChange = 0

        (0..7).forEach { index ->
            val connections = relationships.filter { it.person == forList[index]}

            val connectionBefore = if (index != 0) {
                connections.first { it.theOtherPerson == forList[index - 1] }
            } else {
                connections.first { it.theOtherPerson == forList.last() }
            }

            val connectionAfter = if (index != 7) {
                connections.first { it.theOtherPerson == forList[index + 1] }
            } else {
                connections.first { it.theOtherPerson == forList.first() }
            }

            totalChange += (connectionAfter.impact + connectionBefore.impact)
        }

        return totalChange
    }
}

private var relationships = getTheRelationships()

//work out all possible combinations (256 of them)
//calculate total for each group
//use class below

//class Person(var name : String) {
//    var impactsOnTotal : MutableMap<String, Int> = mutableMapOf()
//
//    init {
//        val myRelationships = getTheRelationships().filter { it.person == name }
//
//        myRelationships.forEach {
//            impactsOnTotal[it.theOtherPerson] = it.impact
//        }
//    }
//}


data class Relationship(val person : String, val impact : Int, val theOtherPerson : String)
private val myNames = listOf("Alice","Bob","Carol","David","Eric","Frank","George","Mallory")
private val myNamesPart2 = myNames + "Caleb"
//class ThePeople {
//    var people : List<Person> = myNames.map { Person(it) }
//}

private fun getTheRelationships(): MutableList<Relationship> {
    val lines = myStrDay13
        .split(".")
        .dropLast(1)
    val answers = mutableListOf<Relationship>()
    //print(lines)
    val regexStatement = "([a-zA-Z]+) would ([a-z]{4}) ([0-9]+) happiness units by sitting next to ([a-zA-Z]+)".toRegex()
    lines.forEach {
        val (name1, sign, size, name2) = regexStatement.find(it)!!.destructured

        val theImpactTotal = if (sign == "lose") {
            -(size.toInt())
        } else {
            size.toInt()
        }

        answers.add(Relationship(name1,theImpactTotal,name2))
    }

    return answers
}
