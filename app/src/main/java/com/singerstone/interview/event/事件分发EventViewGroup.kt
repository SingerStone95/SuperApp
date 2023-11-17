package com.singerstone.interview.event

import android.view.MotionEvent

interface ViewParent {
    fun requestDisallowInterceptTouchEvent(isDisallowIntercept: Boolean)
}

open class MView {
    var parent: ViewParent? = null

    open fun dispatch(ev: MotionEvent): Boolean {
        return onTouch(ev)
    }

    open fun onTouch(ev: MotionEvent): Boolean {
        return false
    }
}

open class MViewGroup(private val child: MView) : MView(), ViewParent {
    private var isChildNeedEvent = false
    private var isSelfNeedEvent = false
    private var isDisallowIntercept = false

    init {
        child.parent = this
    }

    override fun dispatch(ev: MotionEvent): Boolean {
        var handled = false

        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            clearStatus()

            // add isDisallowIntercept
            if (!isDisallowIntercept && onIntercept(ev)) {
                isSelfNeedEvent = true
                handled = onTouch(ev)
            } else {
                handled = child.dispatch(ev)
                if (handled) isChildNeedEvent = true

                if (!handled) {
                    handled = onTouch(ev)
                    if (handled) isSelfNeedEvent = true
                }
            }
        } else {
            if (isSelfNeedEvent) {
                handled = onTouch(ev)
            } else if (isChildNeedEvent) {
                // add isDisallowIntercept
                if (!isDisallowIntercept && onIntercept(ev)) {
                    isSelfNeedEvent = true

                    // add cancel
                    val cancel = MotionEvent.obtain(ev)
                    cancel.action = MotionEvent.ACTION_CANCEL
                    handled = child.dispatch(cancel)
                    cancel.recycle()
                } else {
                    handled = child.dispatch(ev)
                }
            }
        }

        if (ev.actionMasked == MotionEvent.ACTION_UP
            || ev.actionMasked == MotionEvent.ACTION_CANCEL
        ) {
            clearStatus()
        }

        return handled
    }

    private fun clearStatus() {
        isChildNeedEvent = false
        isSelfNeedEvent = false
        isDisallowIntercept = false
    }

    override fun onTouch(ev: MotionEvent): Boolean {
        return false
    }

    open fun onIntercept(ev: MotionEvent): Boolean {
        return false
    }

    override fun requestDisallowInterceptTouchEvent(isDisallowIntercept: Boolean) {
        this.isDisallowIntercept = isDisallowIntercept
        parent?.requestDisallowInterceptTouchEvent(isDisallowIntercept)
    }
}
