package mobi.cwiklinski.smsbyt.ui.channel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_channel.*
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.R
import mobi.cwiklinski.smsbyt.config.Constants
import mobi.cwiklinski.smsbyt.ui.base.BaseFragment
import mobi.cwiklinski.smsbyt.ui.setup.SetupActivity
import mobi.cwiklinski.smsbyt.ui.setup.SetupPresenter
import mobi.cwiklinski.smsbyt.util.generateTelegramForGooglePlay
import timber.log.Timber
import javax.inject.Inject

class ChannelFragment : BaseFragment(), ChannelView, View.OnClickListener {

    companion object {
        fun newInstance() = ChannelFragment()
    }

    @Inject lateinit var presenter: ChannelPresenter
    private lateinit var setupPresenter: SetupPresenter

    override fun inject() {
        App.get().feather.injectFields(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_channel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        channelConversation.setOnClickListener(this)
        channelNext.setOnClickListener(this)
        channelPrev.setOnClickListener(this)
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
            R.id.channelNext -> onNext()
            R.id.channelPrev -> presenter.onPrevious()
            R.id.channelConversation -> presenter.onConversationOpen()
        }
    }

    override fun onNext() {
        getBaseActivity().hideKeyboard()
        setupPresenter.goToUser()
    }

    override fun onPrevious() {
        getBaseActivity().hideKeyboard()
        setupPresenter.goToSms()
    }

    override fun canGoNext(canGo: Boolean) {
        channelNext.isEnabled = canGo
    }

    override fun canGoPrevious(canGo: Boolean) {
        channelPrev.isEnabled = canGo
    }

    override fun openTelegramConversation(): Boolean {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://telegram.me/SMStoTbot")
        intent.`package` = Constants.TELEGRAM_PACKAGE
        try {
            startActivity(intent)
            return true
        } catch (e: Exception) {
            Timber.e(e)
            openTelegramInPlayStore()
        }
        return false
    }

    override fun openTelegramInPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.generateTelegramForGooglePlay()
        startActivity(intent)
    }

    override fun showConversationError() {
    }
}