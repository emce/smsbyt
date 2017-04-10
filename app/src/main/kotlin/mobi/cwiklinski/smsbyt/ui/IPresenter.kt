package mobi.cwiklinski.smsbyt.ui

import rx.subscriptions.CompositeSubscription

abstract class IPresenter<V : IView> {

    lateinit var view: V
    val subscriptions = CompositeSubscription()

    open fun start() {

    }

    open fun attachView(view: V) {
        this.view = view
    }

    open fun detachView() {

    }

    open fun stop() {
        subscriptions.clear()
    }
}