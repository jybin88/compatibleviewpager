package com.hylan.widget

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * CompatibleViewPager
 * 1.兼容低版本可能出现的异常
 * 2.跳转的页面多于两个直接显示页面没有动画
 * @author  lifuhai@linkcircle.cn
 * @date 2018/5/2 14:28
 */
class CompatibleViewPager : ViewPager {
    var mHelper: ViewPagerHelper? = null
    var mNoScroll: Boolean = false

    constructor(pContext: Context) : super(pContext) {
        mHelper = ViewPagerHelper(this)
    }

    constructor(pContext: Context, pAttributeSet: AttributeSet?) : super(pContext, pAttributeSet) {
        mHelper = ViewPagerHelper(this)
    }

    fun setNoScroll(pNoScroll: Boolean) {
        mNoScroll = pNoScroll
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return try {
            !mNoScroll && super.onInterceptTouchEvent(ev)
        } catch (pE: IllegalArgumentException) {
            false
        } catch (pE: ArrayIndexOutOfBoundsException) {
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return try {
            !mNoScroll && super.onTouchEvent(ev)
        } catch (pE: IllegalArgumentException) {
            false
        } catch (pE: ArrayIndexOutOfBoundsException) {
            false
        }
    }

    override fun setCurrentItem(item: Int) {
        setCurrentItem(item, true)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        val scroller: ViewPagerScroller? = mHelper?.getScroller()
        scroller?.let {
            if (Math.abs((currentItem - item)) > 1) {//如果页面相隔大于1,就设置页面切换的动画的时间为0
                scroller.setNoDuration(true)
                super.setCurrentItem(item, smoothScroll)
            } else {
                scroller.setNoDuration(false)
                super.setCurrentItem(item, smoothScroll)
            }
        }
    }
}