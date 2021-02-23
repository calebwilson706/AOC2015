import java.math.BigInteger
import java.security.MessageDigest
import kotlin.system.measureTimeMillis

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