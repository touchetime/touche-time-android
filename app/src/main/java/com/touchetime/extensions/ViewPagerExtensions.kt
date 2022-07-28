package com.touchetime.extensions // ktlint-disable filename

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.removeOverScroll() {
    (getChildAt(0) as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER
}
