package com.bd.kaz.composenoteapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd.kaz.composenoteapp.data.NotesDataSource
import com.bd.kaz.composenoteapp.model.Note
import com.bd.kaz.composenoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel(){

  //  var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
       // noteList.addAll(NotesDataSource().loadNotes())
        viewModelScope.launch(Dispatchers.IO){
            noteRepository.getAllNotes().distinctUntilChanged()
                .collect { listOfNotes->
                    if (listOfNotes.isEmpty()){
                        Log.e("TAG", "List is empty" )
                    }
                    else{
                        _noteList.value = listOfNotes
                    }
                }
        }
    }

     fun addNote(note: Note) = viewModelScope.launch { noteRepository.addNote(note) }
     fun updateNote(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }
     fun removeNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }

}