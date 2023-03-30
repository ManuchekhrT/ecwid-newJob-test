package tj.test.ecwid

import tj.test.ecwid.deep_clone.testCaseDeepClone
import tj.test.ecwid.thread_local.testCaseThreadLocal1
import tj.test.ecwid.thread_local.testCaseThreadLocal2
import tj.test.ecwid.thread_local.testCaseThreadLocal3


fun main(args: Array<String>) {
    //Deep-clone
    testCaseDeepClone()

    //ThreadLocal
    testCaseThreadLocal1()
    testCaseThreadLocal2()
    testCaseThreadLocal3()
}
