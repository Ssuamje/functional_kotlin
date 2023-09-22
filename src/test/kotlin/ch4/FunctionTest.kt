package ch4

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FunctionTest {
    @DisplayName("Pair를 이용한 반환")
    @Test
    fun pairTest() {
        fun myFun(name:String, age:Int): Pair<String, Int> {
            return Pair(name, age)
        }
        val (name, age) = myFun("홍길동", 20)
        println("name = $name, age = $age")
    }


    @DisplayName("이것도 되나? - 3개 반환해보기")
    @Test
    fun trioTest() {
        data class Trio<out A, out B, out C>(val first: A, val second: B, val third: C)
        fun myFun(name:String, age:Int, address:String): Trio<String, Int, String> {
            return Trio(name, age, address)
        }
        val (name, age, address) = myFun("홍길동", 20, "서울시")
        println("name = $name, age = $age, address = $address")

        class PersonalInfo(val name:String, val age:Int, val address:String) {
            operator fun component1() = name
            operator fun component2() = age
            operator fun component3() = address
            operator fun component4() = name + address
            operator fun not() = print("not operator")
        }
        val (name2, age2, address2, nameAddress) = PersonalInfo("홍길동", 20, "서울시")
        println("name = $name2, age = $age2, address = $address2, nameAddress = $nameAddress")
        !PersonalInfo("홍길동", 20, "서울시") // not operator
    }

    @DisplayName("확장함수")
    @Test
    fun extendedFunction() {
        fun String.extended(): String {
            return this + this
        }
        println("Hello".extended()) // HelloHello

        class PersonalInfo(val name:String, val age:Int, val address:String) {
            operator fun component1() = name
            operator fun component2() = age
            operator fun component3() = address
        }

        operator fun PersonalInfo.component4(): String {
            return this.name + this.address
        } // 이런 식으로 코드 블록 안에서 확장 함수를 명시할 수 있겠군..

        val (name, age, address, nameAddress) = PersonalInfo("홍길동", 20, "서울시")
        println("name = $name, age = $age, address = $address, nameAddress = $nameAddress")

        operator fun PersonalInfo.component1(): String {
            return "고정된 값"
        } // 적용되지 않는다. Kotlin에서는 기존 멤버 메서드가 확장 함수보다 우선되기 때문이다.

        val (name2, age2, address2, nameAddress2) = PersonalInfo("홍길동", 20, "서울시")
        println("name = $name2, age = $age2, address = $address2, nameAddress = $nameAddress2")
    }

    @DisplayName("옵셔널한 매개변수에 대한 디폴트 값 할당하기")
    @Test
    fun optionalDefaultTest() {
        fun ifNullReturnFortyTwo(value: Int = 42): Int {
            return value
        }

        println("ifFalsyReturnFortyTwo() = ${ifNullReturnFortyTwo()}") // 42
        println("ifFalsyReturnFortyTwo(0) = ${ifNullReturnFortyTwo(0)}") // 0
        println("ifFalsyReturnFortyTwo(1) = ${ifNullReturnFortyTwo(1)}") // 1
    }

    @DisplayName("함수")
    @Test
    fun functionTest() {
        val myFun = fun() { println("hello~") }
        myFun() // hello~
        fun performOperationOnEven(num:Int, operation:(Int) -> Int): Int {
            return if (num % 2 == 0) {
                operation(num)
            } else {
                num
            }
        }
        println(performOperationOnEven(4) { num -> num * num }) // 16
        println(performOperationOnEven(5) { num -> num * num }) // 5
        println(performOperationOnEven(6) { it * it }) // 36
    }

    @DisplayName("단일 표현 함수")
    @Test
    fun singleExpressionFunction() {
        fun square(num:Int) = num * num
        println(square(2)) // 4
        println(square(3)) // 9
        fun returnsSquareLambda() = { num:Int -> num * num } // 고차 함수
        returnsSquareLambda()(2) // 4
        fun square2(num:Int): () -> Int = {num * num}
        println(square2(2)) // (Int) -> Int, 파라미터로 받은 num을 제곱하는 함수를 반환한다.
        println(square2(2)()) // 4
    }

    @DisplayName("명명 파라미터")
    @Test
    fun namedParameters() {
        data class Customer(val name:String, val age:Int, val address:String)
        val customer = Customer(
            name = "이름이요",
            age = 20,
            address = "주소") // 이러면 빌더가 필요 없겠군
        println(customer) // Customer(name=이름이요, age=20, address=주소)
    }

    @DisplayName("수신 객체(Receiver)와 람다")
    @Test
    fun receiverAndLambda() {
        "hello".also { println(it) } // hello
        "hello".also { println(this) }// FunctionTest@[hashcode] <- this가 FunctionTest를 가리킨다.
        "hello".also { print(it) }.also { print(" world") }.also { println("!") } // hello world!
        println("hello".let { "$it World!" }) // hello World! <- 반환값을 가진다.
        println("hello".let { it.length.toDouble() }) // 5.0
        "hello".apply { println(this) }
        // hello <- this가 "hello"를 가리킨다. apply의 정적 스코프에서 this는 수신 객체를 가리키기 때문.
        // 즉, apply라는 함수의 선언부에서 block: T.() -> Unit으로 선언되어있는데,
        // 이미 이 선언 시점에 this는 T를 가리키게 된다.
        with("hello") { println(this) } // hello
    }

}
