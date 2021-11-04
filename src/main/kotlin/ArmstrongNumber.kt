/*
Implement a Kotlin program to check armstrong number or not

Sample Test Case :
> Enter a number
> 153
> 153 armstrong number

> Enter a number
> 1234
> 1234 is not an armstrong number

*/
fun main() {
    println("Enter a number")
    var num = readLine()!!.toInt()
    var temp = num
    var sum = 0
    while (num > 0) {
        var lastDigit = num % 10
        var cubeResult = lastDigit * lastDigit * lastDigit
        sum += cubeResult
        num /= 10
    }
    if (sum == temp) {
        println("$temp is armstrong number")
    } else {
        println("$temp is not an armstrong number")
    }
}