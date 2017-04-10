package mobi.cwiklinski.smsbyt.ui

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import mobi.cwiklinski.smsbyt.model.telegram.Chat
import mobi.cwiklinski.smsbyt.model.telegram.Message
import mobi.cwiklinski.smsbyt.model.telegram.User
import mobi.cwiklinski.smsbyt.network.Api
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.user.UserPresenter
import mobi.cwiklinski.smsbyt.ui.user.UserView
import mobi.cwiklinski.smsbyt.utils.TestTools
import org.junit.Before
import org.junit.Test
import rx.Single
import kotlin.test.assertNotNull


class UserPresenterTest {

    private val userName = TestTools.randomString(12)
    private val view = mock<UserView> {
        on { getUserName() }.thenReturn(userName)
    }
    private val storage = mock<StorageProvider>()
    private val api = mock<Api> {
        on { getUpdates() }.thenReturn(Single.just(listOf(
                Message(9L, date = 9, text = TestTools.randomString(12), chat = Chat(1L, "channel", null),
                        from = User(12, userName, userName, userName)))))
    }
    private val presenter = UserPresenter(api, storage, TestTools.testSchedulers())

    @Before
    fun setUp() {
        presenter.attachView(view)
        presenter.start()
    }

    @Test
    @Throws(Exception::class)
    fun shouldHaveViewAttached() {
        assertNotNull(presenter.view)
        assertNotNull(presenter.storage)
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowWarningWithInvalidUserName() {
        whenever(view.isUserNameValid()).thenReturn(false)
        presenter.onNext()
        verify(view).showUserNameError()
    }

    @Test
    @Throws(Exception::class)
    fun shouldGoNextWithValidUserName() {
        whenever(view.isUserNameValid()).thenReturn(true)
        presenter.onNext()
        verify(api).getUpdates()
        verify(storage).save(any<String>(), any<Long>())
        verify(view).onNext()
    }

    @Test
    @Throws(Exception::class)
    fun shouldGoBackWhenTriggered() {
        presenter.onPrevious()
        verify(view).onPrevious()
    }
}