package com.touchetime.presentation.common

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.touchetime.R

open class DialogFragmentTransparentBackground : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, R.style.AlertDialogStyle)
        isCancelable = false
        return super.onCreateDialog(savedInstanceState)
    }
}
