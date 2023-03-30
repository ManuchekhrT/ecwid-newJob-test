package tj.test.ecwid.deep_clone

import java.lang.reflect.Array

object CopyUtils {

    fun deepCopy(obj: Any): Any {
        // Проверка на null
        if (obj == null) {
            return obj
        }

        // Получаем класс объекта
        val clazz = obj.javaClass

        val copy = when {
            clazz.isPrimitive || clazz == java.lang.String::class.java -> obj
            clazz.isArray -> {
                if (obj is kotlin.Array<*>) { // Проверяем, что obj является массивом
                    val componentType = clazz.componentType
                    val length = Array.getLength(obj)
                    val copy = Array.newInstance(componentType, length)
                    for (i in 0 until length) {
                        Array.set(copy, i, deepCopy(Array.get(obj, i)))
                    }
                    copy
                } else {
                    obj // Если obj не является массивом, возвращаем его без изменений
                }
            }
            clazz == java.lang.Integer::class.java || clazz == java.lang.Boolean::class.java ||
                    clazz == java.lang.Long::class.java || clazz == java.lang.Short::class.java ||
                    clazz == java.lang.Byte::class.java || clazz == java.lang.Float::class.java ||
                    clazz == java.lang.Double::class.java || clazz == java.math.BigDecimal::class.java ||
                    clazz == java.math.BigInteger::class.java || clazz.isEnum -> obj
            else -> {
                try {
                    val valueOfMethod = clazz.getMethod("valueOf", obj.javaClass)
                    valueOfMethod.invoke(null, obj)
                } catch (e: NoSuchMethodException) {
                    val fields = obj.javaClass.declaredFields
                    val fieldValues = fields.map { field ->
                        field.isAccessible = true
                        field.get(obj)
                    }

                    val constructors = clazz.declaredConstructors
                    val constructor = constructors.firstOrNull { constructor ->
                        val parameterTypes = constructor.parameterTypes
                        parameterTypes.size == fields.size && parameterTypes.withIndex()
                            .all { (i, paramType) ->
                                paramType.isAssignableFrom(fields[i].type)
                            }
                    }
                        ?: throw NoSuchMethodException("Constructor with matching fields not found for $clazz")
                    constructor.isAccessible = true

                    val copy = constructor.newInstance(*fieldValues.toTypedArray())
                    copy
                }
            }
        }

        return copy
    }
}
