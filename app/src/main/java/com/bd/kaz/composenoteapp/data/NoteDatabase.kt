package com.bd.kaz.composenoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bd.kaz.composenoteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

 abstract fun getNoteDao(): NoteDatabaseDao

}