package com.touchetime.utils.state

import android.os.Parcelable
import androidx.annotation.StringRes
import com.touchetime.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class CategoryState(@StringRes val value: Int) : Parcelable {
    CHILDREN_1(R.string.childrens_1),
    CHILDREN_2(R.string.childrens_2),
    CHILDREN_3(R.string.childrens_3),
    U15(R.string.u15),
    U17(R.string.u17),
    U20(R.string.u20),
    U23(R.string.u23),
    SENIOR(R.string.senior),
    MASTER(R.string.master),
    DEFAULT(R.string.category)
}
