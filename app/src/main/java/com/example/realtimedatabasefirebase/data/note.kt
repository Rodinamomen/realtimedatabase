package com.example.realtimedatabasefirebase.data

import com.google.firebase.database.Exclude

data class Note(
    @get:Exclude
    var noteid :String? =null,

    var noteTitle: String? =null,
    var noteDesc : String? =null
)
