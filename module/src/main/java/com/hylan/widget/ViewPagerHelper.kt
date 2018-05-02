package com.hylan.widget

import android.support.v4.view.ViewPager
import java.lang.reflect.Field

/**
 * ViewPagerHelper
 * @author  lifuhai@linkcircle.cn
 * @date 2018/5/2 14:07
 */
class ViewPagerHelper(pViewPager: ViewPager) {
    private var mViewPager: ViewPager? = null
    var mScroller: ViewPagerScroller? = null

    init {
        mViewPager = pViewPager
        mScroller = ViewPagerScroller(mViewPager!!.context)
        val clazz: Class<ViewPager> = ViewPager::class.java
        try {
            val field: Field = clazz.getDeclaredField("mScroller")
            field.isAccessible = true
            //利用反射设置mScroller域为自己定义的MScroller
            field.set(mViewPager, mScroller)
        } catch (pE: NoSuchFieldException) {
            pE.printStackTrace()
        } catch (pE: IllegalAccessException) {
            pE.printStackTrace()
        }
    }

    /**
     * 返回自定义ViewPagerScroller
     * @return ViewPagerScroller
     */
    fun getScroller(): ViewPagerScroller? {
        return mScroller
    }
}