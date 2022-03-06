package com.bd.kaz.composenoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bd.kaz.composenoteapp.model.Note
import com.bd.kaz.composenoteapp.util.DateConverter
import com.bd.kaz.composenoteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {

 abstract fun getNoteDao(): NoteDatabaseDao

}