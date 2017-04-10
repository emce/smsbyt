package mobi.cwiklinski.smsbyt.ui.user

import mobi.cwiklinski.smsbyt.ui.IView


interface UserView : IView {
    fun isUserNameValid(): Boolean
    fun getUserName(): String
    fun onNext()
    fun onPrevious()
    fun showUserNameError()
    fun showConversationError()
}