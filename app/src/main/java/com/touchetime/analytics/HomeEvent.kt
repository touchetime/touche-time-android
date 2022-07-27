package com.touchetime.analytics

import com.touchetime.presentation.model.Fight
import com.touchetime.presentation.state.FightState

sealed class HomeEvent(override val name: String) : BaseEvent(name) {

    class FightEvent(
        fight: Fight
    ) : HomeEvent(AnalyticsConstants.Events.FIGHT_OPENED) {
        init {
            params.putString(
                AnalyticsConstants.Keys.FIGHT_TYPE,
                if (fight.isCustom) {
                    FightState.CUSTOM_FIGHT.name.lowercase()
                } else {
                    FightState.MAIN_FIGHT.name.lowercase()
                }
            )
            params.putString(AnalyticsConstants.Keys.CATEGORY, fight.category.name.lowercase())
            params.putString(AnalyticsConstants.Keys.STYLE, fight.style.name.lowercase())
            params.putString(AnalyticsConstants.Keys.WEIGHT, fight.weight)
        }
    }
}
