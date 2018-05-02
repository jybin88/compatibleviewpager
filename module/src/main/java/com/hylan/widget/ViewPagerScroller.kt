package com.hylan.widget

import android.content.Context
import android.widget.Scroller

/**
 * 自定义Scroller
 * @author  lifuhai@linkcircle.cn
 * @date 2018/5/2 14:02
 */
class ViewPagerScroller(pContext: Context) : Scroller(pContext) {
    var mNoDuration: Boolean = false//滑动是否需要时间间隔, 默认需要

    /**
     * 设置滑动是否需要时间间隔
     * @param pNoDuration  true false
     */
    fun setNoDuration(pNoDuration: Boolean) {
        mNoDuration = pNoDuration
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        if (mNoDuration) {//界面滑动不需要时间间隔
            super.startScroll(startX, startY, dx, dy, 0)
        } else {
            super.startScroll(startX, startY, dx, dy, duration)
        }
    }
}