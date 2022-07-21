package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.touchetime.databinding.CustomizeObjectViewBinding

class CustomizeObjectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = CustomizeObjectViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setupTitle(@StringRes value: Int) {
        viewBinding.title.text = context.getString(value)
    }

    fun updateValue(value: String) {
        viewBinding.value.text = value
    }

    fun removeValue(callback: () -> Unit) {
        viewBinding.remove.setOnClickListener {
            callback()
        }
    }

    fun addValue(callback: () -> Unit) {
        viewBinding.add.setOnClickListener {
            callback()
        }
    }
}
