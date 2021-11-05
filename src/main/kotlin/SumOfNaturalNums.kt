/*
Implement a Kotlin Program to Find the Sum of Natural Numbers using Recursion

Sample Test Case :
> Enter a number
> 20
> Sum is 210

> Enter a number
> 5
> Sum is 15
*/

fun main() {
    println("Enter a number")
    var num = readLine()!!.toInt()
    var sum = findSum(num)
    println(sum)

}

fun findSum(num: Int): Int {
    if (num != 0) {
        val sum = num + findSum(num - 1)
        println("Sum : $sum")
        return sum
    } else {
        println("num : $num")
        return num
    }
}

