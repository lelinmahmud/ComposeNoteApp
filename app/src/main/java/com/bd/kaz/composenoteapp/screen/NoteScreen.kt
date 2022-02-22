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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bd.kaz.composenoteapp.components.NoteInputText

@Composable
fun NoteScreen(){
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
            NoteInputText(text = "Hello", label = "Note", onTextChange ={} )
        }
        
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen()
}
