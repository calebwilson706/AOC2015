import java.math.BigInteger
import java.security.MessageDigest

fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

fun getFrequencyMap(list: List<*>) = list.groupingBy { it }.eachCount()


fun numberOfDigits(num : Int) : Int {
    return num.toString().length
}