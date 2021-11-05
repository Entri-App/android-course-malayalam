/*
Implement a Kotlin Program to Find Factorial of a Number using recursion

Sample Test Case :
 		> Enter a number
		> 6
		> Factorial of 6 is 720
* */
fun main() {

    println("Enter a number")
    var num = readLine()!!.toInt()
    var result = findFact(num)
    println("Factorial of $num is $result")
}

fun findFact(num : Int) : Long {

    if (num >= 1) {
        return num * findFact(num - 1)
    } else {
        return 1
    }

}