package com.simonchila.halo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.simonchila.halo.data.local.dao.HaloDao
import com.simonchila.halo.data.local.entities.PlayerStats

@Database(entities = [PlayerStats::class], version = 2, exportSchema = false)
abstract class HaloDatabase : RoomDatabase() {
    abstract fun haloDao(): HaloDao

    companion object {
        @Volatile
        private var INSTANCE: HaloDatabase? = null

        fun getDatabase(context: Context): HaloDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HaloDatabase::class.java,
                    "halo_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}