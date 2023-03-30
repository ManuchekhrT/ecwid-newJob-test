package tj.test.ecwid.thread_local

import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

/*
  ConcurrentHashMap для хранения значений,
      чтобы обеспечить безопасный доступ из нескольких потоков.
      - Метод get() сначала проверяет наличие значения для текущего потока и,
      если его нет, использует initialValue для создания нового значения.
      - Метод set() устанавливает значение для текущего потока,
      - Метод remove() удаляет значение для текущего потока.

  WeakReference:
      Неожиданная остановка потоков может привести к тому, что значения,
      хранимые в ThreadLocal, останутся в памяти и не будут освобождены.
      Для решения этой проблемы можно использовать слабые ссылки (WeakReference).
      В этом случае, если поток умирает, объект, на который ссылается
      ThreadLocal, будет удален из памяти автоматически.
 */
class SimpleThreadLocal<T> {
    private val map = ConcurrentHashMap<Long, WeakReference<T>>()

    fun get(): WeakReference<T>? {
        return map[Thread.currentThread().id]
    }

    fun set(value: T) {
        map[Thread.currentThread().id] = WeakReference(value)
    }

    fun remove() {
        map.remove(Thread.currentThread().id)
    }
}

