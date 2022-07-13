package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.touchetime.databinding.ChosenOptionViewBinding

class ChosenOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = ChosenOptionViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setupParams(@StringRes title: Int, @StringRes description: Int) {
        viewBinding.apply {
            this.title.text = context.getString(title)
            this.description.text = context.getString(description)
        }
    }
}
