package org.example.app.ui

import androidx.lifecycle.ViewModel
import org.example.app.data.NotesRepository

/**
 * PUBLIC_INTERFACE
 * EditNoteViewModel
 * Provides create and update operations for notes.
 */
class EditNoteViewModel : ViewModel() {

    fun save(noteId: String?, title: String, content: String): String {
        return if (noteId == null) {
            NotesRepository.addNote(title, content).id
        } else {
            NotesRepository.updateNote(noteId, title, content)
            noteId
        }
    }
}
