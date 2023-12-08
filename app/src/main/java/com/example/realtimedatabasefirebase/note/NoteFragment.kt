package com.example.realtimedatabasefirebase.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.realtimedatabasefirebase.R
import com.example.realtimedatabasefirebase.data.Note
import com.example.realtimedatabasefirebase.note.viewmodel.NoteViewModel
import org.checkerframework.common.returnsreceiver.qual.This

lateinit var noteTitle : EditText
lateinit var noteDisc : EditText
lateinit var addNote : Button
class NoteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteTitle= view.findViewById(R.id.et_note_title)
        noteDisc = view.findViewById(R.id.et_note_desc)
        addNote= view.findViewById(R.id.btn_add)
        val viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        addNote.setOnClickListener {
            if(noteTitle.text.isNotEmpty() && noteDisc.text.isNotEmpty()){
                var note = Note()
                note.noteTitle= noteTitle.text.toString()
                note.noteDesc= noteDisc.text.toString()
                viewModel.addNote(note)
            }else{
                Toast.makeText(requireContext(), "PLEASE CHECK THAT TITLE AND DESC IS NOT EMPTY", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.noteAdded.observe(requireActivity()){
            if(it==null){
                Toast.makeText(requireContext(), " Added", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "${it}", Toast.LENGTH_SHORT).show()
            }
        }

    }

}