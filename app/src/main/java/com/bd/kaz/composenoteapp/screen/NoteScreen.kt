package com.bd.kaz.composenoteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bd.kaz.composenoteapp.components.NoteButton
import com.bd.kaz.composenoteapp.components.NoteInputText
import com.bd.kaz.composenoteapp.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note)-> Unit,
    onRemoveNote: (Note)-> Unit
){

    var title = remember {
        mutableStateOf("")
    }

    var description = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        TopAppBar(title = {
            Text(text = "Note App")
        },
        actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "notification")
        },
        backgroundColor = Color(0xFF96AC96)
        )
        
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = "${title.value}",
                label = "Title",
                onTextChange ={
                     if (it.all { char->
                             char.isLetter() || char.isWhitespace()
                         }) title.value = it
            }, modifier = Modifier.padding(top = 9.dp, bottom = 8.dp) )
            NoteInputText(
                text = "${description.value}",
                label = "Add a Note",
                onTextChange ={
                    if (it.all { char->
                            char.isLetter() || char.isWhitespace()
                        }) description.value = it
                },
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp ))
            NoteButton(label = "Save",
                onClickd = {
                    if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                        onAddNote(Note(title = title.value,
                            description = description.value))
                        title.value = ""
                        description.value = ""
                        Toast.makeText(context, "Note Added",
                            Toast.LENGTH_SHORT).show()
                    }
                })

        }

            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn(){
                items(notes){ note->
                    NoteRow(note = note, onNoteClick ={
                        onRemoveNote(it)
                    } )
                }
            }
        }
        
    }


@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick:(Note)->Unit
){

    Surface(
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
        ) {

        Column(
            modifier = Modifier
                .clickable {
                    onNoteClick(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
//            Text(text = note.entryTime.format(DateTimeFormatter.ofPattern("EEE,d MMM")), style = MaterialTheme.typography.caption, modifier = Modifier.padding(bottom = 5.dp))

        }

    }

}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(emptyList(),{},{})
}
