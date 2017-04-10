package mobi.cwiklinski.smsbyt.provider

import mobi.cwiklinski.smsbyt.model.MessageModel
import mobi.cwiklinski.smsbyt.model.TextMessageModel
import mobi.cwiklinski.smsbyt.model.telegram.TextMessage
import mobi.cwiklinski.smsbyt.network.Api
import org.w3c.dom.Text
import rx.Single


class RestTelegramCommunicationProvider(val api: Api) : CommunicationProvider {

    override fun sendMessage(message: TextMessage): Single<Boolean> {
        return api.sendMessage(message)
                .map {
                    it != null
                }
    }
}