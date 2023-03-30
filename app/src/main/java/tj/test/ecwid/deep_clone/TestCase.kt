package tj.test.ecwid.deep_clone

fun testCaseDeepClone() {
    val man = Man("John", 30, listOf("New York", "Broadway"))
    val copy = CopyUtils.deepCopy(man) as Man

    copy.age = 22
    copy.name = "Jack"
    copy.favoriteBooks = listOf("asd1", "Asd")

    println(man)
    println(copy)
}