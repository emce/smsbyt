package mobi.cwiklinski.smsbyt.provider

import android.content.SharedPreferences


class PreferenceStorageProvider(val prefs: SharedPreferences)
    : StorageProvider {

    override fun save(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    override fun get(key: String, default: Long): Long {
        return prefs.getLong(key, default)
    }

    override fun  save(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun get(key: String, default: String): String {
        return prefs.getString(key, default)
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }
}