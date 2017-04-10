package mobi.cwiklinski.smsbyt.ui

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.channel.ChannelPresenter
import mobi.cwiklinski.smsbyt.ui.channel.ChannelView
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull

class ChannelPresenterTest {

    private val view = mock<ChannelView> {

    }
    private val storage = mock<StorageProvider>()
    private val presenter = ChannelPresenter(storage)

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
    fun shouldGoBackWhenTriggered() {
        presenter.onPrevious()
        verify(view).onPrevious()
    }

}