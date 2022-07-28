package com.touchetime.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.touchetime.data.model.FightResponse

@Dao
interface ToucheTimeDao {

    @Query("SELECT * FROM FightResponse")
    fun getListFight(): List<FightResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFightFinish(fightResponse: FightResponse)
}
