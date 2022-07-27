package com.touchetime.presentation.ui.activity.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.touchetime.data.sharedpreferences.OnBoardingSharedPreferences
import com.touchetime.databinding.ActivitySplashScreenBinding
import com.touchetime.extensions.setupFullScreenSystemUiFlags
import com.touchetime.presentation.ui.activity.main.MainActivity
import com.touchetime.presentation.ui.activity.onboarding.OnBoardingActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupFullScreenSystemUiFlags()

        goToMainScreen()
    }

    private fun goToMainScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(
                    baseContext,
                    if (OnBoardingSharedPreferences(this).wasVisible) {
                        MainActivity::class.java
                    } else {
                        OnBoardingActivity::class.java
                    }
                ).apply {
                    putExtras(intent)
                    data = intent.data
                }
            )
        }, OPEN_DELAY)
    }

    companion object {
        private const val OPEN_DELAY = 1000L
    }
}
