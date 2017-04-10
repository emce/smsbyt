package mobi.cwiklinski.smsbyt.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


inline fun <reified T : Any> IntentFor(context: Context, clear: Boolean = false): Intent {
    val intent = Intent(context, T::class.java)
    if (clear) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    return intent
}

fun Context.getInputMethodManager(): InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
        as InputMethodManager

fun Context.hasPermissions(vararg permissions: String) : Boolean
        = permissions.none { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager
            .beginTransaction()
            .add(frameId, fragment, fragment.javaClass.simpleName)
            .commit()
}
fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager
            .beginTransaction()
            .replace(frameId, fragment, fragment.javaClass.simpleName)
            .commit()
}

inline fun <reified T> genericType(): Type = object: TypeToken<T>() {}.type

fun Any?.toGsonString(): String = Gson().toJson(this)

inline fun <reified T> String?.fromGsonString(gson: Gson): T {
    return gson.fromJson<T>(this, genericType<T>())
}

fun JsonObject.longOrZero(key: String): Long {
    if (has(key)) {
        return get(key).asLong
    } else {
        return 0L
    }
}

fun JsonObject.intOrZero(key: String): Int {
    if (has(key)) {
        return get(key).asInt
    } else {
        return 0
    }
}

fun JsonObject.strOrEmpty(key: String): String {
    if (has(key)) {
        return get(key).asString
    } else {
        return ""
    }
}


fun Intent.generateTelegramForGooglePlay() {
    data = Uri.parse("market://details?id=com.telegram.messenger")
}