package com.example.realtimedatabasefirebase.note.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimedatabasefirebase.NODE_NOTES
import com.example.realtimedatabasefirebase.data.Note
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
class NoteViewModel() :ViewModel() {
    val dbNotes = FirebaseDatabase.getInstance().getReference(NODE_NOTES)
    private val _noteAdded = MutableLiveData<Exception?>()
    var noteAdded : MutableLiveData<Exception?> = _noteAdded
    fun addNote(note: Note){
        viewModelScope.launch {
            note.noteid=  dbNotes.push().key
            dbNotes.child(note.noteid!!).setValue(note).addOnCompleteListener {
                if(it.isSuccessful){
                    _noteAdded.value= null
                }else{
                    _noteAdded.value=it.exception
                }
            }
        }
    }
}