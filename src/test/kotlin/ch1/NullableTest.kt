package ch1

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NullableTest {
    @DisplayName("Optional 타입 사용해보기")
    @Test
    fun test() {
        var nullable: String? = null
        val notNullable: String = "not null"

//        nullable!!.length shouldBe null <- NPE
        nullable?.length shouldBe null // 항상 null이므로 null
        notNullable.length shouldBe 8

        var myInt = nullable?.length ?: 0
        myInt shouldBe 0

        nullable = "now it is not null"
        myInt = nullable.length
        myInt shouldNotBe null shouldBe nullable.length
    }
}