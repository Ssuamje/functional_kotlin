package ch1

import io.kotest.core.spec.style.StringSpec

class KotlinTestPlease {
    class TestForKotlin: StringSpec({
        print("hello?")
        "Kotlin test" {
            println("Kotlin test")
        }
    })
}