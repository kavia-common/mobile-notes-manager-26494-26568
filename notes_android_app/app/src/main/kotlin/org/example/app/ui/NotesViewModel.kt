package org.example.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.example.app.data.Note
import org.example.app.data.NotesRepository

/**
 * PUBLIC_INTERFACE
 * NotesViewModel
 * Exposes list of notes and operations to delete notes.
 */
class NotesViewModel : ViewModel() {
    val notes: LiveData<List<Note>> = NotesRepository.getNotes()

    fun delete(id: String) = NotesRepository.deleteNote(id)
}
