package com.example.realtimedatabasefirebase.home.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.realtimedatabasefirebase.R
import com.example.realtimedatabasefirebase.note.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var addNoteButton :FloatingActionButton

class HomeFragment : Fragment() {
lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNoteButton = view.findViewById(R.id.fbtn_add_note)
        recycler = view.findViewById(R.id.recyclerView)
        var viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        viewModel.fetchData()
        viewModel.notes.observe(requireActivity()){
            Log.d("ittest", "onViewCreated: ${it} ")
            recycler.adapter= Adapter(it)
            recycler.layoutManager = LinearLayoutManager(requireContext(),  RecyclerView.VERTICAL, false)
        }
        addNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
        }
    }
}