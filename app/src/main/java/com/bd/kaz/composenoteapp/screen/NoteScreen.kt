package com.bd.kaz.composenoteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bd.kaz.composenoteapp.components.NoteButton
import com.bd.kaz.composenoteapp.components.NoteInputText

@Composable
fun NoteScreen(){

    var title = remember {
        mutableStateOf("")
    }

    var description = remember {
        mutableStateOf("")
    }

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
            NoteButton(label = "Save", onClick = {  })
        }
        
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen()
}
