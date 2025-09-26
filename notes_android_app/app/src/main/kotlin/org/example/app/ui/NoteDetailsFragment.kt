package org.example.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import org.example.app.R
import org.example.app.data.NotesRepository
import android.widget.TextView

/**
 PUBLIC_INTERFACE
 NoteDetailsFragment
 Shows full details of a note and allows editing or deleting it.
 */
class NoteDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_note_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val noteId = arguments?.getString("noteId") ?: return
        val note = NotesRepository.getNote(noteId) ?: return

        val title: TextView = view.findViewById(R.id.detailTitle)
        val content: TextView = view.findViewById(R.id.detailContent)
        val btnEdit: MaterialButton = view.findViewById(R.id.btnEdit)
        val btnDelete: MaterialButton = view.findViewById(R.id.btnDelete)

        title.text = note.title
        content.text = note.content

        btnEdit.setOnClickListener {
            findNavController().navigate(R.id.addEditNoteFragment, bundleOf("noteId" to noteId))
        }
        btnDelete.setOnClickListener {
            NotesRepository.deleteNote(noteId)
            findNavController().navigate(R.id.notesListFragment)
        }
    }
}
