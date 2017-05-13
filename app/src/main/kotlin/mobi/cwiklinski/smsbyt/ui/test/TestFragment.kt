package mobi.cwiklinski.smsbyt.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test.*
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.R
import mobi.cwiklinski.smsbyt.model.telegram.TextMessage
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.base.BaseFragment
import mobi.cwiklinski.smsbyt.ui.setup.SetupActivity
import mobi.cwiklinski.smsbyt.ui.setup.SetupPresenter
import javax.inject.Inject


class TestFragment : BaseFragment(), TestView, View.OnClickListener {

    companion object {
        fun newInstance() = TestFragment()
    }

    @Inject lateinit var presenter: TestPresenter
    lateinit private var setupPresenter: SetupPresenter

    override fun inject() {
        App.get().feather.injectFields(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        testStart.setOnClickListener(this)
        testPrev.setOnClickListener(this)
        testFinish.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupPresenter = (activity as SetupActivity).presenter
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onPause() {
        super.onPause()
        presenter.stop()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.testStart -> {
                val channelId = presenter.storage.get(StorageProvider.KEY_CHANNEL_ID, 0)
                val testMessage = getString(R.string.message_content).format("123456789", "test message")
                presenter.startTest(TextMessage(channelId, testMessage))
            }
            R.id.testFinish -> presenter.onFinish()
            R.id.testPrev -> presenter.onPrevious()
        }
    }

    override fun onPrevious() {
        setupPresenter.goToUser()
    }

    override fun finish() {
        activity.finish()
    }

    override fun onMessageSent(successful: Boolean) {
        setupPresenter.showMessage(if (successful) R.string.test_message_sent
            else R.string.test_message_failed)
    }

    override fun canGoNext(canGo: Boolean) {
        testFinish.isEnabled = canGo
    }

    override fun canGoPrevious(canGo: Boolean) {
        testPrev.isEnabled = canGo
    }
}