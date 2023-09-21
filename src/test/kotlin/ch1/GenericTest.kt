package ch1

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

interface Machine<T> {
    fun process(product: T)
}

class GenericTest {
    data class Dough(val ingredients:String, val product: String)

    class Oven: Machine<Dough> {
        override fun process(product: Dough) {
            println("Baking ${product.product} by ${product.ingredients}...")
        }
    }

    @DisplayName("제네릭 테스트")
    @Test
    fun test() {
        val oven = Oven()
        oven.process(Dough("flour", "bread"))
    }
}