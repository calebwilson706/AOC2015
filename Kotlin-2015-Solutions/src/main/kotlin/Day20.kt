import kotlin.math.min

object Day20 {
    //add all factors of factors and x10

    fun part1() {
        var presentsAtEachHouse = Array(34000000) {
            1
        }

        for (elf in 2..presentsAtEachHouse.size) {
            for (house in ((elf - 1) until presentsAtEachHouse.size).step(elf)) {
                presentsAtEachHouse[house] += elf * 10
            }
            println(elf)
            if (presentsAtEachHouse[elf - 1] >= 34000000) {
                println(elf - 1)
                break
            }
        }
    }

    fun part2() {
        val presentsAtEachHouse = Array(34000000) {
            1
        }

        for (elf in 2..presentsAtEachHouse.size) {
            for (house in ((elf - 1) until min(elf*50,34000000)).step(elf)) {
                presentsAtEachHouse[house] += elf * 11
            }
            presentsAtEachHouse[elf*11]
            if (presentsAtEachHouse[elf - 1] >= 34000000) {
                println(elf)
                break
            }
        }
    }
}