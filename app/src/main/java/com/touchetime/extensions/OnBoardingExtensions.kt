package com.touchetime.extensions // ktlint-disable filename

import android.content.Context
import android.content.Intent
import com.touchetime.data.sharedpreferences.OnBoardingSharedPreferences
import com.touchetime.presentation.ui.activity.main.MainActivity

fun finishOnBoarding(context: Context) {
    OnBoardingSharedPreferences(context).wasVisible = true
    context.startActivity(Intent(context, MainActivity::class.java))
}
