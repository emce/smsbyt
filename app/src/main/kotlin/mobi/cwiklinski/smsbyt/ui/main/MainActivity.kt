package mobi.cwiklinski.smsbyt.ui.main

import android.app.Dialog
import android.os.Bundle
import android.view.View
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.R
import mobi.cwiklinski.smsbyt.ui.base.BaseActivity
import mobi.cwiklinski.smsbyt.ui.setup.SetupActivity
import mobi.cwiklinski.smsbyt.util.IntentFor
import mobi.cwiklinski.smsbyt.view.AlertDialogBuilder
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, View.OnClickListener {

    @Inject lateinit var presenter: MainPresenter
    private var dialog: Dialog? = null

    override fun inject() {
        App.get().feather.injectFields(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
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
            R.id.mainReset -> presenter.queryForReset()
        }
    }

    override fun showSetup() {
        startActivity(IntentFor<SetupActivity>(this))
    }

    override fun showResetConfirmation() {
        dialog = AlertDialogBuilder(this, R.style.Theme_AppCompat_Light_Dialog)
                .setTitle(R.string.main_reset_dialog_title)
                .setMessage(R.string.main_reset_dialog_message)
                .setPositiveButton(R.string.main_reset_dialog_button, {
                    dialog, _ -> dialog.dismiss()
                    presenter.resetData()
                })
                .setNegativeButton(android.R.string.cancel, {
                    dialog, _ -> dialog.dismiss()
                })
                .create()
        dialog?.show()
    }
}
