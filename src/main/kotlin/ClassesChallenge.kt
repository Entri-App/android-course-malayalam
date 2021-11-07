import java.util.*

fun main() {

    println("Enter number of students : ")
    var numOfStuds = readLine()!!.toInt()

    var array = arrayOfNulls<Student>(numOfStuds)

    for (i in array.indices) {
        println("Enter name of student with roll num ${i + 1}")
        var name = readLine()!!
        println("Enter address of student with roll num ${i + 1}")
        var address = readLine()!!

        var student = Student(name, address)
        array[i] = student

    }

    var choice = 0
    do {
        println("Enter you choice :")
        println("1. Delete student")
        println("2. Show student details")
        println("3. Exit")

        choice = readLine()!!.toInt()

        if (choice == 3) {
            break
        }

        println("Enter student roll number")
        var rollNum = readLine()!!.toInt()

        when (choice) {
            1 -> {
                array[rollNum] = null
            }
            2 -> {

                if ((array[rollNum] == null)) {
                    println("Student does not exist")
                } else {
                    array[rollNum - 1]?.printData()
                }

            }
            else -> {
                println("Entered choice is invalid, please enter valid one")
            }
        }


    } while (choice != 3)


}

class Student(var name: String, var address: String) {

    fun printData() {
        println("Student Name : $name")
        println("Student Address : $address")
    }

}