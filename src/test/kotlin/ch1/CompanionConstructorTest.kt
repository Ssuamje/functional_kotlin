package ch1

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CompanionConstructorTest {
    data class Person(val name: String, val age: Int, val sex: Sex) {
        companion object {
            fun male(name:String, age:Int): Person
                = Person(name, age, Sex.MALE)
            fun female(name:String, age:Int): Person
                = Person(name, age, Sex.FEMALE)
        }
    }

    enum class Sex {
        MALE,
        FEMALE,
    }

    @DisplayName("코틀린의 동반자 객체를 이용한 생성자 테스트")
    @Test
    fun test() {
        Person.male("Namja", 20).sex shouldBe Sex.MALE
        Person.female("Yeoja", 20).sex shouldNotBe Sex.MALE shouldBe Sex.FEMALE

    }
}