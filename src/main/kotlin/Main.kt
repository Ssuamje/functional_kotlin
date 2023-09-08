class MyClass{

}
fun main(args: Array<String>) {
    val instance = MyClass();
    println("Hello World!" + instance)

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}