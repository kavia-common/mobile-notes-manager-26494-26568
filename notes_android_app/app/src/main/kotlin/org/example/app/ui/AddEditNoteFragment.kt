package org.example.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.example.app.R
import org.example.app.data.NotesRepository
import com.google.android.material.button.MaterialButton

/**
 PUBLIC_INTERFACE
 AddEditNoteFragment
 Screen to add or edit a note. If noteId is provided, fields are prefilled and save updates it.
 */
class AddEditNoteFragment : Fragment() {

    private val viewModel: EditNoteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_add_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val noteId = arguments?.getString("noteId")

        val titleLayout = view.findViewById<TextInputLayout>(R.id.inputTitleLayout)
        val titleInput = view.findViewById<TextInputEditText>(R.id.inputTitle)
        val contentInput = view.findViewById<TextInputEditText>(R.id.inputContent)
        val btnSave = view.findViewById<MaterialButton>(R.id.btnSave)

        if (noteId != null) {
            val note = NotesRepository.getNote(noteId)
            titleInput.setText(note?.title ?: "")
            contentInput.setText(note?.content ?: "")
        }

        btnSave.setOnClickListener {
            val title = titleInput.text?.toString()?.trim().orEmpty()
            val content = contentInput.text?.toString()?.trim().orEmpty()

            if (title.isEmpty()) {
                titleLayout.error = getString(R.string.hint_title)
                return@setOnClickListener
            } else {
                titleLayout.error = null
            }

            val savedId = viewModel.save(noteId, title, content)
            // Navigate to details after save
            val nav = findNavController()
            nav.navigate(R.id.noteDetailsFragment, Bundle().apply { putString("noteId", savedId) })
        }
    }
}
