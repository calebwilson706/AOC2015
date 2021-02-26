import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import java.util.Collections.swap
import kotlin.system.measureTimeMillis
import java.util.ArrayList
import kotlin.system.measureNanoTime


fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

fun getFrequencyMap(list: List<*>) = list.groupingBy { it }.eachCount()


fun numberOfDigits(num : Int) : Int {
    return num.toString().length
}

fun String.containsTwoDoubleLetters() : Boolean {
    var total = 0
    var firstSet : Char? = null

    (0 until this.length - 1).forEach {
        val curr = this[it]
        if (firstSet !=  curr && curr == this[it + 1]){
            total += 1
            firstSet = curr
        }
    }

    return total >= 2
}

fun timeTest(callback: () -> (Unit), num: Int ){
    val time = measureTimeMillis {
        callback()
    }

    println("$time for test $num")
}


fun <V> List<V>.permutations(): List<List<V>> {
    val retVal: MutableList<List<V>> = mutableListOf()

    fun generate(k: Int, list: List<V>) {
        // If only 1 element, just output the array
        if (k == 1) {
            retVal.add(list.toList())
        } else {
            for (i in 0 until k) {
                generate(k - 1, list)
                if (k % 2 == 0) {
                    swap(list, i, k - 1)
                } else {
                    swap(list, 0, k - 1)
                }
            }
        }
    }

    generate(this.count(), this.toList())
    return retVal
}