package mobi.cwiklinski.smsbyt.ui.main

import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.IPresenter
import javax.inject.Inject


class MainPresenter @Inject constructor(val storage: StorageProvider)
    : IPresenter<MainView>() {

    override fun start() {
        super.start()
        if (!storage.get(StorageProvider.KEY_CHANNEL_ID).equals(0)) {
            view.showSetup()
        }
    }

    fun queryForReset() {
        view.showResetConfirmation()
    }

    fun resetData() {
        storage.clear()
        view.showSetup()
    }


}