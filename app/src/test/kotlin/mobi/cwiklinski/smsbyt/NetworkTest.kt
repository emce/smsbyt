package mobi.cwiklinski.smsbyt

import com.google.gson.GsonBuilder
import mobi.cwiklinski.smsbyt.model.telegram.Message
import mobi.cwiklinski.smsbyt.model.telegram.adapter.MessageDeserializer
import mobi.cwiklinski.smsbyt.model.telegram.adapter.MessageListDeserializer
import mobi.cwiklinski.smsbyt.util.fromGsonString
import mobi.cwiklinski.smsbyt.utils.TestTools
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class NetworkTest {

    private val gson = GsonBuilder()
            .registerTypeAdapter(Message::class.java, MessageDeserializer())
            .registerTypeAdapter(List::class.java, MessageListDeserializer())
            .create()

    @Test
    @Throws(Exception::class)
    fun shouldParseMessageResponse() {
        val message: Message = TestTools.getStringFromResource("/telegram_message.json").fromGsonString(gson)
        assertNotNull(message)
        assertNotNull(message.chat)
        assertNotNull(message.from)
    }

    @Test
    @Throws(Exception::class)
    fun shouldParseUpdateResponse() {
        val messages: List<Message> = TestTools.getStringFromResource("/telegram_updates.json").fromGsonString(gson)
        assertNotNull(messages)
        assertEquals(2, messages.size)
    }
}