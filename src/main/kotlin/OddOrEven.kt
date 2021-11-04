fun main() {
    /*Implement a Kotlin Program to Check Whether a Number is Positive or Negative

    Sample Test Case :
    > Enter a number
    > 4
    > Number 4 is positive

    > Enter a number
    > -1
    > Number -1 is negative*/

    println("Enter a number")
    val num = readLine()!!.toInt()

    if (num > 0) {
        println("Number $num is positive")
    } else if (num < 0) {
        println("Number $num is negative")
    } else {
        println("Number is zero")
    }
}