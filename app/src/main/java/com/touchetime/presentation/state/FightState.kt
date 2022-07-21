package com.touchetime.presentation.state

import android.os.Parcelable
import com.touchetime.presentation.model.Fight
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class FightState : Parcelable {
    data class MainFight(val fight: Fight) : FightState()
    data class CustomFight(val fight: Fight) : FightState()
}
