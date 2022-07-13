package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.touchetime.databinding.ToolbarBinding

class ToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val viewBinding = ToolbarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setupParams(@StringRes title: Int, @DrawableRes icon: Int) {
        viewBinding.apply {
            this.title.text = context.getString(title)
            this.back.setImageDrawable(ContextCompat.getDrawable(context, icon))
        }
    }

    fun setupBack(callback: () -> Unit) {
        viewBinding.back.setOnClickListener {
            callback()
        }
    }
}
