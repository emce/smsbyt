package mobi.cwiklinski.smsbyt.utils

import mobi.cwiklinski.smsbyt.util.RxSchedulers
import rx.schedulers.Schedulers
import java.util.*

object TestTools {


    val generator = Random()
    val AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

    fun randomInt(): Int {
        return generator.nextInt()
    }

    fun randomInt(maxVal: Int): Int {
        return generator.nextInt(maxVal)
    }

    fun randomLong(): Long {
        return generator.nextLong()
    }

    fun randomDouble(): Double {
        return generator.nextDouble()
    }

    fun randomFloat(): Float {
        return generator.nextFloat()
    }

    fun randomDate(): Date {
        return Date(generator.nextLong())
    }

    fun randomString(length: Int): String {
        val sb = StringBuilder(length)
        for (i in 0 until length) {
            sb.append(AB.toCharArray()[generator.nextInt(AB.length)])
        }
        return sb.toString()
    }

    fun testSchedulers(): RxSchedulers {
        val scheduler = Schedulers.immediate()
        return RxSchedulers(scheduler, scheduler, scheduler, scheduler)
    }

    fun getStringFromResource(name: String): String {
        val resourceAsStream = javaClass.getResourceAsStream(name)
                ?: throw Exception("Resource $name not found")

        return resourceAsStream.use { it.bufferedReader().readText() }
    }
}