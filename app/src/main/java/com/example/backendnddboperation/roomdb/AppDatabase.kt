package com.example.backendnddboperation.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.backendnddboperation.utility.AppConstant
import com.example.backendnddboperation.utility.ThisApplication
import com.example.backendnddboperation.views.posts.Posts
import com.example.backendnddboperation.views.posts.Posts_Dao

/**
 * created by Ramanuj Kesharawani on 22/11/19
 */
@Database(entities = [Posts::class], version = AppConstant.ROOM_DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): Posts_Dao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke() = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase().also { instance = it }
        }

        private fun buildDatabase() = Room.databaseBuilder(
            ThisApplication.getAppInstance(),
            AppDatabase::class.java, AppConstant.ROOM_DB_NAME).allowMainThreadQueries()
            .build()
    }
}