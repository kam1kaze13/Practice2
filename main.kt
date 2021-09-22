open class Person (var name: String, var birthYear: Int){
    var age = 2021 - birthYear
}

class Student (var stuName: String, var stuBirthYear: Int,
               var averageGrade: Double, var extramural: Boolean = false)
    : Person(stuName,stuBirthYear){
    override fun toString(): String {
        return ("name: $stuName, birth year: $stuBirthYear, average grade: $averageGrade, extramural: $extramural")
    }
}

class Lecturer (var lecName: String, var lecBirthYear: Int,
                var degree: String, var experienceFrom: Int)
    : Person(lecName,lecBirthYear){
    override fun toString(): String {
        return ("name: $lecName, birth year: $lecBirthYear, degree: $degree, experienceFrom: $experienceFrom")
    }
}

fun main() {
    val persons = mutableListOf<Person>(Student("Alex",2000,4.1),
        Student("Ivan",2001,3.7,true),
        Student("Max",1999,4.3),
        Student("Michael",2000,4.6,true),
        Student("John",2000,4.8),
        Lecturer("Ivan Petrov",1965,"professor",1995),
        Lecturer("Petr Petrov",1971,"candidate",2001),
        Lecturer("Petr Ivanov",1962,"professor",1988),
        Lecturer("Ivan Petrov",1976,"candidate",2006),
        Lecturer("Ivan Petrov",1958,"doctor",1986)
    )
    println("Without sort:")
    println(persons.joinToString( separator = "\n"))

    println("After sortByAge:")
    sortByAge(persons)

    println("After sortByNameStudents:")
    val sortByNameStudents = persons.sortByNameStudents()
    println(sortByNameStudents.joinToString( separator = "\n"))

    println("After sortByAverageGrade:")
    val sortByAverageGrade = persons.sortByAverageGrade(true)
    println(sortByAverageGrade.joinToString( separator = "\n"))
}

fun sortByAge(persons:List<Person>):List<Person> {
    val sortedList = persons.sortedWith(compareBy(Person::age)).reversed()
    println(sortedList.joinToString( separator = "\n"))
    return sortedList
}

fun MutableList<Person>.sortByNameStudents():List<Student>
{
    return this.filter{ it is Student }.sortedByDescending{ it.name } as List<Student>
}

fun MutableList<Person>.sortByAverageGrade(exceptExtramural : Boolean):List<Student>
{
    if (exceptExtramural) {
        return this.sortByNameStudents().filter{it.extramural == false}.sortedByDescending{ it.averageGrade } as List<Student>
    } else
    {
        return this.sortByNameStudents().sortedByDescending{ it.averageGrade } as List<Student>
    }

}