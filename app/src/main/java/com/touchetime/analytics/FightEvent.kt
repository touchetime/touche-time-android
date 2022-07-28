package com.touchetime.analytics

import com.touchetime.data.model.FightResponse
import com.touchetime.presentation.model.Fight
import com.touchetime.utils.state.FightState

sealed class FightEvent(override val name: String) : BaseEvent(name) {

    class FightOpened(
        fight: Fight
    ) : FightEvent(AnalyticsConstants.Events.Fight.FIGHT_SCREEN_OPENED) {
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

    class FightCreated(
        fightResponse: FightResponse
    ) : FightEvent(AnalyticsConstants.Events.Fight.FIGHT_FINISHED) {
        init {
            params.putString(AnalyticsConstants.Keys.ID, fightResponse.id)
            params.putString(AnalyticsConstants.Keys.NAME, fightResponse.name)
        }
    }
}
