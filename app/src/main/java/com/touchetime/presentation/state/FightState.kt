package com.touchetime.presentation.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class FightState : Parcelable {
    MAIN_FIGHT,
    CUSTOM_FIGHT
}
