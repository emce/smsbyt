package mobi.cwiklinski.smsbyt.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent



@CoordinatorLayout.DefaultBehavior(MoveUpwardBehavior::class)
class BehaviourViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : ViewPager(context, attrs) {

    private var canScroll = false


    override fun executeKeyEvent(event: KeyEvent): Boolean {
        return canScroll
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return canScroll && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return canScroll && super.onInterceptTouchEvent(ev)
    }
}