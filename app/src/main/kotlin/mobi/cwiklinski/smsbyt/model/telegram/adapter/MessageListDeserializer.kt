package mobi.cwiklinski.smsbyt.model.telegram.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import mobi.cwiklinski.smsbyt.model.telegram.Message
import mobi.cwiklinski.smsbyt.model.telegram.adapter.MessageDeserializer.Companion.getChat
import mobi.cwiklinski.smsbyt.model.telegram.adapter.MessageDeserializer.Companion.getUser
import mobi.cwiklinski.smsbyt.util.intOrZero
import mobi.cwiklinski.smsbyt.util.longOrZero
import mobi.cwiklinski.smsbyt.util.strOrEmpty
import java.lang.reflect.Type


class MessageListDeserializer : JsonDeserializer<List<Message>> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext)
            : List<Message> {
        val list = mutableListOf<Message>()
        var jsonObject = json.asJsonObject
        if (jsonObject.has("result")) {
            val array = jsonObject.getAsJsonArray("result")
            array.forEach {
                if (it.asJsonObject.has("message")) run {
                    val message = it.asJsonObject.getAsJsonObject("message")
                    list.add(Message(
                            message.longOrZero("message_id"),
                            getUser(message.getAsJsonObject("from")),
                            message.intOrZero("date"),
                            getChat(message.getAsJsonObject("chat")),
                            message.strOrEmpty("text")))
                }
            }
        }
        return list
    }
}