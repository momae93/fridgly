package com.au.fridgly.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.au.fridgly.data.local.models.FavoriteRecipeDB
import com.au.fridgly.data.local.services.IFavoriteService

@Database(entities = [FavoriteRecipeDB::class], version = 1)
abstract class AbstractDatabase: RoomDatabase() {
    abstract fun favoriteService(): IFavoriteService

    companion object {
        private var INSTANCE: AbstractDatabase? = null

        fun getDatabase(context: Context): AbstractDatabase {
            if (INSTANCE == null) {
                synchronized(AbstractDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AbstractDatabase::class.java, "fridgly_database")
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}