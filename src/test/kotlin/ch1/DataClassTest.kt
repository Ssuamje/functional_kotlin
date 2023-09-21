package ch1

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DataClassTest {
    data class Person(val name: String, val age: Int)

    @DisplayName("데이터 클래스 사용해보기")
    @Test
    fun test() {
        val john = Person("John", 30)
        val (name, age) = john
        println("name = $name, age = $age")
        val (thisIsName:String, _) = john
//        print("thisIsName = $thisIsName, age = $_") <- unresolved '_'
        print("thisIsName = $thisIsName")
    }
}