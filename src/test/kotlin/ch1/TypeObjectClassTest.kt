package ch1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TypeObjectClassTest {

	@DisplayName("타입 추론")
	@Test
	fun typeInference() {
		class MyClass(val name: String){
			fun salute() {
				println("My Name is $name")
			}
		}

		// 타입 추론
		var inference = MyClass("hellow");
		inference.salute();
	}

	@DisplayName("상속")
	@Test
	fun inheritance() {
		//open을 적지 않으면 기본적으로 final이다.
	    open class Parent(val name: String) {
			// fun 또한 open 해주어야 override가 가능하다.
			open fun salute() {
				println("my name is $name")
			}
		}

		// 부모의 생성자를 직접적으로 호출하는 느낌으로 사용하는 듯
		class Child(name:String): Parent(name) {
			override fun salute() {
				println("I'm a child and my name is $name")
			}
		}

		val parent = Parent("Father")
		val child = Child("Kid")

		parent.salute()
		child.salute()
	}

	@DisplayName("추상 클래스")
	@Test
	fun abstract() {
	    abstract class Bread(val filling: String) {
			abstract val maker:String;
			abstract fun whatIsIn()
		}

	    class CornBread(filling:String,
						override val maker:String): Bread(filling) {
			override fun whatIsIn() {
				println("It is filled with $filling")
			}

			fun whoIsMaker() {
				println("Its maker is $maker")
			}
		}

		val cornBread = CornBread("CORN CREAM", "Sanan")

		cornBread.whatIsIn()
		cornBread.whoIsMaker()
	}

	@DisplayName("인터페이스")
	@Test
	fun interfaceTest() {
	}
}
