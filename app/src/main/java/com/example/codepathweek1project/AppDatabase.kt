package com.example.codepathweek1project

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codepathweek1project.Flashcard
import com.example.codepathweek1project.FlashcardDao

@Database(entities = [Flashcard::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDao
}
