//Implement a Kotlin Program to find the average of a hardcoded array
fun main() {
    var array = doubleArrayOf(1.0,2.0,3.0)
    var sum = 0.0
    for (arrayItem in array) {
        sum += arrayItem
    }
    val average = sum / array.size
    println("Average of array is $average")
}