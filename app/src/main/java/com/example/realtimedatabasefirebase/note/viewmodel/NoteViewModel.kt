package com.example.realtimedatabasefirebase.note.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimedatabasefirebase.NODE_NOTES
import com.example.realtimedatabasefirebase.data.Note
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
class NoteViewModel() :ViewModel() {
    val dbNotes = FirebaseDatabase.getInstance().getReference(NODE_NOTES)
    private val _noteAdded = MutableLiveData<Exception?>()
    var noteAdded : LiveData<Exception?> = _noteAdded
    private val _notes = MutableLiveData<List<Note>>()
    var notes: LiveData<List<Note>> = _notes
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
    fun fetchData(){
        viewModelScope.launch {
            dbNotes.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            val notesList = mutableListOf<Note>()
                            for(noteSnapshot in snapshot.children){
                                var note = noteSnapshot.getValue(Note::class.java)
                                note?.noteid = noteSnapshot.key
                               notesList.add(note!!)
                                Log.d("ittest", ": ${notesList} ")
                            }
                            _notes.value= notesList


                        }
                }

                override fun onCancelled(error: DatabaseError) {

                }


            })
        }
    }
}