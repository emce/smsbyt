package mobi.cwiklinski.smsbyt.provider


interface StorageProvider {

    companion object {
        const val KEY_CHANNEL_ID = "channel-id"
        const val KEY_USER_NAME = "user-name"
    }

    fun save(key: String, value: String)
    fun save(key: String, value: Long)
    fun get(key: String, default: String): String
    fun get(key: String, default: Long = 0): Long
    fun clear()
}