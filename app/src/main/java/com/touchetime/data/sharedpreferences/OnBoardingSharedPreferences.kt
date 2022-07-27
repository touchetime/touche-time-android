package com.touchetime.data.sharedpreferences

import android.content.Context
import androidx.core.content.edit
import com.touchetime.utils.Constants

class OnBoardingSharedPreferences(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(
            OnBoardingSharedPreferences::class.java.name,
            Context.MODE_PRIVATE
        )

    var wasVisible: Boolean
        get() = sharedPreferences.getBoolean(Constants.ON_BOARDING_WAS_VISIBILITY, false)
        set(value) {
            sharedPreferences.edit {
                putBoolean(Constants.ON_BOARDING_WAS_VISIBILITY, value)
            }
        }
}
