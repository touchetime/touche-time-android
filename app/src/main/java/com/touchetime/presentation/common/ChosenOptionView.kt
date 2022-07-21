package com.touchetime.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

class ChosenOptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding = com.touchetime.databinding.ChosenOptionViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setupParams(@StringRes title: Int, @StringRes description: Int) {
        viewBinding.apply {
            this.title.text = context.getString(title)
            this.description.text = context.getString(description)
        }
    }

    fun setupListener(callback: () -> Unit) {
        viewBinding.container.setOnClickListener {
            callback()
        }
    }

    fun setupIsEnabled(value: Boolean) {
        viewBinding.apply {
            container.isEnabled = value
            viewBinding.title.isEnabled = value
            viewBinding.description.isEnabled = value
            viewBinding.next.isEnabled = value
        }
    }

    fun setupItemSelectedVisibility(@StringRes itemSelected: Int? = null, weightSelected: String? = null) {
        viewBinding.apply {
            objectSelected.isVisible = itemSelected != null || weightSelected != null
            textItemSelected.text =
                itemSelected?.let { context.getString(it) } ?: weightSelected
        }
    }
}
