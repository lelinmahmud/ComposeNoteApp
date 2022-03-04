package com.bd.kaz.composenoteapp.di

import android.content.Context
import androidx.room.Room
import com.bd.kaz.composenoteapp.data.NoteDatabase
import com.bd.kaz.composenoteapp.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDatabaseDao{
        return noteDatabase.getNoteDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase{
        return Room.databaseBuilder(context,NoteDatabase::class.java,"note_db").fallbackToDestructiveMigration().build()
    }

}