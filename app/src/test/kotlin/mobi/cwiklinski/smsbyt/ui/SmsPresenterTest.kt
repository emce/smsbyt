package mobi.cwiklinski.smsbyt.ui

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import mobi.cwiklinski.smsbyt.ui.sms.SmsPresenter
import mobi.cwiklinski.smsbyt.ui.sms.SmsView
import mobi.cwiklinski.smsbyt.util.SmsPermission
import mobi.cwiklinski.smsbyt.utils.TestTools
import org.junit.Before
import org.junit.Test
import rx.Single
import kotlin.test.assertNotNull

class SmsPresenterTest {

    private val view = mock<SmsView> {
        on { canReadSms() }.thenReturn(false)
        on { getPermissionRequest() }.thenReturn(Single.just(SmsPermission.GRANTED))
    }
    private val presenter = SmsPresenter(TestTools.testSchedulers())

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
    fun shouldShowSystemPermissionDialog() {
        presenter.start()
        presenter.onPermission()
        verify(view).getPermissionRequest()
    }

    @Test
    @Throws(Exception::class)
    fun shouldAllowGoNextAfterPermissionAllowance() {
        presenter.start()
        presenter.onPermission()
        verify(view).getPermissionRequest()
        verify(view).canGoNext(true)
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowWarningWithRationalePermissions() {
        whenever(view.getPermissionRequest()).thenReturn(Single.just(SmsPermission.RATIONALE))
        presenter.start()
        presenter.onPermission()
        verify(view).getPermissionRequest()
        verify(view, times(3)).canGoNext(false)
        verify(view).showPermissionRationale()
    }

    @Test
    @Throws(Exception::class)
    fun shouldShowWarningWithNoPermissions() {
        whenever(view.getPermissionRequest()).thenReturn(Single.just(SmsPermission.DENIED))
        presenter.start()
        presenter.onPermission()
        verify(view).getPermissionRequest()
        verify(view).showPermissionWarning()
    }
}