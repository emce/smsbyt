package mobi.cwiklinski.smsbyt.model


interface MessageModel {
    var id: Long?
    var from: UserModel?
    var chat: ChatModel?
    var text: String
}