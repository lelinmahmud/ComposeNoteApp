package com.bd.kaz.composenoteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bd.kaz.composenoteapp.data.NotesDataSource
import com.bd.kaz.composenoteapp.model.Note

class NoteViewModel : ViewModel(){

    var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)

    }

    fun getAllNotes(): List<Note>{
        return noteList
    }
}