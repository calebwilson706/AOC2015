fun main(args: Array<String>){
    val currentDay = Day16()

    timeTest(currentDay::part1,1)
    timeTest(currentDay::part1optimised,2)
    timeTest(currentDay::part2,3)
    timeTest(currentDay::part2optimised,4)
}