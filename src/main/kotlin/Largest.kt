//Implement a Kotlin program to find the largest element in an array
fun main() {
    var array = doubleArrayOf(12.12,34.5,20.1,3.4,5.7,100.1)

    var largest = array[0]
    for (i in 1 until array.size){
        if (array[i] > largest) {
            largest = array[i]
        }
    }
    println("Largest element in array is $largest")
}