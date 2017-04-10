package mobi.cwiklinski.smsbyt.ui

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.main.MainPresenter
import mobi.cwiklinski.smsbyt.ui.main.MainView
import mobi.cwiklinski.smsbyt.utils.TestTools
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull


class MainPresenterTest {

    private val view = mock<MainView> {

    }
    private val storage = mock<StorageProvider>()
    private val presenter = MainPresenter(storage, TestTools.testSchedulers())

    @Before
    fun setUp() {
        presenter.attachView(view)
    }

    @Test
    @Throws(Exception::class)
    fun shouldHaveViewAttached() {
        assertNotNull(presenter.view)
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowProperView() {
        presenter.goToSms()
        verify(view).switchToSms()
        presenter.goToChannel()
        verify(view).switchToChannel()
        presenter.goToUser()
        verify(view).switchToUser()
        presenter.goToTest()
        verify(view).switchToTest()
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowMessage() {
        presenter.showMessage(TestTools.randomString(12))
        verify(view).showMessage(any<String>())
    }
}