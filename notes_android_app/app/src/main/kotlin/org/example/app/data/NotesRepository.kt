package org.example.app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * PUBLIC_INTERFACE
 * NotesRepository
 * A simple in-memory repository to manage notes. Replace with notes_local_database integration later.
 */
object NotesRepository {

    private val notes = mutableListOf<Note>()
    private val notesLive = MutableLiveData<List<Note>>(emptyList())

    fun getNotes(): LiveData<List<Note>> = notesLive

    fun getNote(id: String): Note? = notes.find { it.id == id }

    fun addNote(title: String, content: String): Note {
        val note = Note(title = title, content = content)
        notes.add(0, note)
        publish()
        return note
    }

    fun updateNote(id: String, title: String, content: String) {
        val n = notes.find { it.id == id } ?: return
        n.title = title
        n.content = content
        n.updatedAt = System.currentTimeMillis()
        publish()
    }

    fun deleteNote(id: String) {
        notes.removeAll { it.id == id }
        publish()
    }

    private fun publish() {
        notesLive.postValue(notes.toList())
    }
}
