fun main() {
    /*
    Implement a Kotlin program to Reverse a number

            Sample Test Case :
    > Enter a number
    > 432
    > Reverse is 234

    > Enter a number
    > 1234
    > Reverse is 4321
    */
    println("Enter a number")
    var num = readLine()!!.toInt()

    var rev = 0
    while (num > 0) {
        val rem = num % 10
        rev = (rev * 10) + rem
        num /= 10
    }
    println("Reverse is $rev")
}