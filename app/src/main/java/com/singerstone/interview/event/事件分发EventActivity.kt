package com.singerstone.interview.event

import android.view.MotionEvent

open class MActivity(private val childGroup: MViewGroup) {
    private var isChildNeedEvent = false
    private var isSelfNeedEvent = false

    open fun dispatch(ev: MotionEvent): Boolean {
        var handled = false

        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            clearStatus()

            handled = childGroup.dispatch(ev)
            if (handled) isChildNeedEvent = true

            if (!handled) {
                handled = onTouch(ev)
                if (handled) isSelfNeedEvent = true
            }
        } else {
            if (isSelfNeedEvent) {
                handled = onTouch(ev)
            } else if (isChildNeedEvent) {
                handled = childGroup.dispatch(ev)
            }

            if (!handled) handled = onTouch(ev)
        }

        if (ev.actionMasked == MotionEvent.ACTION_UP
            || ev.actionMasked == MotionEvent.ACTION_CANCEL) {
            clearStatus()
        }

        return handled
    }

    private fun clearStatus() {
        isChildNeedEvent = false
        isSelfNeedEvent = false
    }

    open fun onTouch(ev: MotionEvent): Boolean {
        return false
    }
}
