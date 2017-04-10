package mobi.cwiklinski.smsbyt.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user.*
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.R
import mobi.cwiklinski.smsbyt.ui.base.BaseFragment
import mobi.cwiklinski.smsbyt.ui.main.MainActivity
import mobi.cwiklinski.smsbyt.ui.main.MainPresenter
import javax.inject.Inject


class UserFragment : BaseFragment(), UserView, View.OnClickListener {

    companion object {
        fun newInstance() = UserFragment()
    }

    @Inject lateinit var presenter: UserPresenter
    lateinit private var mainPresenter: MainPresenter

    override fun inject() {
        App.get().feather.injectFields(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        userNext.setOnClickListener(this)
        userPrev.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainPresenter = (activity as MainActivity).presenter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.userNext -> presenter.onNext()
            R.id.userPrev -> presenter.onPrevious()
        }
    }

    override fun isUserNameValid() = !userName.text.isNullOrEmpty()

    override fun onNext() {
        getBaseActivity().hideKeyboard()
        mainPresenter.goToTest()
    }

    override fun onPrevious() {
        mainPresenter.goToChannel()
    }

    override fun getUserName(): String = userName.text.toString()

    override fun showUserNameError() {
        mainPresenter.showMessage(R.string.user_name_incorrect)
    }

    override fun showConversationError() {
        mainPresenter.showMessage(R.string.user_no_conversation)
    }
}