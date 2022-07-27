package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.touchetime.databinding.FightCustomViewBinding

class FightCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = FightCustomViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setupNameFight(value: String) {
        viewBinding.title.text = value.uppercase()
    }

    fun setupScore(red: Int, blue: Int) {
        viewBinding.scoreBlue.text = blue.toString()
        viewBinding.scoreRed.text = red.toString()
    }
}
