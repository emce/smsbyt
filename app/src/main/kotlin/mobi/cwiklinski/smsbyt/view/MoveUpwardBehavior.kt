package mobi.cwiklinski.smsbyt.view

import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.View

@SuppressWarnings("unused")
class MoveUpwardBehavior : CoordinatorLayout.Behavior<View>() {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val translationY = Math.min(0f, dependency.translationY - dependency.height.toFloat())
        child.translationY = translationY
        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        val translationY = Math.min(0f, dependency.translationY + dependency.height.toFloat())
        child.translationY = translationY
    }
}