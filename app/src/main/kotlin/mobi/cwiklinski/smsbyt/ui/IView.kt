package mobi.cwiklinski.smsbyt.ui

import android.support.annotation.StringRes


interface IView {

    fun getString(@StringRes resource: Int): String

}