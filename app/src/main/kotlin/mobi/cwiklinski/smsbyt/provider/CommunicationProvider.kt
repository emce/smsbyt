package mobi.cwiklinski.smsbyt.provider

import mobi.cwiklinski.smsbyt.model.telegram.TextMessage
import rx.Single


interface CommunicationProvider {

    fun sendMessage(message: TextMessage): Single<Boolean>
}