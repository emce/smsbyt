package mobi.cwiklinski.smsbyt.ui.sms

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tbruyelle.rxpermissions.RxPermissions
import kotlinx.android.synthetic.main.fragment_sms.*
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.R
import mobi.cwiklinski.smsbyt.ui.base.BaseFragment
import mobi.cwiklinski.smsbyt.ui.setup.SetupActivity
import mobi.cwiklinski.smsbyt.ui.setup.SetupPresenter
import mobi.cwiklinski.smsbyt.util.SmsPermission
import mobi.cwiklinski.smsbyt.util.hasPermissions
import mobi.cwiklinski.smsbyt.view.AlertDialogBuilder
import rx.Observable
import rx.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SmsFragment : BaseFragment(), SmsView, View.OnClickListener {

    companion object {
        fun newInstance() = SmsFragment()
    }

    @Inject lateinit var presenter: SmsPresenter
    private var dialog: Dialog? = null
    private var canGoNext = false
    private lateinit var setupPresenter: SetupPresenter

    override fun inject() {
        App.get().feather.injectFields(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        smsPermissions.setOnClickListener(this)
        smsNext.setOnClickListener(this)
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
        dialog?.dismiss()
        presenter.stop()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.smsNext -> {
                if (canGoNext) {
                    presenter.onNext()
                }
            }
            R.id.smsPermissions -> {
                presenter.onPermission()
            }
        }
    }

    override fun canReadSms(): Boolean = activity?.hasPermissions(Manifest.permission.READ_SMS) ?: false

    override fun goToChannel() {
        getBaseActivity().hideKeyboard()
        setupPresenter.goToChannel()
    }

    override fun getPermissionRequest(): Single<SmsPermission>
        = Observable.timer(50, TimeUnit.MILLISECONDS)
            .compose(RxPermissions(activity).ensureEach(Manifest.permission.READ_SMS))
            .toSingle()
            .map {
                when {
                    it.granted -> SmsPermission.GRANTED
                    it.shouldShowRequestPermissionRationale -> // Denied permission without ask never again
                        SmsPermission.RATIONALE
                    else -> // Denied permission with ask never again
                        // Need to go to the settings
                        SmsPermission.DENIED
                }
            }

    override fun showPermissionWarning() {
        dialog = AlertDialogBuilder(activity, R.style.Theme_AppCompat_Light_Dialog)
                .setTitle(R.string.sms_no_permissions_title)
                .setMessage(R.string.sms_no_permissions_message)
                .setPositiveButton(R.string.sms_show_settings, {
                    dialog, _ -> dialog.dismiss()
                    showApplicationPermissionsSettings()
                })
                .setNegativeButton(android.R.string.cancel, {
                    dialog, _ -> dialog.dismiss()
                })
                .create()
        dialog?.show()
    }

    override fun canGoNext(canGo: Boolean) {
        canGoNext = canGo
    }

    override fun showApplicationPermissionsSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.fromParts("package", activity.packageName, null)
        startActivity(intent)
    }

    override fun showPermissionRationale() {
        dialog = AlertDialogBuilder(activity, R.style.Theme_AppCompat_Light_Dialog)
                .setTitle(R.string.sms_rationale_title)
                .setMessage(R.string.sms_rationale_message)
                .setPositiveButton(R.string.sms_permissions, {
                    dialog, _ -> dialog.dismiss()
                    presenter.onRationaleAccepted()
                })
                .setNegativeButton(android.R.string.cancel, {
                    dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(activity, R.string.sms_title, Toast.LENGTH_LONG).show()
                    activity.finish()
                })
                .create()
        dialog?.show()
    }
}