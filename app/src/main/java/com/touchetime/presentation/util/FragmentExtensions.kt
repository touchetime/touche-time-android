package com.touchetime.presentation.util

import androidx.fragment.app.Fragment
import com.touchetime.presentation.common.MoreScoreDialogFullscreen
import com.touchetime.presentation.common.WinnerDialogFullscreen
import com.touchetime.presentation.model.Athlete

fun Fragment.showWinnerFullscreenDialog(
    athlete: Athlete,
    closeFight: () -> Unit,
    restartFight: () -> Unit
) = WinnerDialogFullscreen(
    athlete, closeFight, restartFight
).also { it.show(childFragmentManager, it::class.java.name) }

fun Fragment.showMoreScoreDialogFullscreen(
    athlete: Athlete,
    twoScore: () -> Unit,
    fourScore: () -> Unit,
    fiveScore: () -> Unit,
) = MoreScoreDialogFullscreen(
    athlete, twoScore, fourScore, fiveScore,
).also { it.show(childFragmentManager, it::class.java.name) }
