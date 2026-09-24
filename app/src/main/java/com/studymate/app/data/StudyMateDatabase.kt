package com.studymate.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LessonProgress::class],
    version = 1,
    exportSchema = false
)
abstract class StudyMateDatabase : RoomDatabase() {
    abstract fun progressDao(): LessonProgressDao

    companion object {
        @Volatile
        private var INSTANCE: StudyMateDatabase? = null

        fun get(context: Context): StudyMateDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StudyMateDatabase::class.java,
                    "studymate.db"
                ).build().also { INSTANCE = it }
            }
    }
}
