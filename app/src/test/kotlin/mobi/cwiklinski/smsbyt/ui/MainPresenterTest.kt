package mobi.cwiklinski.smsbyt.ui

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.main.MainPresenter
import mobi.cwiklinski.smsbyt.ui.main.MainView
import mobi.cwiklinski.smsbyt.utils.TestTools
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString


class MainPresenterTest {

    private val view: MainView = mock()
    private val storage: StorageProvider = mock()
    private val presenter = MainPresenter(storage)

    @Before
    fun setUp() {
        presenter.attachView(view)
    }

    @Test
    @Throws(Exception::class)
    fun shouldOpenSetupIfNotConfigured() {
        whenever(storage.get(anyString(), anyLong())).thenReturn(0)
        whenever(storage.get(anyString(), anyString())).thenReturn("")
        presenter.start()
        verify(view).showSetup()
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowConfirmationAfterReset() {
        whenever(storage.get(anyString(), anyLong())).thenReturn(TestTools.randomLong())
        whenever(storage.get(anyString(), anyString())).thenReturn(TestTools.randomString(12))
        presenter.start()
        presenter.queryForReset()
        verify(view).showResetConfirmation()
    }

}