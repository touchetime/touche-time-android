package com.touchetime.data.appdatabase // ktlint-disable filename

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.touchetime.data.dao.ToucheTimeDao
import com.touchetime.data.model.FightResponse

@Database(entities = [FightResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val toucheTimeDao: ToucheTimeDao

    companion object {
        // @Volatile indicates that this instance will always work in main memory, never via cache
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it, if it is, then create the database.
            // "synchronized" causes all concurrent database accesses to be queued and handled one by one.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "touche_time_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
