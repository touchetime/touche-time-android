package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.touchetime.databinding.CardCustomViewBinding

class CardCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = CardCustomViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setupParams(@StringRes title: Int, @StringRes description: Int, @DrawableRes icon: Int) {
        viewBinding.apply {
            this.title.text = context.getString(title)
            this.description.text = context.getString(description)
            this.icon.setImageDrawable(ContextCompat.getDrawable(context, icon))
        }
    }
}
