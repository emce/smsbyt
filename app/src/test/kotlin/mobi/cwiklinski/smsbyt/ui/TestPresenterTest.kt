package mobi.cwiklinski.smsbyt.ui

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import mobi.cwiklinski.smsbyt.model.telegram.TextMessage
import mobi.cwiklinski.smsbyt.provider.CommunicationProvider
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.test.TestPresenter
import mobi.cwiklinski.smsbyt.ui.test.TestView
import mobi.cwiklinski.smsbyt.utils.TestTools
import org.junit.Before
import org.junit.Test
import rx.Single
import kotlin.test.assertNotNull


class TestPresenterTest {

    private val view = mock<TestView> {

    }
    private val storage = mock<StorageProvider> {
        on { get(any<String>(), any<String>()) }.thenReturn(TestTools.randomString(12))
    }
    private val communication = mock<CommunicationProvider>()
    private val presenter = TestPresenter(communication, storage, TestTools.testSchedulers())

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
        assertNotNull(presenter.communication)
    }

    @Test
    @Throws(Exception::class)
    fun shouldFinishOnProperClick() {
        presenter.onFinish()
        verify(view).finish()
    }

    @Test
    @Throws(Exception::class)
    fun shouldGoToPreviousView() {
        presenter.onPrevious()
        verify(view).onPrevious()
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowMessageOnSuccessfulSending() {
        whenever(communication.sendMessage(any<TextMessage>()))
                .thenReturn(Single.just(true))
        presenter.startTest(TextMessage(747L, "test"))
        verify(view).onMessageSent(true)
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowMessageOnFailedSending() {
        whenever(communication.sendMessage(any<TextMessage>()))
                .thenReturn(Single.just(false))
        presenter.startTest(TextMessage(747L, "test"))
        verify(view).onMessageSent(false)
    }
}