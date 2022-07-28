package com.touchetime.data.model

import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.touchetime.utils.state.ColorState
import java.util.*

@Entity
data class FightResponse(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,
    @SerializedName("category")
    @ColumnInfo(name = "category")
    @StringRes
    val category: Int,
    @SerializedName("style")
    @ColumnInfo(name = "style")
    @StringRes
    val style: Int,
    @SerializedName("weight")
    @ColumnInfo(name = "weight")
    val weight: String,
    @SerializedName("rounds")
    @ColumnInfo(name = "rounds")
    val rounds: Int,
    @SerializedName("superiority_technical")
    @ColumnInfo(name = "superiority_technical")
    val superiorityTechnical: Int,
    @SerializedName("time_round")
    @ColumnInfo(name = "time_round")
    val timeRound: Long,
    @SerializedName("time_interval")
    @ColumnInfo(name = "time_interval")
    val timeInterval: Long,
    @Embedded
    @SerializedName("athlete_red")
    val athleteRed: AthleteRedResponse,
    @Embedded
    @SerializedName("athlete_blue")
    val athleteBlue: AthleteBlueResponse,
    @SerializedName("athlete_winner")
    @ColumnInfo(name = "athlete_winner")
    val athleteWinner: ColorState,
    @SerializedName("is_touche")
    @ColumnInfo(name = "is_touche")
    val isTouche: Boolean
)

data class AthleteRedResponse(
    @SerializedName("color_red")
    @ColumnInfo(name = "color_red")
    val colorRed: ColorState,
    @SerializedName("score_red")
    @ColumnInfo(name = "score_red")
    val score: Int,
    @SerializedName("foul_red")
    @ColumnInfo(name = "foul_red")
    val foul: Int
)

data class AthleteBlueResponse(
    @SerializedName("color_blue")
    @ColumnInfo(name = "color_blue")
    val colorBlue: ColorState,
    @SerializedName("score_blue")
    @ColumnInfo(name = "score_blue")
    val score: Int,
    @SerializedName("foul_blue")
    @ColumnInfo(name = "foul_blue")
    val foul: Int
)
