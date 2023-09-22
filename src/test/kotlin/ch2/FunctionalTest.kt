package ch2

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FunctionalTest {

    @DisplayName("함수형 프로그래밍 테스트")
    @Test
    fun test() {
        // 일급 객체
        val capitalize = {str : String -> str.uppercase()}
        println(capitalize("hello world!")) // HELLO WORLD!
        /*-------------------------------------------------*/
        fun <T> transform(t: T, fn: (T)->T): T{
            return fn(t)
        }
        println(transform("hello world?", capitalize)) // HELLO WORLD?
        println(transform("hello world?", String::reversed)) // ?dlrow olleh
        /*-------------------------------------------------*/
        fun <T> extractInt(t: T, fn:(T) -> Int): Int {
            return fn(t)
        }
        println("result = ${extractInt("hello world!") { str: String -> str.length }}") // result = 12
        println("yet another = ${extractInt("helloworld!") {it.length}}")
    }

    @DisplayName("람다를 이용해 unless 키워드 DSL을 만들어보기")
    @Test
    fun test2() {
        fun unless(condition: Boolean, block: () -> Unit) {
            if (!condition) block()
        }
        val check = false
        unless(check) { print("check is false!!") }

        var willSideEffected = 10
        unless(check) { willSideEffected += 10 }
        willSideEffected shouldBe 20
    }

    @DisplayName("꼬리 재귀를 통한 최적화와 시간 측정")
    @Test
    fun test3() {
        fun printExecutionTime(body: () -> Unit) {
            val startTime = System.nanoTime()
            body()
            val endTime = System.nanoTime()
            println("Execution Time = ${endTime - startTime}")
        }

        fun nonTailRecursionFactorial(n:Int): Int{
            return if (n <= 1) { n }
            else { n * nonTailRecursionFactorial(n-1) }
        }

        tailrec fun tailRecursionFactorial(n:Int): Int{
            return if (n <= 1) { n }
            else { n * tailRecursionFactorial(n - 1) }
        }

        printExecutionTime { nonTailRecursionFactorial(50) }
        printExecutionTime { tailRecursionFactorial(50) }
    }

    @DisplayName("불변 리스트와 가변 리스트")
    @Test
    fun listTest() {
        val ints = listOf(1, 2, 3, 4, 5)
        ints.forEach(::println)
        ints.forEach { println(it) }
        println("-----------------------------------------------")
        val doubledInts:List<Int> = listOf() // immutable
        for (i in ints) {
            doubledInts.plus(i * 2)
        }
        doubledInts.forEach(::println) // nothing
        println("-----------------------------------------------")
        val doubledInts2:MutableList<Int> = mutableListOf()
        for (i in ints) {
            doubledInts2.add(i * 2)
        }
        doubledInts2.forEach(::println) // 2, 4, 6, 8, 10
        println("-----------------------------------------------")
        val mutablePoweredDoubledInts = doubledInts2.map { it.times(2) }
        mutablePoweredDoubledInts.forEach(::println) // 4, 8, 12, 16, 20
    }

    @DisplayName("컬렉션 메서드 알아보기")
    @Test
    fun collectionMethodTest() {
        val nums = listOf(1, 2, 3, 4)
        val sum = nums.fold(0) { acc, e ->
            println("acc = $acc, e = $e")
            acc + e }
        val sum2 = nums.reduce { acc, e ->
            println("acc = $acc, e = $e")
            acc + e }
        sum shouldBe sum2
    }

    sealed class FunList<out T> {
        object Nil: FunList<Nothing>() // 빈 리스트로서, 종료 지점에 대한 싱글턴
        data class Cons<out T>(val head: T, val tail: FunList<T>): FunList<T>()
    }
    @DisplayName("함수형 리스트")
    @Test
    fun functionalListTest() {
        val unPrettyInitNumbers = FunList.Cons(1,
            FunList.Cons(2,
                FunList.Cons(3,
                    FunList.Cons(4, FunList.Nil)))
        )
        // 더 세련된 방법으로 초기화하기
        fun intListOf(vararg numbers: Int): FunList<Int> {
            return if (numbers.isEmpty()) { FunList.Nil }
            else {FunList.Cons(numbers.first(), intListOf(*numbers.drop(1).toTypedArray().toIntArray()))}
        }
        val nums = listOf(1, 2, 3, 4) // List<Int>[1, 2, 3, 4]
        val initNumbers = intListOf(*nums.toIntArray()) // intListOf(1, 2, 3, 4)
        /*----------------------------------------------------------------------*/



    }
}