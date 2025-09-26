package org.example.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.example.app.R
import org.example.app.data.Note
import com.google.android.material.card.MaterialCardView
import android.widget.ImageButton
import android.widget.TextView

/**
 PUBLIC_INTERFACE
 NotesListFragment
 Displays a list of notes in material cards with options to view, edit, and delete.
 */
class NotesListFragment : Fragment() {

    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerNotes)
        val emptyView = view.findViewById<View>(R.id.emptyState)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = NotesAdapter(
            onClick = { note ->
                findNavController().navigate(
                    R.id.noteDetailsFragment,
                    bundleOf("noteId" to note.id)
                )
            },
            onEdit = { note ->
                findNavController().navigate(
                    R.id.addEditNoteFragment,
                    bundleOf("noteId" to note.id)
                )
            },
            onDelete = { note ->
                viewModel.delete(note.id)
            }
        )
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        viewModel.notes.observe(viewLifecycleOwner) { list ->
            adapter.submit(list)
            emptyView.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private class NotesAdapter(
        val onClick: (Note) -> Unit,
        val onEdit: (Note) -> Unit,
        val onDelete: (Note) -> Unit
    ) : RecyclerView.Adapter<NotesAdapter.NoteVH>() {

        private val items = mutableListOf<Note>()

        fun submit(newItems: List<Note>) {
            items.clear()
            items.addAll(newItems)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_note_card, parent, false)
            return NoteVH(v)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: NoteVH, position: Int) {
            holder.bind(items[position], onClick, onEdit, onDelete)
        }

        class NoteVH(view: View) : RecyclerView.ViewHolder(view) {
            private val card: MaterialCardView = view.findViewById(R.id.card)
            private val title: TextView = view.findViewById(R.id.txtTitle)
            private val content: TextView = view.findViewById(R.id.txtContent)
            private val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
            private val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)

            fun bind(n: Note, onClick: (Note) -> Unit, onEdit: (Note) -> Unit, onDelete: (Note) -> Unit) {
                title.text = n.title
                content.text = n.content
                card.setOnClickListener { onClick(n) }
                btnEdit.setOnClickListener { onEdit(n) }
                btnDelete.setOnClickListener { onDelete(n) }
            }
        }
    }
}
