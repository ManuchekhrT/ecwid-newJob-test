package tj.test.ecwid.thread_local


fun main(args: Array<String>) {
    testCase1()
    testCase2()
    testCase3()
}

fun testCase1() {
    val threadLocal = SimpleThreadLocal<Int>()
    threadLocal.set(42)

    val value = threadLocal.get()
    println(value?.get()) // выводит 42

    threadLocal.remove()

    val value2 = threadLocal.get()
    println(value2) // выводит null
}

fun testCase2() {
    val threadLocal = SimpleThreadLocal<List<Int>>()

   // Установить значение для текущего потока
    val list: MutableList<Int> = ArrayList()
    list.add(1)
    list.add(2)
    list.add(3)
    threadLocal.set(list)

    // Получить значение для текущего потока
    val value = threadLocal.get()
    println(value?.get()) // Выводит "[1, 2, 3]"


    threadLocal.remove()

    // Попытка получить значение после удаления
    val removedValue = threadLocal.get()
    println(removedValue) // Выводит "null"
}

fun testCase3() {
    val threadLocal = SimpleThreadLocal<String>()

    // Установить значение для текущего потока

    // Установить значение для текущего потока
    threadLocal.set("Hello, World!")

    // Получить значение для текущего потока

    // Получить значение для текущего потока
    val value = threadLocal.get()
    println(value?.get()) // Выводит "Hello, World!"


    threadLocal.remove()

    // Попытка получить значение после удаления

    // Попытка получить значение после удаления
    val removedValue = threadLocal.get()
    println(removedValue) // Выводит "null"
}