package com.touchetime.presentation.util

import androidx.fragment.app.Fragment
import com.touchetime.presentation.common.WinnerDialogFullscreen
import com.touchetime.presentation.model.Athlete

fun Fragment.showWinnerFullscreenDialog(
    athlete: Athlete,
    addScore: () -> Unit,
    removeScore: () -> Unit,
    closeFight: () -> Unit,
    restartFight: () -> Unit
) = WinnerDialogFullscreen(
    athlete, addScore, removeScore, closeFight, restartFight
).also { it.show(childFragmentManager, it::class.java.name) }
